package dev.astro.net.managers;

import com.sk89q.worldedit.bukkit.selections.Selection;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.chat.CC;

import org.bukkit.entity.Player;

import java.util.Set;

public class RegionsManager {

    public void createRegion(String name, Player p ){
        if (Vulcan.getInstance().getRegionsFile().getConfigurationSection("Regions." + name) != null) {
            p.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("Messages.Region.RegionCreate.AlreadyExist").replace("%name%", name)));
            return;
        }
        Selection sel = Vulcan.getInstance().getWorldEdit().getSelection(p);
        if (sel == null) {
            p.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("Messages.Region.RegionCreate.MakeASelection")));
            return;
        }
        p.performCommand("/expand vert");
        Vulcan.getInstance().getRegionsFile().set("Regions." + name + ".world", sel.getMaximumPoint().getWorld().getName());
        Vulcan.getInstance().getRegionsFile().set("Regions." + name + ".A.x", sel.getMaximumPoint().getX() + 1);
        Vulcan.getInstance().getRegionsFile().set("Regions." + name + ".A.y", sel.getMaximumPoint().getY());
        Vulcan.getInstance().getRegionsFile().set("Regions." + name + ".A.z", sel.getMaximumPoint().getZ() + 1);
        Vulcan.getInstance().getRegionsFile().set("Regions." + name + ".B.x", sel.getMinimumPoint().getX());
        Vulcan.getInstance().getRegionsFile().set("Regions." + name + ".B.y", sel.getMinimumPoint().getY());
        Vulcan.getInstance().getRegionsFile().set("Regions." + name + ".B.z", sel.getMinimumPoint().getZ());
        Vulcan.getInstance().getRegionsFile().save();
        Vulcan.getInstance().getRegionsFile().load();
        p.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("Messages.Region.RegionCreate.Created").replace("%name%", name)));
    }
    public void deleteRegion(String name, Player p){
        if (Vulcan.getInstance().getRegionsFile().getConfigurationSection("Regions." + name) == null) {
            p.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("Messages.Region.RegionDelete.DoesNotExist").replace("%name%", name)));
            return;
        }
        Vulcan.getInstance().getRegionsFile().set("Regions." + name, null);
        Vulcan.getInstance().getRegionsFile().save();
        Vulcan.getInstance().getRegionsFile().load();
        p.sendMessage(CC.translate(Vulcan.getInstance().getConfig().getString("Messages.Region.RegionDelete.Deleted").replace("%name%", name)));
    }
    public Set<String> getRegionList() {

        return Vulcan.getInstance().getRegionsFile().getConfigurationSection("Regions").getKeys(false);
    }
}
