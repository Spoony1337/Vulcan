package dev.astro.net.utilities.config;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;

@Getter @Setter
public class ConfigManager {

    /* File */
    private File file;
    /* Strings */
    private String name, directory;
    /* Configuration */
    private YamlConfiguration configuration;

    /**
     * Bukkit Configuration Class
     *
     * @param name - Is the identifier for the configuration file and object.
     * @param directory - Directory.
     */
    public ConfigManager(JavaPlugin plugin, String name, String directory){
        /* Set the Name */
        setName(name);
        /* Set the Directory */
        setDirectory(directory);
        /* Set File */
        file = new File(directory, name + ".yml");
        /* If file does not already exist, then grab it internally from the resources folder */
        if (!file.exists()) {
            plugin.saveResource(name + ".yml", false);
        }
        /* Load the files configuration */
        this.configuration = YamlConfiguration.loadConfiguration(this.getFile());
    }

    /**
     * Saves the configuration file from memory to storage
     */
    public void save() {
        try {
            configuration.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void reload() {
        try {
            configuration.load(file);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public File getFile() {
        return this.file;
    }
    
    public String getName() {
        return this.name;
    }
    
    public String getDirectory() {
        return this.directory;
    }
    
    public YamlConfiguration getConfiguration() {
        return this.configuration;
    }
    
    public void setFile(File file) {
        this.file = file;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public void setDirectory(String directory) {
        this.directory = directory;
    }
    
    public void setConfiguration(YamlConfiguration configuration) {
        this.configuration = configuration;
    }
}
