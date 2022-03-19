package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
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

public class ViewSwitcher extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public ViewSwitcher() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("ViewSwitcher.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("ViewSwitcher.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("ViewSwitcher.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isViewSwitcher(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("ViewSwitcher.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("ViewSwitcher.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("ViewSwitcher.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("ViewSwitcher.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("ViewSwitcher.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("ViewSwitcher.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("ViewSwitcher.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("ViewSwitcher.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getDamager() instanceof Player)) return;
        if (!(e.getEntity() instanceof Player)) return;
        Player damager = (Player) e.getDamager();
        Player damaged = (Player) e.getEntity();

        if (isViewSwitcher(damager.getItemInHand())) {
            if ((damager.getItemInHand() == null) || (damager.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(damager)) {
                e.setCancelled(true);
                for (String s : Vulcan.getInstance().getConfig().getStringList("ViewSwitcher.Lang.OnCooldown")) {
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
            Location location = damaged.getLocation();
            location.setYaw(location.getYaw() + Vulcan.getInstance().getConfig().getInt("ViewSwitcher.Options.Rotate.Yaw"));
            location.setPitch(location.getPitch() + Vulcan.getInstance().getConfig().getInt("ViewSwitcher.Options.Rotate.Pitch"));
            damaged.teleport(location);
            setCooldown(damager, damager.getUniqueId());
            if (Vulcan.getInstance().getConfig().getBoolean("ViewSwitcher.Options.RemoveOnUse")) {
                if (damager.getInventory().getItemInHand().getAmount() == 1) {
                    damager.getInventory().setItemInHand(null);
                    return;
                }
                damager.getInventory().getItemInHand().setAmount(damager.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }
}
