package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class BedBomb extends Ability implements Listener {

    private String name;

    public BedBomb() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("BedBomb.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("BedBomb.Item.Name"));
    }

    /*
     * Item Utils
     */

    public ItemStack getItem() {
        return new ItemBuilder(new ItemStack(Material.BED))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("BedBomb.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("BedBomb.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("BedBomb.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("BedBomb.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("BedBomb.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("BedBomb.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (e.getItemInHand().getType() == getItem().getType() && e.getItemInHand().hasItemMeta() && e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(name) && e.getItemInHand().getItemMeta().hasLore()) {
            if ((e.getItemInHand() == null) || (e.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setCancelled(true);
                for (String s : Vulcan.getInstance().getConfig().getStringList("BedBomb.Lang.OnCooldown")) {
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
            e.getBlockPlaced().setType(Material.AIR);
            Location loc = e.getBlockPlaced().getLocation();
            p.getWorld().createExplosion(loc.getX(), loc.getY(), loc.getZ(), 5f, false, false);
            setCooldown(p, p.getUniqueId());
            if (p.getInventory().getItemInHand().getAmount() == 1) {
                p.getInventory().setItemInHand(null);
                return;
            }
            p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
        }
    }
}
