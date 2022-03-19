package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Switcher extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public Switcher() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("Switcher.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("Switcher.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("Switcher.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isSwitcher(ItemStack itemStack) {
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
        if (Vulcan.getInstance().getConfig().getString("Switcher.Item.Type").toUpperCase().equalsIgnoreCase("SNOWBALL")) {
            return new ItemBuilder(Material.SNOW_BALL)
                    .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Switcher.Item.Name")))
                    .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Switcher.Item.Lore"))).build();
        } else if (Vulcan.getInstance().getConfig().getString("Switcher.Item.Type").toUpperCase().equalsIgnoreCase("EGG")) {
            return new ItemBuilder(Material.EGG)
                    .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Switcher.Item.Name")))
                    .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Switcher.Item.Lore"))).build();
        }
        return null;
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("Switcher.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("Switcher.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("Switcher.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("Switcher.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onLaunch(ProjectileLaunchEvent e) {
        Projectile entity = e.getEntity();
        if (!(entity.getShooter() instanceof Player)) return;
        Player p = (Player) entity.getShooter();
        if (!(e.getEntity().getShooter() instanceof Player)) {
            return;
        }
        if (isSwitcher(p.getItemInHand())) {
            if ((p.getItemInHand() == null) || p.getItemInHand().getType() != getItem().getType()) {
                return;
            }

            if (entity instanceof Snowball) {
                Snowball snowball = (Snowball) entity;
                snowball.setMetadata("switcher", new FixedMetadataValue(Vulcan.getInstance(), p.getUniqueId()));
                setCooldown(p, p.getUniqueId());

            }
        }
    }

    @EventHandler
    public void hasCooldown(PlayerInteractEvent e) {
        Player p = e.getPlayer();
    
        if (isSwitcher(p.getItemInHand())) {
            if ((p.getItemInHand() == null) || p.getItemInHand().getType() != getItem().getType()) {
                return;
            }
            if (hasCooldown(p)) {
            	   e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("Switcher.Lang.OnCooldown")) {
                    p.sendMessage(CC.translate(s).replace("%time%", Formatter.getRemaining(this.getRemaining(p.getUniqueId()), true, false)));
                }
                p.updateInventory();
                return;
            }
            if (!isEnabled()) {
            	   e.setUseItemInHand(Event.Result.DENY);
                p.sendMessage(Vulcan.getInstance().getConfigService().ABILITY_DISABLED());
                return;
            }
            if(isInRegion(p)){
            	   e.setUseItemInHand(Event.Result.DENY);
                p.sendMessage(Vulcan.getInstance().getConfigService().ON_ABILITY_USE_IN_REGION());
                return;
            }
        }
    }
    
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (e.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) e.getDamager();
            if (!(projectile.getShooter() instanceof Player)) return;
            if (!projectile.hasMetadata("switcher")) return;
            if (e.getEntity() instanceof Player) {
                Player shooter = (Player) projectile.getShooter();
                Location shooterLoc = shooter.getLocation();
                Player damaged = (Player) e.getEntity();
                if (damaged.getLocation().distance(shooter.getLocation()) <= Vulcan.getInstance().getConfig().getInt("Switcher.Options.Range")) {
                    shooter.teleport(e.getEntity().getLocation());
                    damaged.teleport(shooterLoc);
                    for (String s : Vulcan.getInstance().getConfig().getStringList("Switcher.Lang.OnUse.Player")) {
                        shooter.sendMessage(CC.translate(s).replace("%target%", damaged.getDisplayName()));
                    }
                    for (String s : Vulcan.getInstance().getConfig().getStringList("Switcher.Lang.OnUse.Target")) {
                        damaged.sendMessage(CC.translate(s).replace("%player%", shooter.getDisplayName()));
                    }
                }
            }
        }
    }
}
