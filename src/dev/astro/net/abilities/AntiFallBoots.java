package dev.astro.net.abilities;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;

public class AntiFallBoots implements Listener {

    private String name;
    private List<String> lore;

    public AntiFallBoots() {
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("AntiFallBoots.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("AntiFallBoots.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isAntiFallBoots(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("AntiFallBoots.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("AntiFallBoots.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("AntiFallBoots.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("AntiFallBoots.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("AntiFallBoots.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("AntiFallBoots.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("AntiFallBoots.Enabled", false);
    }


    /*
     * Listener
     */
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(e.getCause() == EntityDamageEvent.DamageCause.FALL)) return;
        Player p = (Player) e.getEntity();
        if (isAntiFallBoots(p.getInventory().getBoots())) {
            if (!isEnabled()) {
                p.sendMessage(Vulcan.getInstance().getConfigService().ABILITY_DISABLED());
                return;
            }
            e.setCancelled(true);
            if (Vulcan.getInstance().getConfig().getBoolean("AntiFallBoots.Options.RemoveOnUse")) {
                p.getInventory().getBoots().setAmount(0);
            }
        }
    }
}