package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
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

public class PotionRefill extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public PotionRefill() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("PotionRefill.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("PotionRefill.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("PotionRefill.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isPotionRefill(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("PotionRefill.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("PotionRefill.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("PotionRefill.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("PotionRefill.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("PotionRefill.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("PotionRefill.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("PotionRefill.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("PotionRefill.Lang.OnExpire")) {
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
        if (isPotionRefill(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("PotionRefill.Lang.OnCooldown")) {
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
            if (p.getInventory().firstEmpty() < 0) {
                for (String fullinv : Vulcan.getInstance().getConfig().getStringList("PotionRefill.Lang.FullInventory")) {
                    p.sendMessage(CC.translate(fullinv));
                }
                p.updateInventory();
                return;
            }
            ItemStack[] inv = p.getInventory().getContents();
            ItemStack item = new ItemStack(Material.POTION, 1, (short) 16421);
            for (int i = 0; i < inv.length; i++) {
                p.getInventory().addItem(item);
                p.updateInventory();
            }
            for (String onuse : Vulcan.getInstance().getConfig().getStringList("PotionRefill.Lang.OnUse")) {
                p.sendMessage(CC.translate(onuse));
            }
            setCooldown(p, p.getUniqueId());
            if (Vulcan.getInstance().getConfig().getBoolean("PotionRefill.Options.RemoveOnUse")) {
                if (p.getInventory().getItemInHand().getAmount() == 1) {
                    p.getInventory().setItemInHand(null);
                    return;
                }
                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }
}
