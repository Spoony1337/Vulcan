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

public class PearlReset extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public PearlReset() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("PearlReset.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("PearlReset.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("PearlReset.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isPearlReset(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("PearlReset.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("PearlReset.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("PearlReset.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("PearlReset.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("PearlReset.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("PearlReset.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("PearlReset.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("PearlReset.Lang.OnExpire")) {
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
        if (isPearlReset(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("PearlReset.Lang.OnCooldown")) {
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
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Vulcan.getInstance().getConfig().getString("PearlReset.Options.Command").replace("%player%", p.getName()));
            setCooldown(p, p.getUniqueId());
            if (Vulcan.getInstance().getConfig().getBoolean("PearlReset.Options.RemoveOnUse")) {
                if (p.getInventory().getItemInHand().getAmount() == 1) {
                    p.getInventory().setItemInHand(null);
                    return;
                }
                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }
}

