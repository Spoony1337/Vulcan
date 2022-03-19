package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Freeze extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public Freeze() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("Freeze.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("Freeze.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("Freeze.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isFreeze(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("Freeze.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("Freeze.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Freeze.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Freeze.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("Freeze.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("Freeze.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("Freeze.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("Freeze.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (e.getItemInHand().getType() == getItem().getType() && e.getItemInHand().hasItemMeta() && e.getItemInHand().getItemMeta().getDisplayName().equalsIgnoreCase(name) && e.getItemInHand().getItemMeta().hasLore()) {
            e.setCancelled(true);
        }
    }
    /*
     * Listener
     */
    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        if (isFreeze(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("Freeze.Lang.OnCooldown")) {
                    p.sendMessage(CC.translate(s).replace("%time%", Formatter.getRemaining(this.getRemaining(p.getUniqueId()), true, false)));
                }
                p.updateInventory();
                return;
            }
            if (!isEnabled()) {
                p.sendMessage(Vulcan.getInstance().getConfigService().ABILITY_DISABLED());
                return;
            }
            if(isInRegion(p)){
                p.sendMessage(Vulcan.getInstance().getConfigService().ON_ABILITY_USE_IN_REGION());
                return;
            }
            int dur;
            int amp;
            List<String> effects = Vulcan.getInstance().getConfig().getStringList("Freeze.Options.Effects");
            int radius = Vulcan.getInstance().getConfig().getInt("Freeze.Options.Radius");
            List<Entity> nearby = p.getNearbyEntities(radius, radius, radius);
            for (Entity entity : nearby) {
                if (entity instanceof Player) {
                    Player target = (Player) entity;
                    for (String po : effects) {
                        String[] inf = po.split(":");
                        if (inf.length == 3 && PotionEffectType.getByName(inf[0].toUpperCase()) != null) {
                            PotionEffectType pot = PotionEffectType.getByName(inf[0].toUpperCase());
                            try {
                                dur = Math.min(Integer.parseInt(inf[1]), Integer.MAX_VALUE);
                            } catch (NumberFormatException e1) {
                                dur = Integer.MAX_VALUE;
                            }
                            try {
                                amp = Math.min(Integer.parseInt(inf[2]), 255);
                            } catch (NumberFormatException e1) {
                                amp = 255;
                            }
                            if (!target.hasPotionEffect(pot)) {
                                PotionEffect effect = new PotionEffect(pot, dur * 20, amp - 1);
                                target.addPotionEffect(effect);
                                for (String toplayer : Vulcan.getInstance().getConfig().getStringList("Freeze.Lang.Player")) {
                                    p.sendMessage(CC.translate(toplayer));
                                }
                                for (String totarget : Vulcan.getInstance().getConfig().getStringList("Freeze.Lang.Target")) {
                                    target.sendMessage(CC.translate(totarget).replace("%player%", p.getDisplayName()));
                                }
                            } else {
                                target.hasPotionEffect(pot);
                                PotionEffect effect = new PotionEffect(pot, dur * 20, amp - 1);
                                target.addPotionEffect(effect);
                                for (String toplayer : Vulcan.getInstance().getConfig().getStringList("Freeze.Lang.Player")) {
                                    p.sendMessage(CC.translate(toplayer));
                                }
                                for (String totarget : Vulcan.getInstance().getConfig().getStringList("Freeze.Lang.Target")) {
                                    target.sendMessage(CC.translate(totarget).replace("%player%", p.getDisplayName()));
                                }
                            }
                        }
                    }

                }
            }
            setCooldown(p, p.getUniqueId());
            if (Vulcan.getInstance().getConfig().getBoolean("Freeze.Options.RemoveOnUse")) {
                if (p.getInventory().getItemInHand().getAmount() == 1) {
                    p.getInventory().setItemInHand(null);
                    return;
                }
                p.getInventory().getItemInHand().setAmount(p.getInventory().getItemInHand().getAmount() - 1);
            }
        }
    }
}
