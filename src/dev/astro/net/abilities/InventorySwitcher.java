package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.Inventory;
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

public class InventorySwitcher extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public InventorySwitcher() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("InventorySwitcher.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("InventorySwitcher.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("InventorySwitcher.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isInventorySwitcher(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("InventorySwitcher.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("InventorySwitcher.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("InventorySwitcher.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("InventorySwitcher.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("InventorySwitcher.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("InventorySwitcher.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("InventorySwitcher.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("InventorySwitcher.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void OnDamage(EntityDamageByEntityEvent e) {
        if (e.getEntity() instanceof Player && e.getDamager() instanceof Player) {
            Player damager = (Player) e.getDamager();
            Player target = (Player) e.getEntity();
        
        if (isInventorySwitcher(damager.getItemInHand())) {
            if ((damager.getItemInHand() == null) || (damager.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(damager)) {
                e.setCancelled(true);
                for (String s : Vulcan.getInstance().getConfig().getStringList("InventorySwitcher.Lang.OnCooldown")) {
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
            Inventory targetinv = target.getInventory();
            ItemStack slot1 = targetinv.getItem(0);
            ItemStack slot2 = targetinv.getItem(1);
            ItemStack slot3 = targetinv.getItem(2);
            ItemStack slot4 = targetinv.getItem(3);
            ItemStack slot5 = targetinv.getItem(4);
            ItemStack slot6 = targetinv.getItem(5);
            ItemStack slot7 = targetinv.getItem(6);
            ItemStack slot8 = targetinv.getItem(7);
            ItemStack slot9 = targetinv.getItem(8);
            targetinv.setItem(0, slot4);
            targetinv.setItem(1, slot3);
            targetinv.setItem(2, slot6);
            targetinv.setItem(3, slot8);
            targetinv.setItem(4, slot9);
            targetinv.setItem(5, slot1);
            targetinv.setItem(6, slot2);
            targetinv.setItem(7, slot5);
            targetinv.setItem(8, slot7);
        
            target.updateInventory();
            for (String toplayer : Vulcan.getInstance().getConfig().getStringList("InventorySwitcher.Lang.Player")) {
                damager.sendMessage(CC.translate(toplayer).replace("%target%", target.getDisplayName()));
            }
            for (String totarget : Vulcan.getInstance().getConfig().getStringList("InventorySwitcher.Lang.Target")) {
                target.sendMessage(CC.translate(totarget).replace("%player%", damager.getDisplayName()));
            }
            setCooldown(damager, damager.getUniqueId());
            if (Vulcan.getInstance().getConfig().getBoolean("InventorySwitcher.Options.RemoveOnUse")) {
                if (damager.getInventory().getItemInHand().getAmount() == 1) {
                    damager.getInventory().setItemInHand(null);
                    return;
                }
                damager.getInventory().getItemInHand().setAmount(damager.getInventory().getItemInHand().getAmount() - 1);
            }
        }
        }
    }
}
