package dev.astro.net.menu;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.ArrayList;
import java.util.List;

public class AbilitiesMenuListener implements Listener {
    private Vulcan plugin;

    public AbilitiesMenuListener(Vulcan plugin) {
        this.plugin = plugin;
    }

    @EventHandler
    public void onClickMainInventory(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        Inventory holder = e.getInventory();
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getAbilitiesEditorInventory().getName())) {
            if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getAntiTrap().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openAntiTrapInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getAntiFalLBoots().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openAntiFallBootsInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getAutoBow().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openAutoBowInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getBedBomb().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openBedBombInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getBelchBomb().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openBelchBombInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getCobweb().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openCowbebInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getCocaine().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openCocaineInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getCooldownBow().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openCooldonwBowInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getEffects().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openEffectsInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getFireBall().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openFireBallInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getFreezeGun().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openFreezeGunInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getGrapplingHook().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openGrapplingHookInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getNinja().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openNinjaInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getPearlReset().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openPearlResetInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getRocketBoost().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openRocketBoostInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getStormBreaker().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openStormBreakerInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getSwapperHelmet().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openSwapperHelmetInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getSwitcher().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openSwitcherInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getViewSwitcher().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openViewSwitcherInventory(p);
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getFreeze().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openFreezeInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getGhost().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openGhostInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getPotionRefill().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openPotionRefillInventory(p);
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getFastPearl().getItem())) {
                e.setCancelled(true);
    
                return;
            }
            if (clickedItem.isSimilar(plugin.getAbilitiesManager().getInventorySwitcher().getItem())) {
                e.setCancelled(true);
                p.closeInventory();
                plugin.getAbilitiesMenu().openInventorySwitcherInventory(p);
                return;
            }
        }
    }

    @EventHandler
    public void onClickAbilityEditor(InventoryClickEvent e) {
        Player p = (Player) e.getWhoClicked();
        ItemStack clickedItem = e.getCurrentItem();
        Inventory holder = e.getInventory();
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getAntitrapInventory().getName())) {
            if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "AntiTrap Has Been Disabled!");
                plugin.getAbilitiesManager().getAntiTrap().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "AntiTrap Has Been Enabled!");
                plugin.getAbilitiesManager().getAntiTrap().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getAntifallboootsInventory().getName())) {
            if (clickedItem == null || clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "AntiFallBoots Has Been Disabled!");
                plugin.getAbilitiesManager().getAntiFalLBoots().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "AntiFallBoots Has Been Enabled!");
                plugin.getAbilitiesManager().getAntiFalLBoots().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getAutobowInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "AutoBow Has Been Disabled!");
                plugin.getAbilitiesManager().getAutoBow().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "AutoBow Has Been Enabled!");
                plugin.getAbilitiesManager().getAutoBow().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getBedbombInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "BedBomb Has Been Disabled!");
                plugin.getAbilitiesManager().getBedBomb().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "BedBomb Has Been Enabled!");
                plugin.getAbilitiesManager().getBedBomb().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getBelchbombInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "BelchBomb Has Been Disabled!");
                plugin.getAbilitiesManager().getBelchBomb().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "BelchBomb Has Been Enabled!");
                plugin.getAbilitiesManager().getBelchBomb().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getCobwebInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Cobweb Has Been Disabled!");
                plugin.getAbilitiesManager().getCobweb().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Cobweb Has Been Enabled!");
                plugin.getAbilitiesManager().getCobweb().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getCocaineInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Cocaine Has Been Disabled!");
                plugin.getAbilitiesManager().getCocaine().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Cocaine Has Been Enabled!");
                plugin.getAbilitiesManager().getCocaine().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getCooldownbowInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "CooldownBow Has Been Disabled!");
                plugin.getAbilitiesManager().getCooldownBow().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "CooldownBow Has Been Enabled!");
                plugin.getAbilitiesManager().getCooldownBow().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getEffectsInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Effects Has Been Disabled!");
                plugin.getAbilitiesManager().getEffects().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Effects Has Been Enabled!");
                plugin.getAbilitiesManager().getEffects().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getFireballInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "FireBall Has Been Disabled!");
                plugin.getAbilitiesManager().getFireBall().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "FireBall Has Been Enabled!");
                plugin.getAbilitiesManager().getFireBall().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getFreezegunInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "FreezeGun Has Been Disabled!");
                plugin.getAbilitiesManager().getFreezeGun().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "FreezeGun Has Been Enabled!");
                plugin.getAbilitiesManager().getFreezeGun().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getGrapplinghookInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "GrapplingHook Has Been Disabled!");
                plugin.getAbilitiesManager().getGrapplingHook().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "GrapplingHook Has Been Enabled!");
                plugin.getAbilitiesManager().getGrapplingHook().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getNinjaInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Ninja Has Been Disabled!");
                plugin.getAbilitiesManager().getNinja().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Ninja Has Been Enabled!");
                plugin.getAbilitiesManager().getNinja().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getPearlresetInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "PearlReset Has Been Disabled!");
                plugin.getAbilitiesManager().getPearlReset().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "PearlReset Has Been Enabled!");
                plugin.getAbilitiesManager().getPearlReset().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getRocketboostInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "RocketBoost Has Been Disabled!");
                plugin.getAbilitiesManager().getRocketBoost().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "RocketBoost Has Been Enabled!");
                plugin.getAbilitiesManager().getRocketBoost().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getStormbreakerInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "StormBreaker Has Been Disabled!");
                plugin.getAbilitiesManager().getStormBreaker().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "StormBreaker Has Been Enabled!");
                plugin.getAbilitiesManager().getStormBreaker().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getSwapperhelmetInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "SwapperHelmet Has Been Disabled!");
                plugin.getAbilitiesManager().getSwapperHelmet().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "SwapperHelmet Has Been Enabled!");
                plugin.getAbilitiesManager().getSwapperHelmet().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getSwitcherInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Switcher Has Been Disabled!");
                plugin.getAbilitiesManager().getSwitcher().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Switcher Has Been Enabled!");
                plugin.getAbilitiesManager().getSwitcher().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getViewswitcherInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "ViewSwitcher Has Been Disabled!");
                plugin.getAbilitiesManager().getViewSwitcher().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "ViewSwitcher Has Been Enabled!");
                plugin.getAbilitiesManager().getViewSwitcher().Enable();
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getFreezeInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Freeze Has Been Disabled!");
                plugin.getAbilitiesManager().getFreeze().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Freeze Has Been Enabled!");
                plugin.getAbilitiesManager().getFreeze().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getGhostInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Ghost Has Been Disabled!");
                plugin.getAbilitiesManager().getGhost().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Ghost Has Been Enabled!");
                plugin.getAbilitiesManager().getGhost().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getPotionrefillInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Potion Refill Has Been Disabled!");
                plugin.getAbilitiesManager().getPotionRefill().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Potion Refill Has Been Enabled!");
                plugin.getAbilitiesManager().getPotionRefill().Enable();
                return;
            }
        }
        if (holder.getName().equalsIgnoreCase(plugin.getAbilitiesMenu().getInventoryswitcherInventory().getName())) {
            if (clickedItem.getType() == Material.AIR || clickedItem.getType() == Material.STAINED_GLASS_PANE || e.getSlotType() == null) {
                e.setCancelled(true);
                return;
            }
            if (e.getClick().equals(ClickType.NUMBER_KEY) || e.getClick().equals(ClickType.CONTROL_DROP) || e.getClick().equals(ClickType.DROP)) {
                e.setCancelled(true);
                return;
            }
            if (clickedItem.getType() == Material.EMERALD_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Disable());
                p.sendMessage(ChatColor.RED + "Inventory Switcher Has Been Disabled!");
                plugin.getAbilitiesManager().getInventorySwitcher().Disable();
                return;
            }
            if (clickedItem.getType() == Material.REDSTONE_BLOCK) {
                e.setCancelled(true);
                holder.setItem(13, Enable());
                p.sendMessage(ChatColor.GREEN + "Inventory Switcher Has Been Enabled!");
                plugin.getAbilitiesManager().getInventorySwitcher().Enable();
                return;
            }
        }
    }

    private ItemStack Enable() {
        List<String> statuslore = new ArrayList<>();
        statuslore.add("&8&m------------------------------------------------");
        statuslore.add("&7- Click on an ability to enable/disable it.");
        statuslore.add(" ");
        statuslore.add("&7- Status: &aEnable");
        statuslore.add("&8&m------------------------------------------------");
        return new ItemBuilder(Material.EMERALD_BLOCK).setName(CC.translate("&6Status")).setLore(CC.translate(statuslore)).build();
    }

    private ItemStack Disable() {
        List<String> statuslore = new ArrayList<>();
        statuslore.add("&8&m------------------------------------------------");
        statuslore.add("&7- Click on an ability to enable/disable it.");
        statuslore.add(" ");
        statuslore.add("&7- Status: &cDisable");
        statuslore.add("&8&m------------------------------------------------");
        return new ItemBuilder(Material.REDSTONE_BLOCK).setName(CC.translate("&6Status")).setLore(CC.translate(statuslore)).build();
    }
}
