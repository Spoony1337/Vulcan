package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class SwapperHelmet extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public SwapperHelmet() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("SwapperHelmet.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("SwapperHelmet.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("SwapperHelmet.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isSwapper(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("SwapperHelmet.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("SwapperHelmet.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("SwapperHelmet.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("SwapperHelmet.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("SwapperHelmet.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("SwapperHelmet.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("SwapperHelmet.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("SwapperHelmet.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof Player)) return;

        Player damager = (Player) e.getDamager();
        Player damaged = (Player) e.getEntity();

        List<String> delayDamager = Vulcan.getInstance().getConfig().getStringList("SwapperHelmet.Lang.Player.OnUse");
        List<String> delayDamaged = Vulcan.getInstance().getConfig().getStringList("SwapperHelmet.Lang.Target.OnDamage");

        if (isSwapper(damager.getItemInHand())) {
            if ((damager.getItemInHand() == null) || (damager.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(damager)) {
                e.setCancelled(true);
                for (String s : Vulcan.getInstance().getConfig().getStringList("SwapperHelmet.Lang.OnCooldown")) {
                    damager.sendMessage(CC.translate(s).replace("%time%", Formatter.getRemaining(this.getRemaining(damager.getUniqueId()), true, false)));
                }
                damager.updateInventory();
                return;
            }
            if (!isEnabled()) {
                damager.sendMessage(Vulcan.getInstance().getConfigService().ABILITY_DISABLED());
                return;
            }
            if (isInRegion(damager)) {
                damager.sendMessage(Vulcan.getInstance().getConfigService().ON_ABILITY_USE_IN_REGION());
                return;
            }
            if (damaged.getInventory().getHelmet() == null) {
                return;
            }
            if (Vulcan.getInstance().getConfig().getBoolean("SwapperHelmet.Options.OnlyDiamondHelmet")) {
                if (damaged.getInventory().getHelmet().getType() != Material.DIAMOND_HELMET) {
                    damager.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("SwapperHelmet.Lang.Target.NoDiamondHelmet")).replace("%target%", damaged.getName()));
                    return;
                }
            }
            for (String s : delayDamaged) {
                damaged.sendMessage(CC.translate(s).replace("%player%", damager.getName()));
            }
            for (String s : delayDamager) {
                damager.sendMessage(CC.translate(s).replace("%target%", damaged.getName()));
            }
            Bukkit.getScheduler().scheduleSyncDelayedTask(Vulcan.getInstance(), () -> {
                if (!(damaged.getInventory().firstEmpty() < 0)) {
                    damaged.getInventory().addItem(damaged.getInventory().getHelmet());
                    damaged.getInventory().setHelmet(null);
                } else {
                    World world = damaged.getWorld();
                    world.dropItem(damaged.getLocation(), damaged.getInventory().getHelmet());
                    damaged.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("SwapperHelmet.Lang.Target.FullInventory")));
                }
            }, 20 * Vulcan.getInstance().getConfig().getInt("SwapperHelmet.Options.Delay"));
            setCooldown(damager, damager.getUniqueId());
        }
    }
}