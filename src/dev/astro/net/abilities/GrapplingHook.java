package dev.astro.net.abilities;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.util.Vector;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.Ability;
import dev.astro.net.utilities.Formatter;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class GrapplingHook extends Ability implements Listener {

    private String name;
    private List<String> lore;

    public GrapplingHook() {
        super(TimeUnit.SECONDS.toMillis(Vulcan.getInstance().getConfig().getInt("GrapplingHook.Options.Cooldown")));
        this.name = CC.translate(Vulcan.getInstance().getConfig().getString("GrapplingHook.Item.Name"));
        this.lore = CC.translate(Vulcan.getInstance().getConfig().getStringList("GrapplingHook.Item.Lore"));
    }

    /*
     * Item
     */
    private boolean isGrapplingHook(ItemStack itemStack) {
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
        return new ItemBuilder(Material.FISHING_ROD).setAmount(1).setName(CC.translate(Vulcan.getInstance().getConfig().getString("GrapplingHook.Item.Name"))).setLore(CC.translate(Vulcan.getInstance().getConfig().getStringList("GrapplingHook.Item.Lore"))).build();
    }
    public Boolean isEnabled() {
        return Vulcan.getInstance().getConfig().getBoolean("GrapplingHook.Enabled");
    }

    public void Enable() {
        Vulcan.getInstance().getConfig().set("GrapplingHook.Enabled", true);
    }

    public void Disable() {
        Vulcan.getInstance().getConfig().set("GrapplingHook.Enabled", false);
    }


    /*
     * Cooldown
     */
    @Override
    public void onExpire(UUID userUUID) {
        super.onExpire(userUUID);
        Player p = Bukkit.getPlayer(userUUID);
        if (p != null) {
            for (String s : Vulcan.getInstance().getConfig().getStringList("GrapplingHook.Lang.OnExpire")) {
                p.sendMessage(CC.translate(s));
            }
        }
    }

    /*
     * Listener
     */
	
	@EventHandler
	public void onInteract(PlayerInteractEvent event) {
	      Player p = event.getPlayer();
		if (event.getItem() == null || (event.getAction() != Action.RIGHT_CLICK_AIR && event.getAction() != Action.RIGHT_CLICK_BLOCK) || event.getItem().getType() != Material.FISHING_ROD) {
			return;
		}
	     if (isGrapplingHook(p.getItemInHand())) {
	    	if (hasCooldown(p)) {
			 event.setCancelled(true);
		     for (String s : Vulcan.getInstance().getConfig().getStringList("GrapplingHook.Lang.OnCooldown")) {
		    	 event.getPlayer().sendMessage(CC.translate(s).replace("%time%", Formatter.getRemaining(this.getRemaining(p.getUniqueId()), true, false)));
		     }
			event.getPlayer().updateInventory();
	    	}
	     }
	}

	
	public void pullPlayerSlightly(Player p, Location loc) {
		if (loc.getY() > p.getLocation().getY()) {
			p.setVelocity(new Vector(0.0, 0.25, 0.0));
			return;
		}
		Location playerLoc = p.getLocation();
		Vector vector = loc.toVector().subtract(playerLoc.toVector());
		p.setVelocity(vector);
	}
	
    @EventHandler
    public void onFish(PlayerFishEvent e) {
        Player p = e.getPlayer();
		Location hookLoc = e.getHook().getLocation();
		
		
        hookLoc.getBlock().getLocation().setY(hookLoc.getBlock().getLocation().getY() - 1.0);
   
        if (isGrapplingHook(p.getItemInHand()) && ((e.getState() == PlayerFishEvent.State.FAILED_ATTEMPT) || e.getState() == PlayerFishEvent.State.IN_GROUND)) {
            if (((p.getItemInHand()) == null) || (p.getItemInHand().getType() != getItem().getType())) {
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
    
            Location loc = p.getLocation();
            hookLoc.setY(hookLoc.getY() - 3.0);
            if ((loc.getBlockX() != hookLoc.getBlockX() && loc.getBlockZ() != hookLoc.getBlockZ() && hookLoc.getBlock().getType() != Material.AIR && loc.getBlock().getType() != Material.STATIONARY_WATER) || loc.getBlock().getType() != Material.AIR || e.getState() == PlayerFishEvent.State.IN_GROUND) {
                double kyori = hookLoc.distance(loc);
                double y3 = hookLoc.getY();
                hookLoc.setY(y3 + 6.0);
                Vector vec = loc.toVector();
                Vector vec2 = hookLoc.toVector();
                p.setVelocity(vec2.subtract(vec).normalize().multiply(kyori / 4.5)); 
                setCooldown(p, p.getUniqueId());
            } else {
                if (hasCooldown(p)) {
                    clearCooldown(p);
                }
            }
        }
    }
}


