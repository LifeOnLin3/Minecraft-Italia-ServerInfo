package me.eduproard.MCItaliaServerInfo;

import java.io.File;
import java.io.IOException;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.Plugin;

public class MCConfig {
	FileConfiguration data;
	File dfile;
	Plugin p;
	static MCConfig instance = new MCConfig();
	
	public static MCConfig getInstance() { 
		return instance; 
	}
	
	public void setup() {
		this.dfile = new File(Main.plugin.getDataFolder(), "MCConfig.yml");
		if(!this.dfile.exists()) {
			try {
				this.dfile.createNewFile();
			} catch(IOException e) {
				Bukkit.getLogger().info("[MCItalia_ServerInfo] File created with exception!");
			}
		}
		this.data = YamlConfiguration.loadConfiguration(this.dfile);
		this.data.addDefault("ServerName", "serverName");
		this.data.addDefault("No-Permission", "&4Non hai il permesso!");
		this.data.options().copyDefaults(true);
	}
	public FileConfiguration getData() {
		return this.data;
	}
	public void saveData() {
		try {
			this.data.save(this.dfile);
		} catch(IOException e) {
			Bukkit.getLogger().info("[MCItalia_ServerInfo] File saved with exception!");
		}
	}
	
}