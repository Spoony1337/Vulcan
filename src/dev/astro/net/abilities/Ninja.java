package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.projectiles.ProjectileSource;
import org.bukkit.scheduler.BukkitRunnable;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Ninja extends Ability implements Listener {

    private String name;
    private List<String> lore;
    Player attacker;

    public Ninja() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("Ninja.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("Ninja.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("Ninja.Item.Lore"));
        this.attacker = null;
    }

    /*
     * Item
     */
    public boolean isNinja(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("Ninja.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("Ninja.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Ninja.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Ninja.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("Ninja.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("Ninja.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("Ninja.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.OnExpire")) {
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
        if (isNinja(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.OnCooldown")) {
                    p.sendMessage(CC.translate(s).replace("%time%", Formatter.getRemaining(this.getRemaining(p.getUniqueId()), true, false)));
                }
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
            if (this.attacker == null || this.attacker == p) {
                for (String notarget : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.Player.NoTarget")) {
                    p.sendMessage(CC.translate(notarget));
                }
                return;
            }
            if (p.getLocation().distance(attacker.getLocation()) > Vulcan.getInstance().getConfig().getInt("Ninja.Options.Range")) {
                for (String outofrange : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.Player.OutOfRange")) {
                    p.sendMessage(CC.translate(outofrange).replace("%target%", attacker.getDisplayName()));
                }
                return;
            }
            for (String onuse : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.Player.OnUse")) {
                p.sendMessage(CC.translate(onuse).replace("%target%", attacker.getDisplayName()));
            }
            for (String onplayeruse : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.Target.OnPlayerUse")) {
                attacker.sendMessage(CC.translate(onplayeruse).replace("%player%", p.getDisplayName()));
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    p.teleport(attacker.getLocation());
                    for (String onteleport : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.Player.OnTeleport")) {
                        p.sendMessage(CC.translate(onteleport).replace("%target%", attacker.getDisplayName()));
                    }
                    for (String onplayerteleport : Vulcan.getInstance().getConfig().getStringList("Ninja.Lang.Target.OnPlayerTeleport")) {
                        attacker.sendMessage(CC.translate(onplayerteleport).replace("%player%", p.getDisplayName()));

                    }
                }
            }.runTaskLater(Vulcan.getInstance(), Vulcan.getInstance().getConfig().getInt("Ninja.Options.TeleportCooldown") * 20);

            setCooldown(p, p.getUniqueId());
            if (Vulcan.getInstance().getConfig().getBoolean("Ninja.Options.RemoveOnUse")) {
                if (p.getInventory().getItemInHand().getAmount() == 1) {
                    p.getInventory().setItemInHand(null);
                    return;
                }
                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }

    @EventHandler
    private Player onAttack(EntityDamageByEntityEvent e) {
        Entity damager = e.getDamager();
        if (e.getDamager() instanceof Player) {
            attacker = (Player) damager;
        } else if (e.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) damager;
            ProjectileSource shooter = projectile.getShooter();
            if (shooter instanceof Player) {
                attacker = (Player) shooter;
            }
        }
        if (attacker != null && e.getEntity().equals(attacker)) {
            attacker = null;
        }
        return attacker;
    }
}
