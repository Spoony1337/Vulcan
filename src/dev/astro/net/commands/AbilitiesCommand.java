package dev.astro.net.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.chat.CC;

import java.util.ArrayList;
import java.util.List;


public class AbilitiesCommand implements CommandExecutor, TabCompleter {

    private Vulcan plugin;

    public AbilitiesCommand(Vulcan plugin) {
        this.plugin = plugin;
    }

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!sender.hasPermission("abilities.command.use")) {
            sender.sendMessage(plugin.getConfigService().NO_PERMISSION());
            return true;
        }
        if (args.length < 1) {
            sendHelp(sender, label);
            return true;
        }
        switch (args[0]) {
            case "give":
                if (args.length < 4) {
                    sender.sendMessage("§cUsage: /" + label + " give <player> <abilitiy/all> <amount>");
                    return true;
                }
                Player target = Bukkit.getPlayer(args[1]);
                if (target == null) {
                    sender.sendMessage(plugin.getConfigService().NO_PLAYER_FOUND().replace("%player%", args[1]));
                    return true;
                }
                try {
                    Integer.parseInt(args[3]);
                } catch (Throwable e) {
                    sender.sendMessage("§cUsage: / " + label + " give <player> <abilitiy/all> <amount>");
                    return true;
                }

                switch (args[2].toLowerCase()) {
                    case "antitrap":
                        ItemStack antitrap = plugin.getAbilitiesManager().getAntiTrap().getItem();
                        antitrap.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(antitrap);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "antifallboots":
                        ItemStack antifallboots = plugin.getAbilitiesManager().getAntiFalLBoots().getItem();
                        antifallboots.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(antifallboots);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "autobow":
                        ItemStack autobow = plugin.getAbilitiesManager().getAutoBow().getItem();
                        autobow.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(autobow);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "bedbomb":
                        ItemStack bedbomb = plugin.getAbilitiesManager().getBedBomb().getItem();
                        bedbomb.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(bedbomb);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "belchbomb":
                        ItemStack belchbomb = plugin.getAbilitiesManager().getBelchBomb().getItem();
                        belchbomb.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(belchbomb);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "cobweb":
                        ItemStack cobweb = plugin.getAbilitiesManager().getCobweb().getItem();
                        cobweb.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(cobweb);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "cocaine":
                        ItemStack cocaine = plugin.getAbilitiesManager().getCocaine().getItem();
                        cocaine.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(cocaine);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "cooldownbow":
                        ItemStack cooldownbow = plugin.getAbilitiesManager().getCooldownBow().getItem();
                        cooldownbow.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(cooldownbow);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "effects":
                        ItemStack effects = plugin.getAbilitiesManager().getEffects().getItem();
                        effects.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(effects);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "fireball":
                        ItemStack fireball = plugin.getAbilitiesManager().getFireBall().getItem();
                        fireball.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(fireball);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "freezegun":
                        ItemStack freezegun = plugin.getAbilitiesManager().getFreezeGun().getItem();
                        freezegun.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(freezegun);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "grapplinghook":
                        ItemStack grapplinghook = plugin.getAbilitiesManager().getGrapplingHook().getItem();
                        grapplinghook.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(grapplinghook);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "ninja":
                        ItemStack ninja = plugin.getAbilitiesManager().getNinja().getItem();
                        ninja.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(ninja);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "pearlreset":
                        ItemStack pearlreset = plugin.getAbilitiesManager().getPearlReset().getItem();
                        pearlreset.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(pearlreset);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "rocketboost":
                        ItemStack rocketboost = plugin.getAbilitiesManager().getRocketBoost().getItem();
                        rocketboost.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(rocketboost);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "stormbreaker":
                        ItemStack stormbreaker = plugin.getAbilitiesManager().getStormBreaker().getItem();
                        stormbreaker.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(stormbreaker);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "swapperhelmet":
                        ItemStack swapperhelmet = plugin.getAbilitiesManager().getSwapperHelmet().getItem();
                        swapperhelmet.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(swapperhelmet);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "switcher":
                        ItemStack switcher = plugin.getAbilitiesManager().getSwitcher().getItem();
                        switcher.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(switcher);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "viewswitcher":
                        ItemStack viewswitcher = plugin.getAbilitiesManager().getViewSwitcher().getItem();
                        viewswitcher.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(viewswitcher);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "freeze":
                        ItemStack freeze = plugin.getAbilitiesManager().getFreeze().getItem();
                        freeze.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(freeze);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "ghost":
                        ItemStack ghost = plugin.getAbilitiesManager().getGhost().getItem();
                        ghost.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(ghost);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "potionrefill":
                        ItemStack potionrefill = plugin.getAbilitiesManager().getPotionRefill().getItem();
                        potionrefill.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(potionrefill);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "inventoryswitcher":
                        ItemStack inventoryswitcher = plugin.getAbilitiesManager().getInventorySwitcher().getItem();
                        inventoryswitcher.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(inventoryswitcher);
                        sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Sender").replace("%target%", target.getDisplayName())));
                        target.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.Give.Target").replace("%sender%", ((Player) sender).getDisplayName())));
                        break;
                    case "all":
                        ItemStack antitrap1 = plugin.getAbilitiesManager().getAntiTrap().getItem();
                        antitrap1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(antitrap1);
                        
                        ItemStack antifallboots1 = plugin.getAbilitiesManager().getAntiFalLBoots().getItem();
                        antifallboots1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(antifallboots1);
                 
                        ItemStack autobow1 = plugin.getAbilitiesManager().getAutoBow().getItem();
                        autobow1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(autobow1);
                
                        ItemStack bedbomb1 = plugin.getAbilitiesManager().getBedBomb().getItem();
                        bedbomb1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(bedbomb1);
                
                        ItemStack belchbomb1 = plugin.getAbilitiesManager().getBelchBomb().getItem();
                        belchbomb1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(belchbomb1);
               
                        ItemStack cobweb1 = plugin.getAbilitiesManager().getCobweb().getItem();
                        cobweb1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(cobweb1);
              
                        ItemStack cocaine1 = plugin.getAbilitiesManager().getCocaine().getItem();
                        cocaine1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(cocaine1);
               
                        ItemStack cooldownbow1 = plugin.getAbilitiesManager().getCooldownBow().getItem();
                        cooldownbow1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(cooldownbow1);
              
                        ItemStack effects1 = plugin.getAbilitiesManager().getEffects().getItem();
                        effects1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(effects1);
               
                        ItemStack fireball1 = plugin.getAbilitiesManager().getFireBall().getItem();
                        fireball1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(fireball1);
                 
                        ItemStack freezegun1 = plugin.getAbilitiesManager().getFreezeGun().getItem();
                        freezegun1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(freezegun1);
               
                        ItemStack grapplinghook1 = plugin.getAbilitiesManager().getGrapplingHook().getItem();
                        grapplinghook1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(grapplinghook1);
          
                        ItemStack ninja1 = plugin.getAbilitiesManager().getNinja().getItem();
                        ninja1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(ninja1);
               
                        ItemStack pearlreset1 = plugin.getAbilitiesManager().getPearlReset().getItem();
                        pearlreset1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(pearlreset1);
               
                        ItemStack rocketboost1 = plugin.getAbilitiesManager().getRocketBoost().getItem();
                        rocketboost1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(rocketboost1);
              
                        ItemStack stormbreaker1 = plugin.getAbilitiesManager().getStormBreaker().getItem();
                        stormbreaker1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(stormbreaker1);
                   
                        ItemStack swapperhelmet1 = plugin.getAbilitiesManager().getSwapperHelmet().getItem();
                        swapperhelmet1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(swapperhelmet1);
             
                        ItemStack switcher1 = plugin.getAbilitiesManager().getSwitcher().getItem();
                        switcher1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(switcher1);
            
                        ItemStack viewswitcher1 = plugin.getAbilitiesManager().getViewSwitcher().getItem();
                        viewswitcher1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(viewswitcher1);
        
                        ItemStack freeze1 = plugin.getAbilitiesManager().getFreeze().getItem();
                        freeze1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(freeze1);
   
                        ItemStack ghost1 = plugin.getAbilitiesManager().getGhost().getItem();
                        ghost1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(ghost1);

                        ItemStack potionrefill1 = plugin.getAbilitiesManager().getPotionRefill().getItem();
                        potionrefill1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(potionrefill1);
      
                        ItemStack inventoryswitcher1 = plugin.getAbilitiesManager().getInventorySwitcher().getItem();
                        inventoryswitcher1.setAmount(Integer.parseInt(args[3]));
                        target.getInventory().addItem(inventoryswitcher1);
  
                        break;
                    default:
                        sender.sendMessage("§cUsage: /" + label + " give <player> <abilitiy> <amount>");
                        return true;
                }
                break;
            case "cleartimer":
                if (args.length < 2) {
                    sender.sendMessage("§cUsage: /" + label + " cleartimer <player> <abilitiy>");
                    return true;
                }
                Player target2 = Bukkit.getPlayer(args[1]);
                if (target2 == null) {
                    sender.sendMessage(plugin.getConfigService().NO_PLAYER_FOUND().replace("%player%", args[1]));
                    return true;
                }

                switch (args[2].toLowerCase()) {
                    case "antitrap":
                        if (plugin.getAbilitiesManager().getAntiTrap().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getAntiTrap().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getAntiTrap().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getAntiTrap().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getAntiTrap().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "autobow":
                        if (plugin.getAbilitiesManager().getAutoBow().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getAutoBow().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getAutoBow().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getAutoBow().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getAutoBow().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "bedbomb":
                        if (plugin.getAbilitiesManager().getBedBomb().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getBedBomb().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getBedBomb().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getBedBomb().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getBedBomb().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "belchbomb":
                        if (plugin.getAbilitiesManager().getBelchBomb().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getBelchBomb().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getBelchBomb().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getBelchBomb().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getBelchBomb().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "cobweb":
                        if (plugin.getAbilitiesManager().getCobweb().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getCobweb().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCobweb().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCobweb().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCobweb().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "cocaine":
                        if (plugin.getAbilitiesManager().getCocaine().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getCocaine().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCocaine().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCocaine().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCocaine().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "cooldownbow":
                        if (plugin.getAbilitiesManager().getCooldownBow().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getCooldownBow().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCooldownBow().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCooldownBow().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getCooldownBow().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "effects":
                        if (plugin.getAbilitiesManager().getEffects().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getEffects().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getEffects().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getEffects().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getEffects().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "fireball":
                        if (plugin.getAbilitiesManager().getFireBall().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getFireBall().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFireBall().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFireBall().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFireBall().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "freezegun":
                        if (plugin.getAbilitiesManager().getFreezeGun().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getFreezeGun().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFreezeGun().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFreezeGun().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFreezeGun().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "grapplinghook":
                        if (plugin.getAbilitiesManager().getGrapplingHook().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getGrapplingHook().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getGrapplingHook().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getGrapplingHook().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getGrapplingHook().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "ninja":
                        if (plugin.getAbilitiesManager().getNinja().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getNinja().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getNinja().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getNinja().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getNinja().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "pearlreset":
                        if (plugin.getAbilitiesManager().getPearlReset().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getPearlReset().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getPearlReset().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getPearlReset().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getPearlReset().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "rocketboost":
                        if (plugin.getAbilitiesManager().getRocketBoost().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getRocketBoost().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getRocketBoost().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getRocketBoost().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getRocketBoost().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "stormbreaker":
                        if (plugin.getAbilitiesManager().getStormBreaker().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getStormBreaker().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getStormBreaker().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getStormBreaker().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getStormBreaker().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "swapperhelmet":
                        if (plugin.getAbilitiesManager().getSwapperHelmet().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getSwapperHelmet().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getSwapperHelmet().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getSwapperHelmet().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getSwapperHelmet().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "switcher":
                        if (plugin.getAbilitiesManager().getSwitcher().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getSwitcher().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getSwitcher().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getSwitcher().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getSwitcher().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "viewswitcher":
                        if (plugin.getAbilitiesManager().getViewSwitcher().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getViewSwitcher().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getViewSwitcher().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getViewSwitcher().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getViewSwitcher().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "freeze":
                        if (plugin.getAbilitiesManager().getFreeze().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getFreeze().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFreeze().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFreeze().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getFreeze().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "ghost":
                        if (plugin.getAbilitiesManager().getGhost().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getGhost().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getGhost().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getGhost().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getGhost().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "potionrefill":
                        if (plugin.getAbilitiesManager().getPotionRefill().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getPotionRefill().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getPotionRefill().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getPotionRefill().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getPotionRefill().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    case "inventoryswitcher":
                        if (plugin.getAbilitiesManager().getInventorySwitcher().hasCooldown(target2)) {
                            plugin.getAbilitiesManager().getInventorySwitcher().clearCooldown(target2);
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Sender").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getInventorySwitcher().getItem().getItemMeta().getDisplayName())));
                            target2.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.Target").replace("%sender%", ((Player) sender).getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getInventorySwitcher().getItem().getItemMeta().getDisplayName())));
                        } else {
                            sender.sendMessage(CC.translate(plugin.getConfig().getString("Messages.Commands.ClearTimer.NoHasCooldown").replace("%target%", target2.getDisplayName()).replace("%timer%", plugin.getAbilitiesManager().getInventorySwitcher().getItem().getItemMeta().getDisplayName())));
                        }
                        break;
                    default:
                        sender.sendMessage("§cUsage: /" + label + " cleartimer <player> <abilitiy>");
                        return true;
                }
                break;
            case "region":
                if (!(sender instanceof Player)) {
                    sender.sendMessage("§4Only For Players");
                    return true;
                }

                if(args.length <2){
                    sender.sendMessage(CC.translate("&e/" + label + " region create <name> &7- create a new region &4(abilities don't work on regions)"));
                    sender.sendMessage(CC.translate("&e/" + label + " region delete <name> &7- delete a region"));
                    sender.sendMessage(CC.translate("&e/" + label + " region list &7- show list of all regions"));
                }
                switch (args[1]) {
                    case "create":
                        if (args.length < 3) {
                            sender.sendMessage(CC.translate("&cUsage: /" + label + " region create <name>"));
                            return true;
                        }
                        plugin.getRegionsManager().createRegion(args[2], ((Player) sender));
                        break;
                    case "delete":
                        if (args.length < 3) {
                            sender.sendMessage(CC.translate("&cUsage: /" + label + " regions delete <name>"));
                            return true;
                        }
                        plugin.getRegionsManager().deleteRegion(args[2], ((Player) sender));
                        break;
                    case "list":
                        sender.sendMessage(CC.translate("&eList of all Regions: &6" + plugin.getRegionsManager().getRegionList().toString().replace("[", "").replace("]", "")));
                        break;
                    default:
                        sendHelp(sender, label);
                }
                break;
            case "list":
                sendAbilitiesList(sender);
                break;
            case "manage":
                if (sender instanceof Player) {
                    plugin.getAbilitiesMenu().openAbilitiesEditor((Player) sender);
                } else {
                    sender.sendMessage("§4Only For Players");
                }
                break;
            case "help":
            default:
                sendHelp(sender, label);
        }
        return false;
    }

    private void sendHelp(CommandSender sender, String label) {
        String[] helpMessage = new String[]{
                CC.translate(" "),
                CC.translate("&6&l" + label + " Arguments"),
                CC.translate(" "),
                CC.translate("&e/" + label + " give <player> <ability/all> <amount> &7- Give to a player ability item"),
                CC.translate("&e/" + label + " manage &7- Open gui to edit a ability"),
                CC.translate("&e/" + label + " list &7- Show list of all abilities"),
                CC.translate("&e/" + label + " cleartimer <player> <ability> &7- Clear player cooldown"),
                CC.translate(" "),
                CC.translate("&e/" + label + " region create <name> &7- Create a new region &4(Abilities don't work on regions)"),
                CC.translate("&e/" + label + " region delete <name> &7- Delete a region"),
                CC.translate("&e/" + label + " region list &7- Show list of all regions"),
                CC.translate(" "),
        };
        sender.sendMessage(helpMessage);
    }

    private void sendAbilitiesList(CommandSender sender) {
        for (String messages : plugin.getConfig().getStringList("Messages.AbilitiesList.Message")) {
            sender.sendMessage(CC.translate(messages)
                    .replace("%antitrap_status%", (plugin.getAbilitiesManager().getAntiTrap().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%antifallboots_status%", (plugin.getAbilitiesManager().getAntiFalLBoots().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%autobow_status%", (plugin.getAbilitiesManager().getAutoBow().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%bedbomb_status%", (plugin.getAbilitiesManager().getBedBomb().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%belchbomb_status%", (plugin.getAbilitiesManager().getBelchBomb().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%cobweb_status%", (plugin.getAbilitiesManager().getCobweb().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%cocaine_status%", (plugin.getAbilitiesManager().getCocaine().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%cooldownbow_status%", (plugin.getAbilitiesManager().getCooldownBow().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%effects_status%", (plugin.getAbilitiesManager().getEffects().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%fireball_status%", (plugin.getAbilitiesManager().getFireBall().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%freezegun_status%", (plugin.getAbilitiesManager().getFreezeGun().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%grapplinghook_status%", (plugin.getAbilitiesManager().getGrapplingHook().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%ninja_status%", (plugin.getAbilitiesManager().getNinja().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%pearlreset_status%", (plugin.getAbilitiesManager().getPearlReset().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%rocketboost_status%", (plugin.getAbilitiesManager().getRocketBoost().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%stormbreaker_status%", (plugin.getAbilitiesManager().getStormBreaker().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%swapperhelmet_status%", (plugin.getAbilitiesManager().getSwapperHelmet().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%switcher_status%", (plugin.getAbilitiesManager().getSwitcher().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%freeze_status%", (plugin.getAbilitiesManager().getFreeze().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%ghost_status%", (plugin.getAbilitiesManager().getGhost().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%potionrefill_status%", (plugin.getAbilitiesManager().getPotionRefill().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%inventoryswitcher_status%", (plugin.getAbilitiesManager().getInventorySwitcher().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled"))))
                    .replace("%viewswitcher_status%", (plugin.getAbilitiesManager().getViewSwitcher().isEnabled() ? CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Enabled")) : CC.translate(plugin.getConfig().getString("Messages.AbilitiesList.Status.Disabled")))));

        }
    }


    @Override
    public List<String> onTabComplete(CommandSender sender, Command command, String alias, String[] args) {
        if (args[0].equalsIgnoreCase("give")) {
            if (args.length == 2) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (Player player : players) {
                    playerNames.add(player.getName());
                }

                return playerNames;
            } else if (args.length == 3) {
                List<String> arguments = new ArrayList<>();
                arguments.add("antifallboots");
                arguments.add("antitrap");
                arguments.add("autobow");
                arguments.add("bedbomb");
                arguments.add("belchbomb");
                arguments.add("cobweb");
                arguments.add("cocaine");
                arguments.add("cooldownbow");
                arguments.add("effects");
                arguments.add("fastpearl");
                arguments.add("fireball");
                arguments.add("freeze");
                arguments.add("freezegun");
                arguments.add("ghost");
                arguments.add("grapplinghook");
                arguments.add("inventoryswitcher");
                arguments.add("ninja");
                arguments.add("pearlreset");
                arguments.add("potionrefill");
                arguments.add("rocketboost");
                arguments.add("stormbreaker");
                arguments.add("switcher");
                arguments.add("swapperhelmet");
                arguments.add("viewswitcher");
                return arguments;
            }
        } else if (args[0].equalsIgnoreCase("cleartimer")) {
            if (args.length == 2) {
                List<String> playerNames = new ArrayList<>();
                Player[] players = new Player[Bukkit.getServer().getOnlinePlayers().size()];
                Bukkit.getServer().getOnlinePlayers().toArray(players);
                for (Player player : players) {
                    playerNames.add(player.getName());
                }
                return playerNames;
            } else if (args.length == 3) {
                List<String> arguments = new ArrayList<>();
                arguments.add("antifallboots");
                arguments.add("antitrap");
                arguments.add("autobow");
                arguments.add("bedbomb");
                arguments.add("belchbomb");
                arguments.add("cobweb");
                arguments.add("cocaine");
                arguments.add("cooldownbow");
                arguments.add("effects");
                arguments.add("fastpearl");
                arguments.add("fireball");
                arguments.add("freeze");
                arguments.add("freezegun");
                arguments.add("ghost");
                arguments.add("grapplinghook");
                arguments.add("inventoryswitcher");
                arguments.add("ninja");
                arguments.add("pearlreset");
                arguments.add("potionrefill");
                arguments.add("rocketboost");
                arguments.add("stormbreaker");
                arguments.add("switcher");
                arguments.add("swapperhelmet");
                arguments.add("viewswitcher");
                return arguments;
            }
        }
        return null;

    }
}
