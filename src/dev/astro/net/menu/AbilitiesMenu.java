package dev.astro.net.menu;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import dev.astro.net.Vulcan;
import dev.astro.net.utilities.ItemBuilder;
import dev.astro.net.utilities.chat.CC;

import java.util.ArrayList;
import java.util.List;

public class AbilitiesMenu {
	

    private Vulcan plugin;
    
    private Inventory abilitiesEditorInventory;
    private Inventory antitrapInventory;
    private Inventory antifallboootsInventory;
    private Inventory autobowInventory;
    private Inventory bedbombInventory;
    private Inventory belchbombInventory;
    private Inventory cobwebInventory;
    private Inventory cocaineInventory;
    private Inventory cooldownbowInventory;
    private Inventory effectsInventory;
    private Inventory fireballInventory;
    private Inventory freezegunInventory;
    private Inventory grapplinghookInventory;
    private Inventory ninjaInventory;
    private Inventory pearlresetInventory;
    private Inventory rocketboostInventory;
    private Inventory stormbreakerInventory;
    private Inventory swapperhelmetInventory;
    private Inventory switcherInventory;
    private Inventory viewswitcherInventory;
    private Inventory freezeInventory;
    private Inventory ghostInventory;
    private Inventory potionrefillInventory;
    private Inventory inventoryswitcherInventory;
    private Inventory enderpearl;
    
    public AbilitiesMenu(Vulcan plugin) {
        this.plugin = plugin;


        this.abilitiesEditorInventory = Bukkit.createInventory(null, 54, "Abilities Editor Menu");
        //Editors
        this.antitrapInventory = Bukkit.createInventory(null, 27, "AntiBuild Editor");
        this.antifallboootsInventory = Bukkit.createInventory(null, 27, "antifallbooots Editor");
        this.autobowInventory = Bukkit.createInventory(null, 27, "AutoBow Editor");
        this.bedbombInventory = Bukkit.createInventory(null, 27, "BedBomb Editor");
        this.belchbombInventory = Bukkit.createInventory(null, 27, "BelchBomb Editor");
        this.cobwebInventory = Bukkit.createInventory(null, 27, "Cobweb Editor");
        this.cocaineInventory = Bukkit.createInventory(null, 27, "Cocaine Editor");
        this.cooldownbowInventory = Bukkit.createInventory(null, 27, "CooldownBow Editor");
        this.effectsInventory = Bukkit.createInventory(null, 27, "Effects Editor");
        this.fireballInventory = Bukkit.createInventory(null, 27, "FireBall Editor");
        this.freezegunInventory = Bukkit.createInventory(null, 27, "FreezeGun Editor");
        this.grapplinghookInventory = Bukkit.createInventory(null, 27, "GrapplingHook Editor");
        this.ninjaInventory = Bukkit.createInventory(null, 27, "Ninja Editor");
        this.pearlresetInventory = Bukkit.createInventory(null, 27, "PearlReset Editor");
        this.rocketboostInventory = Bukkit.createInventory(null, 27, "RocketBoost Editor");
        this.stormbreakerInventory = Bukkit.createInventory(null, 27, "StormBreaker Editor");
        this.swapperhelmetInventory = Bukkit.createInventory(null, 27, "SwapperHelmet Editor");
        this.switcherInventory = Bukkit.createInventory(null, 27, "Switcher Editor");
        this.viewswitcherInventory = Bukkit.createInventory(null, 27, "ViewSwitcher Editor");
        this.freezeInventory = Bukkit.createInventory(null, 27, "Freeze Editor");
        this.ghostInventory = Bukkit.createInventory(null, 27, "Ghost Editor");
        this.potionrefillInventory = Bukkit.createInventory(null, 27, "Potion Refill Editor");
        this.inventoryswitcherInventory = Bukkit.createInventory(null, 27, "Inventory Switcher Editor");
        this.enderpearl = Bukkit.createInventory(null, 27, "FastPearl");
    }

    //Editors
    public Inventory getAbilitiesEditorInventory() {
        return abilitiesEditorInventory;
    }

    public Inventory getAntitrapInventory() { return antitrapInventory; }
    public Inventory getAntifallboootsInventory() { return antifallboootsInventory; }
    public Inventory getAutobowInventory() { return autobowInventory; }
    public Inventory getBedbombInventory() { return bedbombInventory; }
    public Inventory getBelchbombInventory() { return belchbombInventory; }
    public Inventory getCobwebInventory() { return cobwebInventory; }
    public Inventory getCocaineInventory() { return cocaineInventory; }
    public Inventory getCooldownbowInventory() { return cooldownbowInventory; }
    public Inventory getEffectsInventory() { return effectsInventory; }
    public Inventory getFireballInventory() { return fireballInventory; }
    public Inventory getFreezegunInventory() { return freezegunInventory; }
    public Inventory getGrapplinghookInventory() { return grapplinghookInventory; }
    public Inventory getNinjaInventory() { return ninjaInventory; }
    public Inventory getPearlresetInventory() { return pearlresetInventory; }
    public Inventory getRocketboostInventory() { return rocketboostInventory; }
    public Inventory getStormbreakerInventory() { return stormbreakerInventory; }
    public Inventory getSwapperhelmetInventory() { return swapperhelmetInventory; }
    public Inventory getSwitcherInventory() { return switcherInventory; }
    public Inventory getViewswitcherInventory() { return viewswitcherInventory; }
    public Inventory getFreezeInventory() { return freezeInventory; }
    public Inventory getGhostInventory() { return ghostInventory; }
    public Inventory getPotionrefillInventory() { return potionrefillInventory; }
    public Inventory getInventoryswitcherInventory() { return inventoryswitcherInventory; }
    public Inventory getFastPearl() { return enderpearl; }


    /*
     * Abilities Editor
     */

    public void openAbilitiesEditor(Player p) {
        for (int i = 0; i < 9; i++) {
            abilitiesEditorInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        for (int i = 45; i < 54; i++) {
            abilitiesEditorInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        for (int i = 42; i < 45; i++) {
            abilitiesEditorInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        for (int i = 36; i < 39; i++) {
            abilitiesEditorInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        for (int i = 9; i < 12; i++) {
            abilitiesEditorInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        for (int i = 15; i < 18; i++) {
            abilitiesEditorInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        abilitiesEditorInventory.setItem(12, plugin.getAbilitiesManager().getGhost().getItem());
        abilitiesEditorInventory.setItem(13, plugin.getAbilitiesManager().getGrapplingHook().getItem());
        abilitiesEditorInventory.setItem(14, plugin.getAbilitiesManager().getFreeze().getItem());
      
        abilitiesEditorInventory.setItem(18, plugin.getAbilitiesManager().getCocaine().getItem());
        abilitiesEditorInventory.setItem(19, plugin.getAbilitiesManager().getViewSwitcher().getItem());
        abilitiesEditorInventory.setItem(20, plugin.getAbilitiesManager().getFastPearl().getItem());
        abilitiesEditorInventory.setItem(21, plugin.getAbilitiesManager().getRocketBoost().getItem());
        abilitiesEditorInventory.setItem(22, plugin.getAbilitiesManager().getSwapperHelmet().getItem());
        abilitiesEditorInventory.setItem(23, plugin.getAbilitiesManager().getEffects().getItem());
        abilitiesEditorInventory.setItem(24, plugin.getAbilitiesManager().getSwitcher().getItem());
        abilitiesEditorInventory.setItem(25, plugin.getAbilitiesManager().getFireBall().getItem());
        abilitiesEditorInventory.setItem(26, plugin.getAbilitiesManager().getAntiTrap().getItem());
        abilitiesEditorInventory.setItem(27, plugin.getAbilitiesManager().getStormBreaker().getItem());
        abilitiesEditorInventory.setItem(28, plugin.getAbilitiesManager().getCobweb().getItem());
        abilitiesEditorInventory.setItem(29, plugin.getAbilitiesManager().getBelchBomb().getItem());
        abilitiesEditorInventory.setItem(30, plugin.getAbilitiesManager().getAutoBow().getItem());
        abilitiesEditorInventory.setItem(31, plugin.getAbilitiesManager().getNinja().getItem());
        abilitiesEditorInventory.setItem(32, plugin.getAbilitiesManager().getAntiFalLBoots().getItem());
        abilitiesEditorInventory.setItem(33, plugin.getAbilitiesManager().getFreezeGun().getItem());
        abilitiesEditorInventory.setItem(34, plugin.getAbilitiesManager().getPearlReset().getItem());
        abilitiesEditorInventory.setItem(35, plugin.getAbilitiesManager().getCooldownBow().getItem());
        abilitiesEditorInventory.setItem(39, plugin.getAbilitiesManager().getBedBomb().getItem());
        abilitiesEditorInventory.setItem(40, plugin.getAbilitiesManager().getPotionRefill().getItem());
        abilitiesEditorInventory.setItem(41, plugin.getAbilitiesManager().getInventorySwitcher().getItem());

        p.openInventory(abilitiesEditorInventory);
    }

    /*
     * Abilities
     */

    public void openAntiTrapInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            antitrapInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        antitrapInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        antitrapInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        antitrapInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        antitrapInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        antitrapInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        antitrapInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        antitrapInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        antitrapInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            antitrapInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        antitrapInventory.setItem(13, plugin.getAbilitiesManager().getAntiTrap().isEnabled() ? Enable() : Disable());

        p.openInventory(antitrapInventory);
    }
    public void openAntiFallBootsInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            antifallboootsInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        antifallboootsInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        antifallboootsInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        antifallboootsInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        antifallboootsInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        antifallboootsInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        antifallboootsInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        antifallboootsInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        antifallboootsInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            antifallboootsInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        antifallboootsInventory.setItem(13, plugin.getAbilitiesManager().getAntiFalLBoots().isEnabled() ? Enable() : Disable());

        p.openInventory(antifallboootsInventory);
    }
    public void openAutoBowInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            autobowInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        autobowInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        autobowInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        autobowInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        autobowInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        autobowInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        autobowInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        autobowInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        autobowInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            autobowInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        autobowInventory.setItem(13, plugin.getAbilitiesManager().getAutoBow().isEnabled() ? Enable() : Disable());

        p.openInventory(autobowInventory);
    }
    public void openBedBombInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            bedbombInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        bedbombInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        bedbombInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        bedbombInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        bedbombInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        bedbombInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        bedbombInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        bedbombInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        bedbombInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            bedbombInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        bedbombInventory.setItem(13, plugin.getAbilitiesManager().getBedBomb().isEnabled() ? Enable() : Disable());

        p.openInventory(bedbombInventory);
    }
    public void openBelchBombInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            belchbombInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        belchbombInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        belchbombInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        belchbombInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        belchbombInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        belchbombInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        belchbombInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        belchbombInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        belchbombInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            belchbombInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        belchbombInventory.setItem(13, plugin.getAbilitiesManager().getBelchBomb().isEnabled() ? Enable() : Disable());

        p.openInventory(belchbombInventory);
    }
    public void openCowbebInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            cobwebInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        cobwebInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        cobwebInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        cobwebInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        cobwebInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        cobwebInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        cobwebInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        cobwebInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        cobwebInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            cobwebInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        cobwebInventory.setItem(13, plugin.getAbilitiesManager().getCobweb().isEnabled() ? Enable() : Disable());

        p.openInventory(cobwebInventory);
    }
    public void openCocaineInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            cocaineInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        cocaineInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        cocaineInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        cocaineInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        cocaineInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        cocaineInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        cocaineInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        cocaineInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        cocaineInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            cocaineInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        cocaineInventory.setItem(13, plugin.getAbilitiesManager().getCocaine().isEnabled() ? Enable() : Disable());

        p.openInventory(cocaineInventory);
    }
    public void openCooldonwBowInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            cooldownbowInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        cooldownbowInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        cooldownbowInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        cooldownbowInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        cooldownbowInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        cooldownbowInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        cooldownbowInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        cooldownbowInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        cooldownbowInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            cooldownbowInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        cooldownbowInventory.setItem(13, plugin.getAbilitiesManager().getCooldownBow().isEnabled() ? Enable() : Disable());

        p.openInventory(cooldownbowInventory);
    }
    public void openEffectsInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            effectsInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        effectsInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        effectsInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        effectsInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        effectsInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        effectsInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        effectsInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        effectsInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        effectsInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            effectsInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        effectsInventory.setItem(13, plugin.getAbilitiesManager().getEffects().isEnabled() ? Enable() : Disable());

        p.openInventory(effectsInventory);
    }
    public void openFireBallInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            fireballInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        fireballInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        fireballInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        fireballInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        fireballInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        fireballInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        fireballInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        fireballInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        fireballInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            fireballInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        fireballInventory.setItem(13, plugin.getAbilitiesManager().getFireBall().isEnabled() ? Enable() : Disable());

        p.openInventory(fireballInventory);
    }
    public void openFreezeGunInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            freezegunInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        freezegunInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        freezegunInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        freezegunInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        freezegunInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        freezegunInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        freezegunInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        freezegunInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        freezegunInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            freezegunInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        freezegunInventory.setItem(13, plugin.getAbilitiesManager().getFreezeGun().isEnabled() ? Enable() : Disable());

        p.openInventory(freezegunInventory);
    }
    public void openGrapplingHookInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            grapplinghookInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        grapplinghookInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        grapplinghookInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        grapplinghookInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        grapplinghookInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        grapplinghookInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        grapplinghookInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        grapplinghookInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        grapplinghookInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            grapplinghookInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        grapplinghookInventory.setItem(13, plugin.getAbilitiesManager().getGrapplingHook().isEnabled() ? Enable() : Disable());


        p.openInventory(grapplinghookInventory);
    }
    public void openNinjaInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            ninjaInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        ninjaInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        ninjaInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        ninjaInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        ninjaInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        ninjaInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        ninjaInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        ninjaInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        ninjaInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            ninjaInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        ninjaInventory.setItem(13, plugin.getAbilitiesManager().getNinja().isEnabled() ? Enable() : Disable());


        p.openInventory(ninjaInventory);
    }
    public void openPearlResetInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            pearlresetInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        pearlresetInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        pearlresetInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        pearlresetInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        pearlresetInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        pearlresetInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        pearlresetInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        pearlresetInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        pearlresetInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            pearlresetInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        pearlresetInventory.setItem(13, plugin.getAbilitiesManager().getPearlReset().isEnabled() ? Enable() : Disable());

        p.openInventory(pearlresetInventory);
    }
    public void openRocketBoostInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            rocketboostInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        rocketboostInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        rocketboostInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        rocketboostInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        rocketboostInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        rocketboostInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        rocketboostInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        rocketboostInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        rocketboostInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            rocketboostInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        rocketboostInventory.setItem(13, plugin.getAbilitiesManager().getRocketBoost().isEnabled() ? Enable() : Disable());

        p.openInventory(rocketboostInventory);
    }
    public void openStormBreakerInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            stormbreakerInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        stormbreakerInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        stormbreakerInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        stormbreakerInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        stormbreakerInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        stormbreakerInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        stormbreakerInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        stormbreakerInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        stormbreakerInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            stormbreakerInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        stormbreakerInventory.setItem(13, plugin.getAbilitiesManager().getStormBreaker().isEnabled() ? Enable() : Disable());

        p.openInventory(stormbreakerInventory);
    }
    public void openSwapperHelmetInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            swapperhelmetInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        swapperhelmetInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        swapperhelmetInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        swapperhelmetInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        swapperhelmetInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        swapperhelmetInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        swapperhelmetInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        swapperhelmetInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        swapperhelmetInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            swapperhelmetInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        swapperhelmetInventory.setItem(13, plugin.getAbilitiesManager().getSwapperHelmet().isEnabled() ? Enable() : Disable());

        p.openInventory(swapperhelmetInventory);
    }
    public void openSwitcherInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            switcherInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        switcherInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        switcherInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        switcherInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        switcherInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        switcherInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        switcherInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        switcherInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        switcherInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            switcherInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        switcherInventory.setItem(13, plugin.getAbilitiesManager().getSwitcher().isEnabled() ? Enable() : Disable());

        p.openInventory(switcherInventory);
    }
    public void openViewSwitcherInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            viewswitcherInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        viewswitcherInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        viewswitcherInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        viewswitcherInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        viewswitcherInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        viewswitcherInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        viewswitcherInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        viewswitcherInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        viewswitcherInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            viewswitcherInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        viewswitcherInventory.setItem(13, plugin.getAbilitiesManager().getViewSwitcher().isEnabled() ? Enable() : Disable());

        p.openInventory(viewswitcherInventory);
    }
    public void openFreezeInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            freezeInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        freezeInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        freezeInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        freezeInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        freezeInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        freezeInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        freezeInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        freezeInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        freezeInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            freezeInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        freezeInventory.setItem(13, plugin.getAbilitiesManager().getFreeze().isEnabled() ? Enable() : Disable());

        p.openInventory(freezeInventory);
    }
    public void openGhostInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            ghostInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        ghostInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        ghostInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        ghostInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        ghostInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        ghostInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        ghostInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        ghostInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        ghostInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            ghostInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        ghostInventory.setItem(13, plugin.getAbilitiesManager().getGhost().isEnabled() ? Enable() : Disable());

        p.openInventory(ghostInventory);
    }
    public void openPotionRefillInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            potionrefillInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        potionrefillInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        potionrefillInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        potionrefillInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        potionrefillInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        potionrefillInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        potionrefillInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        potionrefillInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        potionrefillInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            potionrefillInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        potionrefillInventory.setItem(13, plugin.getAbilitiesManager().getPotionRefill().isEnabled() ? Enable() : Disable());

        p.openInventory(potionrefillInventory);
    }
    public void openInventorySwitcherInventory(Player p) {
        for (int i = 0; i < 9; i++) {
            inventoryswitcherInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        inventoryswitcherInventory.setItem(9, new ItemStack(Material.STAINED_GLASS_PANE));
        inventoryswitcherInventory.setItem(10, new ItemStack(Material.STAINED_GLASS_PANE));
        inventoryswitcherInventory.setItem(11, new ItemStack(Material.STAINED_GLASS_PANE));
        inventoryswitcherInventory.setItem(12, new ItemStack(Material.STAINED_GLASS_PANE));
        inventoryswitcherInventory.setItem(14, new ItemStack(Material.STAINED_GLASS_PANE));
        inventoryswitcherInventory.setItem(15, new ItemStack(Material.STAINED_GLASS_PANE));
        inventoryswitcherInventory.setItem(16, new ItemStack(Material.STAINED_GLASS_PANE));
        inventoryswitcherInventory.setItem(17, new ItemStack(Material.STAINED_GLASS_PANE));

        for (int i = 18; i < 27; i++) {
            inventoryswitcherInventory.setItem(i, new ItemStack(Material.STAINED_GLASS_PANE));
        }
        inventoryswitcherInventory.setItem(13, plugin.getAbilitiesManager().getInventorySwitcher().isEnabled() ? Enable() : Disable());

        p.openInventory(inventoryswitcherInventory);
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
