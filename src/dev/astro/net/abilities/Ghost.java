package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class Ghost extends Ability implements Listener {

    private String name;
    private List<String> lore;
    private HashSet<UUID> invisCooldown;

    public Ghost() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("Ghost.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("Ghost.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("Ghost.Item.Lore"));
        this.invisCooldown = new HashSet<>();
    }

    /*
     * Item
     */
    private boolean isGhost(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("Ghost.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("Ghost.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Ghost.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Ghost.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("Ghost.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("Ghost.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("Ghost.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("Ghost.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player player = (Player) event.getDamager();
            Player target = (Player) event.getEntity();
            if (this.invisCooldown.contains(target.getUniqueId())) {
                target.removePotionEffect(PotionEffectType.INVISIBILITY);
                this.invisCooldown.remove(target.getUniqueId());
                for (String totarget : Vulcan.getInstance().getConfig().getStringList("Ghost.Lang.Target.OnAttack")) {
                    target.sendMessage(CC.translate(totarget).replace("%player%", player.getDisplayName()));
                }
                for (String toplayer : Vulcan.getInstance().getConfig().getStringList("Ghost.Lang.Player.OnDamage")) {
                    player.sendMessage(CC.translate(toplayer).replace("%target%", target.getDisplayName()));
                }
            }
        }
    }

    @EventHandler
    public void onAttack(EntityDamageByEntityEvent event) {
        if (event.getEntity() instanceof Player && event.getDamager() instanceof Player) {
            Player damager = (Player) event.getDamager();
            if (this.invisCooldown.contains(damager.getUniqueId())) {
                damager.removePotionEffect(PotionEffectType.INVISIBILITY);
                this.invisCooldown.remove(damager.getUniqueId());
                for (String toplayer : Vulcan.getInstance().getConfig().getStringList("Ghost.Lang.Player.OnAttack")) {
                    damager.sendMessage(CC.translate(toplayer));
                }
            }
        }
    }

    public void putFullInvis(UUID uuid, long delay) {
        this.invisCooldown.add(uuid);
        Player p = Bukkit.getPlayer(uuid);
        p.addPotionEffect(new PotionEffect(PotionEffectType.INVISIBILITY, Vulcan.getInstance().getConfig().getInt("Ghost.Options.Duration") * 20, 0));
        for (Player online : Bukkit.getOnlinePlayers()) {
            online.canSee(p);
        }
        setCooldown(p, p.getUniqueId());
        new BukkitRunnable() {

            @Override
            public void run() {
                invisCooldown.remove(uuid);
            }
        }.runTaskLater(Vulcan.getInstance(), delay * 1000);
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (isGhost(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("Ghost.Lang.OnCooldown")) {
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
            putFullInvis(p.getUniqueId(), Vulcan.getInstance().getConfig().getInt("Ghost.Options.Duration"));
            if (Vulcan.getInstance().getConfig().getBoolean("Ghost.Options.RemoveOnUse")) {
                if (p.getInventory().getItemInHand().getAmount() == 1) {
                    p.getInventory().setItemInHand(null);
                    return;
                }
                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }
}
