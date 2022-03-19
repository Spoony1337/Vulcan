package dev.astro.net.commands.vulcan.argument;

import org.bukkit.command.*;
import org.bukkit.entity.Player;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.CommandArgument;
import dev.astro.net.utilities.chat.CC;

public class ReloadArgument extends CommandArgument
{
    public ReloadArgument() {
        super("reload", "Reload Vapor Config", "*");
    }
    
    @Override
    public String getUsage(String label) {
        return "/" + label + " reload";
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] argument) {
		Player player = (Player) sender;
        Vulcan.getInstance().getRegionsFile().load();
        Vulcan.getInstance().getConfigFile().load();
        player.sendMessage(CC.translate("&4&lVulcan &7has been &creloaded."));
        System.out.println("" + player.getName() + " has reloaded Vulcan configuration.");
		return false;
	}
}
