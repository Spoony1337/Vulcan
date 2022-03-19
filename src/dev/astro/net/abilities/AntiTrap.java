package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AntiTrap extends Ability implements Listener {

    private String name;
    private List<String> lore;
    private Map<UUID, Long> enemycooldown;
    private int hits;

    public AntiTrap() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("AntiTrap.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("AntiTrap.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("AntiTrap.Item.Lore"));
        this.enemycooldown = new HashMap<>();
    }

    /*
     * Item
     */
    private boolean isAntiTrap(ItemStack itemStack) {
        if (itemStack == null || !itemStack.hasItemMeta()) {
            return false;
        }
        ItemMeta itemMeta = itemStack.getItemMeta();
        return itemMeta.hasDisplayName() && itemMeta.hasLore() && itemMeta.getDisplayName().equals(this.name) && itemMeta.getLore().equals(this.lore);
    }

    /*
     * Item Utils
     */

    public ItemStack getItem() {
        return new ItemBuilder(new ItemStack(
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("AntiTrap.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("AntiTrap.Item.Data"))
        ).setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("AntiTrap.Item.Name"))).setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("AntiTrap.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("AntiTrap.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("AntiTrap.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("AntiTrap.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("AntiTrap.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        Long lastInteractTime = this.enemycooldown.get(p.getUniqueId());
        if (lastInteractTime != null && lastInteractTime - System.currentTimeMillis() > 0L) {
            if (Vulcan.getInstance().getConfig().getBoolean("AntiTrap.Options.DenyPlaceBlocks")) {
                for (String s : Vulcan.getInstance().getConfig().getStringList("AntiTrap.Lang.Target.OnPlaceBlock")) {
                    p.sendMessage(CC.translate(s));
                }
                e.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        Long lastInteractTime = this.enemycooldown.get(p.getUniqueId());
        if (lastInteractTime != null && lastInteractTime - System.currentTimeMillis() > 0L) {
            if (Vulcan.getInstance().getConfig().getBoolean("AntiTrap.Options.DenyBreakBlocks")) {
                for (String s : Vulcan.getInstance().getConfig().getStringList("AntiTrap.Lang.Target.OnBreakBlock")) {
                    p.sendMessage(CC.translate(s));
                }
                e.setCancelled(true);
            }
        }
    }

    public void cancelPlaceBlocks(UUID uuid, long delay) {
        this.enemycooldown.put(uuid, System.currentTimeMillis() + delay);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getDamager();
        Player entity = (Player) e.getEntity();

        if (isAntiTrap(p.getItemInHand())) {
            if ((p.getItemInHand() == null) || (p.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setCancelled(true);
                p.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("AntiTrap.Lang.OnCooldown").replace("%time%", Formatter.getRemaining(this.getRemaining(p.getUniqueId()), true, false))));
                p.updateInventory();
                return;
            }
            if (!isEnabled()) {
                p.sendMessage(Vulcan.getInstance().getConfigService().ABILITY_DISABLED());
                return;
            }
            if (isInRegion(p)) {
                p.sendMessage(Vulcan.getInstance().getConfigService().ON_ABILITY_USE_IN_REGION());
                return;
            }
            if (isInRegion(entity)) {
                p.sendMessage(Vulcan.getInstance().getConfigService().TARGET_IS_IN_REGION());
                return;
            }
            for (String player : Vulcan.getInstance().getConfig().getStringList("AntiTrap.Lang.OnUse.Player")) {
                p.sendMessage(CC.translate(player).replace("%target%", entity.getDisplayName()));
            }
            for (String target : Vulcan.getInstance().getConfig().getStringList("AntiTrap.Lang.OnUse.Target")) {
                entity.sendMessage(CC.translate(target).replace("%player%", p.getDisplayName()));
            }
            if (hits < Vulcan.getInstance().getConfig().getInt("AntiTrap.Options.Hits")) {
                hits++;
            }
            if (hits == Vulcan.getInstance().getConfig().getInt("AntiTrap.Options.Hits")) {
                cancelPlaceBlocks(entity.getUniqueId(), Vulcan.getInstance().getConfig().getInt("AntiTrap.Options.Time") * 1000);
                setCooldown(p, p.getUniqueId());
                hits = 0;
                if (Vulcan.getInstance().getConfig().getBoolean("AntiTrap.Options.RemoveOnUse")) {
                    if (p.getInventory().getItemInHand().getAmount() == 1) {
                        p.getInventory().setItemInHand(null);
                        return;
                    }
                    p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
                }
            }
        }

    }


    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        enemycooldown.remove(player.getUniqueId());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        enemycooldown.remove(player.getUniqueId());
    }
}