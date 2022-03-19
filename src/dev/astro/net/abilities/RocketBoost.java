package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.HashSet;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class RocketBoost extends Ability implements Listener {

    private String name;
    private List<String> lore;
    private HashSet<Player> nofall;

    public RocketBoost() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("RocketBoost.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("RocketBoost.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("RocketBoost.Item.Lore"));
        this.nofall = new HashSet<>();
    }

    /*
     * Item
     */
    private boolean isRocket(ItemStack itemStack) {
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
        return new ItemBuilder(Material.FIREWORK).
                setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("RocketBoost.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("RocketBoost.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("RocketBoost.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("RocketBoost.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("RocketBoost.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("RocketBoost.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (isRocket(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("RocketBoost.Lang.OnCooldown")) {
                    p.sendMessage(CC.translate(s).replace("%time%", Formatter.getRemaining(this.getRemaining(p.getUniqueId()), true, false)));
                }
                p.updateInventory();
                return;
            }
            if (!isEnabled()) {
                p.sendMessage(Vulcan.getInstance().getConfigService().ABILITY_DISABLED());
                return;
            }
            if(isInRegion(p)){
                p.sendMessage(Vulcan.getInstance().getConfigService().ON_ABILITY_USE_IN_REGION());
                return;
            }
            p.setVelocity(new Vector(0.1, 2.0, 0.0));
            p.playSound(p.getLocation(), Sound.FIZZ, 1f, 1f);
            e.setCancelled(true);
            p.updateInventory();
            setCooldown(p, p.getUniqueId());
            nofall.add(p);
            new BukkitRunnable() {

                @Override
                public void run() {
                    nofall.remove(p);
                }
            }.runTaskLater(Vulcan.getInstance(), 100);
            if (Vulcan.getInstance().getConfig().getBoolean("RocketBoost.Options.RemoveOnUse")) {
                if (p.getInventory().getItemInHand().getAmount() == 1) {
                    p.getInventory().setItemInHand(null);
                    return;
                }
                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }

    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(e.getCause().equals(EntityDamageEvent.DamageCause.FALL))) return;
        Player p = (Player) e.getEntity();
        if (nofall.contains(p)) {
            e.setCancelled(true);
        }
    }
}