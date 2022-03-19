package dev.astro.net.utilities;

import com.google.common.base.Optional;
import com.google.common.base.Predicate;
import com.sk89q.worldedit.bukkit.selections.CuboidSelection;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.timers.Timer;
import dev.astro.net.utilities.timers.TimerCooldown;
import dev.astro.net.utilities.timers.events.*;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public abstract class Ability extends Timer {

    /*
    Abilities Cooldown
    Usages:
      is on cooldown: hasCooldown(p);
	  set on cooldown: setCooldown(player, player.getUniqueId());
	  get cooldown: Formatter.getRemaining(this.getRemaining(player.getUniqueId()), true, false)
	  clear cooldown clearCooldown(p);

     */

    protected boolean persistable;
    protected Map<UUID, TimerCooldown> cooldowns = new ConcurrentHashMap<>();

    public Ability(long defaultCooldown, boolean persistable) {
        super(defaultCooldown);
        this.persistable = persistable;
    }

    public Ability(long defaultCooldown) {
        this(defaultCooldown, true);
    }

    public void onExpire(UUID userUUID) {
    }

    @EventHandler(ignoreCancelled = true, priority = EventPriority.MONITOR)
    public void onTimerExpireLoadReduce(TimerExpireEvent event) {
        if (event.getTimer() == this) {
            Optional<UUID> optionalUserUUID = event.getUserUUID();
            if (optionalUserUUID.isPresent()) {
                UUID userUUID = optionalUserUUID.get();
                onExpire(userUUID);
                clearCooldown(userUUID);
            }
        }
    }

    public void clearCooldown(Player player) {
        clearCooldown(player.getUniqueId());
    }

    public TimerCooldown clearCooldown(UUID playerUUID) {
        TimerCooldown runnable = cooldowns.remove(playerUUID);
        if (runnable != null) {
            runnable.cancel();
            Bukkit.getPluginManager().callEvent(new TimerClearEvent(playerUUID, this));
            return runnable;
        }
        return null;
    }

    public boolean isPaused(Player player) {
        return isPaused(player.getUniqueId());
    }

    public boolean isPaused(UUID playerUUID) {
        TimerCooldown runnable = cooldowns.get(playerUUID);
        return runnable != null && runnable.isPaused();
    }

    public void setPaused(UUID playerUUID, boolean paused) {
        TimerCooldown runnable = cooldowns.get(playerUUID);
        if (runnable != null && runnable.isPaused() != paused) {
            TimerPauseEvent event = new TimerPauseEvent(playerUUID, this, paused);
            Bukkit.getPluginManager().callEvent(event);
            if (!event.isCancelled()) {
                runnable.setPaused(paused);
            }
        }
    }

    public boolean hasCooldown(UUID userUUID) {
        return this.getRemaining(userUUID) > 0L;
    }

    public boolean hasCooldown(Player player) {
        return this.getRemaining(player) > 0L;
    }

    public long getRemaining(Player player) {
        return getRemaining(player.getUniqueId());
    }

    public long getRemaining(UUID playerUUID) {
        TimerCooldown runnable = cooldowns.get(playerUUID);
        return runnable == null ? 0L : runnable.getRemaining();
    }

    public boolean setCooldown(Player player, UUID playerUUID) {
        return setCooldown(player, playerUUID, defaultCooldown, false);
    }

    public boolean setCooldown(Player player, UUID playerUUID, long duration, boolean overwrite) {
        return setCooldown(player, playerUUID, duration, overwrite, null);
    }

    public boolean setCooldown(Player player, UUID playerUUID, long duration, boolean overwrite, Predicate<Long> currentCooldownPredicate) {
        TimerCooldown runnable = duration > 0L ? cooldowns.get(playerUUID) : this.clearCooldown(playerUUID);
        if (runnable != null) {
            long remaining = runnable.getRemaining();
            if (!overwrite && remaining > 0L && duration <= remaining) {
                return false;
            }

            TimerExtendEvent event = new TimerExtendEvent(player, playerUUID, this, remaining, duration);
            Bukkit.getPluginManager().callEvent(event);
            if (event.isCancelled()) {
                return false;
            }

            boolean flag = true;
            if (currentCooldownPredicate != null) {
                flag = currentCooldownPredicate.apply(remaining);
            }

            if (flag) {
                runnable.setRemaining(duration);
            }

            return flag;
        } else {
            Bukkit.getPluginManager().callEvent(new TimerStartEvent(player, playerUUID, this, duration));
            runnable = new TimerCooldown(this, playerUUID, duration);
        }

        cooldowns.put(playerUUID, runnable);
        return true;
    }

    public boolean isInRegion(Player p) {
        if (Vulcan.getInstance().getRegionsFile().getConfigurationSection("Regions").getKeys(false) == null) {
            return false;
        } else {
            for (String claim : Vulcan.getInstance().getRegionsFile().getConfigurationSection("Regions").getKeys(false)) {
                CuboidSelection selection = new CuboidSelection(Bukkit.getWorld(Vulcan.getInstance().getRegionsFile().getString("Regions." + claim + ".world")), getLocation(claim, "A"), getLocation(claim, "B"));
                if (selection.contains(p.getLocation())) {
                    return true;
                }
            }
        }
        return false;
    }

    private Location getLocation(String town, String corner) {
        World world = Bukkit.getWorld((String) Vulcan.getInstance().getRegionsFile().get("Regions." + town + ".world"));
        double x = Vulcan.getInstance().getRegionsFile().getDouble("Regions." + town + "." + corner + ".x");
        double y = Vulcan.getInstance().getRegionsFile().getDouble("Regions." + town + "." + corner + ".y");
        double z = Vulcan.getInstance().getRegionsFile().getDouble("Regions." + town + "." + corner + ".z");
        return new Location(world, x, y, z);
    }
}