package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Cobweb extends Ability implements Listener {

    private String name;
    private List<String> lore;
    private ArrayList<FallingBlock> falling;

    public Cobweb() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("Cobweb.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("Cobweb.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("Cobweb.Item.Lore"));
        this.falling = new ArrayList<>();
    }

    /*
     * Item
     */
    private boolean isCobweb(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("Cobweb.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("Cobweb.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Cobweb.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Cobweb.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("Cobweb.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("Cobweb.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("Cobweb.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("Cobweb.Lang.OnExpire")) {
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
        World world = p.getWorld();
        if (isCobweb(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("Cobweb.Lang.OnCooldown")) {
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
            @Deprecated
            FallingBlock block = world.spawnFallingBlock(p.getEyeLocation(), Material.WEB, (byte) 0);
            Vector smallerVector = p.getLocation().getDirection().multiply(2);
            block.setVelocity(smallerVector);
            falling.add(block);
            setCooldown(p, p.getUniqueId());
            e.setCancelled(true);
            if (Vulcan.getInstance().getConfig().getBoolean("Cobweb.Options.RemoveOnUse")) {
                if (p.getInventory().getItemInHand().getAmount() == 1) {
                    p.getInventory().setItemInHand(null);
                    return;
                }
                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }

    @EventHandler
    public void fallingImpact(EntityChangeBlockEvent e) {
        if (!(e.getEntity() instanceof FallingBlock)) {
            return;
        }
        if (!e.getBlock().isEmpty()) {
            return;
        }
        FallingBlock fb = (FallingBlock) e.getEntity();
        if (!this.falling.contains(fb)) {
            return;
        }
        new BukkitRunnable() {
            public void run() {
                falling.remove(fb);
                e.getBlock().setType(Material.AIR);
                e.getBlock().getState().update();
                e.setCancelled(true);
            }
        }.runTaskLater(Vulcan.getInstance(), Vulcan.getInstance().getConfig().getInt("Cobweb.Options.DespawnTime") * 20);
    }
}
