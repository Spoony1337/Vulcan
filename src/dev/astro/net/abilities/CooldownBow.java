package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityShootBowEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class CooldownBow extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public CooldownBow() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("CooldownBow.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("CooldownBow.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("CooldownBow.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isCooldownBow(ItemStack itemStack) {
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
                .setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("CooldownBow.Item.Name")))
                .setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("CooldownBow.Item.Lore"))).build();
    }

    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("CooldownBow.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("CooldownBow.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("CooldownBow.Enabled", false);
    }

    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("CooldownBow.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
    @EventHandler
    public void onShoot(EntityShootBowEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        Player p = (Player) e.getEntity();
        if (isCooldownBow(p.getItemInHand())) {
            if ((p.getItemInHand() == null) || (p.getItemInHand().getType() != getItem().getType())) {
                return;
            }
            if (hasCooldown(p)) {
                e.setCancelled(true);
                for (String s : Vulcan.getInstance().getConfig().getStringList("CooldownBow.Lang.OnCooldown")) {
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
                e.getProjectile().setMetadata("cooldownbow", new FixedMetadataValue(Vulcan.getInstance(), p.getUniqueId()));
                setCooldown(p, p.getUniqueId());
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) return;
        if (!(e.getDamager() instanceof Arrow)) return;
        Player p = (Player) e.getEntity();
        Arrow arrow = (Arrow) e.getDamager();
        if (arrow.hasMetadata("cooldownbow")) {
            Player shooter = (Player) arrow.getShooter();
            for (String s : Vulcan.getInstance().getConfig().getStringList("CooldownBow.Lang.OnUse.Player")) {
                shooter.sendMessage(CC.translate(s).replace("%target%", p.getDisplayName()));
            }
            for (String s : Vulcan.getInstance().getConfig().getStringList("CooldownBow.Lang.OnUse.Target")) {
                p.sendMessage(CC.translate(s).replace("%player%", shooter.getDisplayName()));
            }
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), Vulcan.getInstance().getConfig().getString("CooldownBow.Options.Command").replace("%player%", p.getName()));
        }
    }
}
