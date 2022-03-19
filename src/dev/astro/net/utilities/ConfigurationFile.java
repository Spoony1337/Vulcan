package dev.astro.net.utilities;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import dev.astro.net.utilities.chat.CC;


public class ConfigurationFile extends YamlConfiguration {
  private File file;
  
  private JavaPlugin plugin;
  
  private String name;
  
  public ConfigurationFile(JavaPlugin plugin, String name) {
    this.file = new File(plugin.getDataFolder(), name);
    this.plugin = plugin;
    this.name = name;
    if (!this.file.exists())
      plugin.saveResource(name, false); 
    try {
      load(this.file);
    } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
      e.printStackTrace();
    } 
  }
  
  public void load() {
    this.file = new File(this.plugin.getDataFolder(), this.name);
    if (!this.file.exists())
      this.plugin.saveResource(this.name, false); 
    try {
      load(this.file);
    } catch (IOException|org.bukkit.configuration.InvalidConfigurationException e) {
      e.printStackTrace();
    } 
  }
  
  public void save() {
    try {
      save(this.file);
    } catch (IOException e) {
      e.printStackTrace();
    } 
  }
  
  public int getInt(String path) {
    return getInt(path, 0);
  }
  
  public double getDouble(String path) {
    return getDouble(path, 0.0D);
  }
  
  public boolean getBoolean(String path) {
    return getBoolean(path, false);
  }
  
  public String getString(String path, boolean check) {
    return getString(path, null);
  }
  
  public String getString(String path) {
    if (super.getString(path) == "")
      return CC.translate(getString(path, "String at path '" + path + "' not found.")).replace("|", ""); 
    return CC.translate(getString(path, "String at path '" + path + "' not found.")).replace("|", "");
  }
  
  public List<String> getStringList(String path) {
    return (List<String>)super.getStringList(path).stream().map(CC::translate).collect(Collectors.toList());
  }
  
  public List<String> getStringList(String path, boolean check) {
    if (!contains(path))
      return null; 
    return (List<String>)super.getStringList(path).stream().map(CC::translate).collect(Collectors.toList());
  }
  
  public boolean getOption(String option) {
    return getBoolean("options." + option);
  }
}
