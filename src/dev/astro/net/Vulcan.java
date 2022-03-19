package dev.astro.net;

import com.sk89q.worldedit.bukkit.WorldEditPlugin;

import dev.astro.net.abilities.AntiFallBoots;
import dev.astro.net.commands.AbilitiesCommand;
import dev.astro.net.commands.vulcan.VulcanExecutor;
import dev.astro.net.managers.AbilitiesManager;
import dev.astro.net.managers.RegionsManager;
import dev.astro.net.menu.AbilitiesMenu;
import dev.astro.net.menu.AbilitiesMenuListener;
import dev.astro.net.utilities.ChatUtil;
import dev.astro.net.utilities.ConfigService;
import dev.astro.net.utilities.ConfigurationFile;
import dev.astro.net.utilities.Reflection;

import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class Vulcan extends JavaPlugin {

    private static Vulcan instance;
    private AbilitiesManager abilitiesManager;
    private RegionsManager regionsManager;
    private ConfigurationFile config;
    private ConfigurationFile regions;
    private AbilitiesMenu abilitiesMenu;
    private ConfigService configService;

    public void onEnable() {
        instance = this;
        this.config = new ConfigurationFile(this, "config.yml");  
    	getCommand("abilities").setExecutor(new AbilitiesCommand(this));
        getCommand("vulcan").setExecutor(new VulcanExecutor());
        this.regions = new ConfigurationFile(this, "regions.yml");
        abilitiesManager = new AbilitiesManager(this);
        regionsManager = new RegionsManager();
        abilitiesMenu = new AbilitiesMenu(this);
        configService = new ConfigService();
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new AbilitiesMenuListener(this), this);
        pm.registerEvents(new AntiFallBoots(), this);

    }

    
    public void onDisable() {
        instance = null;
    }

    public static Vulcan getInstance() {
        return instance;
    }

    public AbilitiesManager getAbilitiesManager() {
        return abilitiesManager;
    }

    public AbilitiesMenu getAbilitiesMenu() {
        return abilitiesMenu;
    }

    public ConfigurationFile getConfigFile() {
        return config;
    }

    public ConfigurationFile getRegionsFile() {
        return regions;
    }

    public RegionsManager getRegionsManager() {
        return regionsManager;
    }

    public static void setInstance(Vulcan instance) {
		Vulcan.instance = instance;
	}

	public void setAbilitiesManager(AbilitiesManager abilitiesManager) {
		this.abilitiesManager = abilitiesManager;
	}

	public void setRegionsManager(RegionsManager regionsManager) {
		this.regionsManager = regionsManager;
	}


	public void setAbilitiesMenu(AbilitiesMenu abilitiesMenu) {
		this.abilitiesMenu = abilitiesMenu;
	}

	public void setConfigService(ConfigService configService) {
		this.configService = configService;
	}

	public ConfigService getConfigService() {
        return configService;
    }

    public WorldEditPlugin getWorldEdit() {
        return (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
    }
}


