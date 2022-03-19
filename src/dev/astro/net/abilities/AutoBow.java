package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class AutoBow extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public AutoBow() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("AutoBow.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("AutoBow.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("AutoBow.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isAutoBow(ItemStack itemStack) {
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
        return new ItemBuilder(new ItemStack(Material.BOW))
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("AutoBow.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("AutoBow.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("AutoBow.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("AutoBow.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("AutoBow.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("AutoBow.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void eventArrowFired(EntityShootBowEvent e) {
        Player p = (Player) e.getEntity();
        if (isAutoBow(p.getItemInHand())) {
            if ((p.getItemInHand() == null) || (p.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setCancelled(true);
                for (String s : Vulcan.getInstance().getConfig().getStringList("AutoBow.Lang.OnCooldown")) {
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

            if (e.getEntity() instanceof Player && e.getProjectile() instanceof Arrow) {
                double minAngle = 6.283185307179586;
                Player minEntity = null;
                for (Entity entity : p.getNearbyEntities(64.0, 64.0, 64.0)) {
                    if (p.hasLineOfSight(entity) && entity instanceof Player && !entity.isDead()) {
                        Vector toTarget = entity.getLocation().toVector().clone().subtract(p.getLocation().toVector());
                        double angle = e.getProjectile().getVelocity().angle(toTarget);
                        if (angle >= minAngle) {
                            continue;
                        }
                        minAngle = angle;
                        minEntity = (Player) entity;
                    }
                }
                setCooldown(p, p.getUniqueId());
                if (minEntity != null) {
                    new AutoBowTask((Arrow) e.getProjectile(), minEntity);
                }
            }
        }
    }

    private static class AutoBowTask extends BukkitRunnable {
        private static double MaxRotationAngle = 0.12;
        private Arrow arrow;
        private LivingEntity target;

        public AutoBowTask(Arrow arrow, LivingEntity target) {
            this.arrow = arrow;
            this.target = target;
            this.runTaskTimer(Vulcan.getInstance(), 1L, 1L);
        }

        public void run() {
            double speed = arrow.getVelocity().length();
            if (arrow.isOnGround() || arrow.isDead() || target.isDead()) {
                cancel();
                return;
            }
            Vector toTarget = target.getLocation().clone().add(new Vector(0.0, 0.5, 0.0)).subtract(arrow.getLocation()).toVector();
            Vector dirVelocity = arrow.getVelocity().clone().normalize();
            Vector dirToTarget = toTarget.clone().normalize();
            double angle = dirVelocity.angle(dirToTarget);
            double newSpeed = 0.9 * speed + 0.13999999999999999;
            if (target instanceof Player && arrow.getLocation().distance(target.getLocation()) < 8.0) {
                Player p = (Player) target;
                if (p.isBlocking()) {
                    newSpeed = speed * 0.6;
                }
            }
            Vector newVelocity;
            if (angle < MaxRotationAngle) {
                newVelocity = dirVelocity.clone().multiply(newSpeed);
            } else {
                Vector newDir = dirVelocity.clone().multiply((angle - MaxRotationAngle) / angle).add(dirToTarget.clone().multiply(MaxRotationAngle / angle));
                newDir.normalize();
                newVelocity = newDir.clone().multiply(newSpeed);
            }
            arrow.setVelocity(newVelocity.add(new Vector(0.0, 0.03, 0.0)));
            arrow.getWorld().playEffect(arrow.getLocation(), Effect.SMOKE, 0);
        }
    }
}
