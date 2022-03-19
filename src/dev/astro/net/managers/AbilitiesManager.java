package dev.astro.net.managers;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.event.Listener;

import dev.astro.net.Vulcan;
import dev.astro.net.abilities.*;
import dev.astro.net.utilities.timers.Timer;

import java.util.LinkedHashSet;
import java.util.Set;

public class AbilitiesManager {

    @Getter
    private Vulcan plugin;
    @Getter
    private Set<Timer> timers = new LinkedHashSet<>();
    /* Timers */
    private GrapplingHook grapplingHook;
    private Cocaine cocaine;
    private ViewSwitcher viewSwitcher;
    private RocketBoost rocketBoost;
    private SwapperHelmet swapperHelmet;
    private AntiTrap antiTrap;
    private Effects effects;
    private Switcher switcher;
    private FireBall fireBall;
    private StormBreaker stormBreaker;
    private Cobweb cobweb;
    private BelchBomb belchBomb;
    private AutoBow autoBow;
    private Ninja ninja;
    private FreezeGun freezeGun;
    private PearlReset pearlReset;
    private CooldownBow cooldownBow;
    private BedBomb bedBomb;
    private Freeze freeze;
    private Ghost ghost;
    private PotionRefill potionRefill;
    private InventorySwitcher inventorySwitcher;
    private FastPearl fastPearl;
    /* Others */
    private AntiFallBoots antiFallBoots;


    public AbilitiesManager(Vulcan plugin) {
        this.plugin = plugin;
        registerTimer(grapplingHook = new GrapplingHook());
        registerTimer(cocaine = new Cocaine());
        registerTimer(viewSwitcher = new ViewSwitcher());
        registerTimer(rocketBoost = new RocketBoost());
        registerTimer(swapperHelmet = new SwapperHelmet());
        registerTimer(antiTrap = new AntiTrap());
        registerTimer(switcher = new Switcher());
        registerTimer(effects = new Effects());
        registerTimer(fireBall = new FireBall());
        registerTimer(stormBreaker = new StormBreaker());
        registerTimer(cobweb = new Cobweb());
        registerTimer(belchBomb = new BelchBomb());
        registerTimer(autoBow = new AutoBow());
        registerTimer(ninja = new Ninja());
        registerTimer(freezeGun = new FreezeGun());
        registerTimer(pearlReset = new PearlReset());
        registerTimer(cooldownBow = new CooldownBow());
        registerTimer(bedBomb = new BedBomb());
        registerTimer(freeze = new Freeze());
        registerTimer(ghost = new Ghost());
        registerTimer(potionRefill = new PotionRefill());
        registerTimer(inventorySwitcher = new InventorySwitcher());
        registerTimer(fastPearl = new FastPearl());
        antiFallBoots = new AntiFallBoots();
    }

    public void registerTimer(Timer timer) {
        timers.add(timer);
        if (timer instanceof Listener) {
            Bukkit.getPluginManager().registerEvents((Listener) timer, plugin);
        }
    }

    public void unregisterTimer(Timer timer) {
        timers.remove(timer);
    }

    public GrapplingHook getGrapplingHook() {
        return grapplingHook;
    }

    public ViewSwitcher getViewSwitcher() {
        return viewSwitcher;
    }

    public Cocaine getCocaine() {
        return cocaine;
    }

    public RocketBoost getRocketBoost() {
        return rocketBoost;
    }

    public SwapperHelmet getSwapperHelmet() {
        return swapperHelmet;
    }

    public AntiTrap getAntiTrap() {
        return antiTrap;
    }

    public Effects getEffects() {
        return effects;
    }

    public Switcher getSwitcher() {
        return switcher;
    }

    public FireBall getFireBall() {
        return fireBall;
    }

    public StormBreaker getStormBreaker() {
        return stormBreaker;
    }

    public Cobweb getCobweb() {
        return cobweb;
    }

    public BelchBomb getBelchBomb() {
        return belchBomb;
    }

    public AutoBow getAutoBow() {
        return autoBow;
    }

    public Ninja getNinja() {
        return ninja;
    }

    public AntiFallBoots getAntiFalLBoots() {
        return antiFallBoots;
    }

    public FreezeGun getFreezeGun() {
        return freezeGun;
    }

    public PearlReset getPearlReset() {
        return pearlReset;
    }

    public CooldownBow getCooldownBow() {
        return cooldownBow;
    }

    public BedBomb getBedBomb() {
        return bedBomb;
    }

    public Ghost getGhost() { return ghost; }

    public Freeze getFreeze() { return freeze; }

    public PotionRefill getPotionRefill() { return potionRefill; }

    public InventorySwitcher getInventorySwitcher() { return inventorySwitcher;}

    public FastPearl getFastPearl() {
        return fastPearl;
    }
}