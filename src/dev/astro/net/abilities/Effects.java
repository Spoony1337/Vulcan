package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
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

public class Effects extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public Effects() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("Effects.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("Effects.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("Effects.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isEffect(ItemStack itemStack) {
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
        if (Vulcan.getInstance().getConfig().getString("Effects.Item.Type").toUpperCase().equalsIgnoreCase("SNOWBALL")) {
            return new ItemBuilder(Material.SNOW_BALL)
                    .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Effects.Item.Name")))
                    .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Effects.Item.Lore"))).build();
        } else if (Vulcan.getInstance().getConfig().getString("Effects.Item.Type").toUpperCase().equalsIgnoreCase("EGG")) {
            return new ItemBuilder(Material.EGG)
                    .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("Effects.Item.Name")))
                    .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("Effects.Item.Lore"))).build();
        }
        return null;
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("Effects.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("Effects.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("Effects.Enabled", false);
    }

    /*
     * Cooldown
     */

    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("Effects.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }


    /*
     * Listener
     */
    @EventHandler
    public void onLaunch(ProjectileLaunchEvent e) {
        Projectile entity = e.getEntity();
        if (!(entity.getShooter() instanceof Player)) return;
        Player p = (Player) entity.getShooter();
        if (isEffect(p.getItemInHand())) {
            if ((p.getItemInHand() == null) || (p.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            entity.setMetadata("effects", new FixedMetadataValue(Vulcan.getInstance(), p.getUniqueId()));
            setCooldown(p, p.getUniqueId());
        }
    }

    @EventHandler
    public void hasCooldown(PlayerInteractEvent e) {
        Player p = e.getPlayer();
    
        if (isEffect(p.getItemInHand())) {
            if ((p.getItemInHand() == null) || p.getItemInHand().getType() != getItem().getType()) {
                return;
            }
            if (hasCooldown(p)) {
            	   e.setUseItemInHand(Event.Result.DENY);
                   for (String s : Vulcan.getInstance().getConfig().getStringList("Effects.Lang.OnCooldown")) {
                       p.sendMessage(CC.translate(s).replace("%time%", Formatter.getRemaining(this.getRemaining(p.getUniqueId()), true, false)));
                   }
                p.updateInventory();
                return;
            }
            if (!isEnabled()) {
            	   e.setUseItemInHand(Event.Result.DENY);
                p.sendMessage(Vulcan.getInstance().getConfigService().ABILITY_DISABLED());
                return;
            }
            if(isInRegion(p)){
            	   e.setUseItemInHand(Event.Result.DENY);
                p.sendMessage(Vulcan.getInstance().getConfigService().ON_ABILITY_USE_IN_REGION());
                return;
            }
        }
    }
    
    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        int dur;
        int amp;
        List<String> effects = Vulcan.getInstance().getConfig().getStringList("Effects.Options.Effects");
        if (e.getDamager() instanceof Projectile) {
            Projectile projectile = (Projectile) e.getDamager();
            if (!(projectile.getShooter() instanceof Player)) return;
            if (!projectile.hasMetadata("effects")) return;
            if (e.getEntity() instanceof Player) {
                Player damaged = (Player) e.getEntity();
                Player shooter = (Player) projectile.getShooter();
                if(isInRegion(damaged)){
                    shooter.sendMessage(Vulcan.getInstance().getConfigService().TARGET_IS_IN_REGION());
                    return;
                }
                for (String s : Vulcan.getInstance().getConfig().getStringList("Effects.Lang.OnUse.Player")) {
                    shooter.sendMessage(CC.translate(s).replace("%target%", damaged.getDisplayName()));
                }
                for (String s : Vulcan.getInstance().getConfig().getStringList("Effects.Lang.OnUse.Target")) {
                    damaged.sendMessage(CC.translate(s).replace("%player%", shooter.getDisplayName()));
                }
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
