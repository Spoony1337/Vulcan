package dev.astro.net.commands.vulcan.argument;

import org.bukkit.command.*;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.BukkitUtils;
import dev.astro.net.utilities.CommandArgument;
import dev.astro.net.utilities.chat.CC;

public class VersionArgument extends CommandArgument
{
    public VersionArgument() {
        super("version", "See Vapor Version");
    }
    
    @Override
    public String getUsage(String label) {
        return "/" + label + " version";
    }
    
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] argument) {
        if (argument.length == 1) {
            sendInfo(sender); 
        }
        return false;
    }
    public static void sendInfo(CommandSender sender) {
        sender.sendMessage(CC.translate("&7" + BukkitUtils.STRAIGHT_LINE_DEFAULT));
        sender.sendMessage(CC.translate("&4&lVulcan"));
        sender.sendMessage(CC.translate("&cAuthor &7- Daaz x Mustang"));
        sender.sendMessage(CC.translate("&cDiscord &7- astrx.us/discord"));
        sender.sendMessage(CC.translate("&cWebsite &7- www.astrx.us"));
        sender.sendMessage("");
        sender.sendMessage(CC.translate("&cVersion &7- " + Vulcan.getInstance().getDescription().getVersion()));
        sender.sendMessage(CC.translate("&cLast Update &7- 28/12/20"));
        sender.sendMessage(CC.translate("&7" + BukkitUtils.STRAIGHT_LINE_DEFAULT));
 
    }

}
