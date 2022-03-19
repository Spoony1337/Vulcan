package dev.astro.net.utilities.config;

import org.bukkit.configuration.file.YamlConfiguration;

import dev.astro.net.Vulcan;

public class ConfigFile {

    private ConfigManager root;
   
    private YamlConfiguration config;

    public ConfigFile() {
        root = new ConfigManager(Vulcan.getInstance(), "config", Vulcan.getInstance().getDataFolder().getAbsolutePath());
        config = getRoot().getConfiguration();
    }

    public ConfigManager getRoot() {
        return this.root;
    }

    public void setRoot(ConfigManager root) {
		this.root = root;
	}

	public void setConfig(YamlConfiguration config) {
		this.config = config;
	}

	public YamlConfiguration getConfig() {
        return this.config;
    }
}

