package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.entity.Snowball;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.ProjectileLaunchEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
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

public class FreezeGun extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public FreezeGun() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("FreezeGun.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("FreezeGun.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("FreezeGun.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isFreezeGun(ItemStack itemStack) {
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
                Material.matchMaterial(Vulcan.getInstance().getConfig().getString("FreezeGun.Item.Material")),
                (short) Vulcan.getInstance().getConfig().getInt("FreezeGun.Item.Data")))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("FreezeGun.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("FreezeGun.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("FreezeGun.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("FreezeGun.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("FreezeGun.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("FreezeGun.Lang.OnExpire")) {
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
        if (isFreezeGun(p.getItemInHand())) {
            if ((e.getItem() == null) || ((e.getAction() != Action.RIGHT_CLICK_AIR) && (e.getAction() != Action.RIGHT_CLICK_BLOCK)) || (e.getItem().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setUseItemInHand(Event.Result.DENY);
                for (String s : Vulcan.getInstance().getConfig().getStringList("FreezeGun.Lang.OnCooldown")) {
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
            p.launchProjectile(Snowball.class);
            p.updateInventory();
            setCooldown(p, p.getUniqueId());
        }

    }

    @EventHandler
    public void onLaunch(ProjectileLaunchEvent e) {
        if (!(e.getEntity().getShooter() instanceof Player)) {
            return;
        }
        Projectile entity = e.getEntity();
        Player p = (Player) entity.getShooter();
        if (isFreezeGun(p.getItemInHand())) {
            if (entity instanceof Snowball) {
                Snowball snowball = (Snowball) entity;
                snowball.setMetadata("freezegun", new FixedMetadataValue(Vulcan.getInstance(), p.getUniqueId()));
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        int dur;
        int amp;
        List<String> effects = Vulcan.getInstance().getConfig().getStringList("FreezeGun.Options.Effects");
        if (e.getDamager() instanceof Snowball && e.getEntity() instanceof Player) {
            Player damaged = (Player) e.getEntity();
            Snowball snowball = (Snowball) e.getDamager();
            if (!snowball.hasMetadata("freezegun")) {
                return;
            }
            if (snowball.getShooter() instanceof Player) {
                Player p = (Player) snowball.getShooter();
                damaged.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 2));
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
                        if (!damaged.hasPotionEffect(pot)) {
                            PotionEffect effect = new PotionEffect(pot, dur * 20, amp -1);
                            damaged.addPotionEffect(effect);
                            for (String toplayer : Vulcan.getInstance().getConfig().getStringList("FreezeGun.Lang.Player")) {
                                p.sendMessage(CC.translate(toplayer).replace("%target%", damaged.getDisplayName()));
                            }
                            for (String totarget : Vulcan.getInstance().getConfig().getStringList("FreezeGun.Lang.Target")) {
                                damaged.sendMessage(CC.translate(totarget).replace("%player%", p.getDisplayName()));
                            }
                        } else {
                            damaged.hasPotionEffect(pot);
                            PotionEffect effect = new PotionEffect(pot, dur * 20, amp -1);
                            damaged.addPotionEffect(effect);
                        }
                    }
                }
            }
        }
    }
}
