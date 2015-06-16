package me.eduproard.MCItaliaServerInfo;

import me.eduproard.MCItaliaServerInfo.Commands.mainCMD;
import me.eduproard.MCItaliaServerInfo.Commands.serverlCMD;

import org.bukkit.plugin.java.JavaPlugin;
 
public class Main extends JavaPlugin {
	public static Main plugin;
	private static String prefix = "§7[§fMC§2It§fal§cia§7] §r";
	
	public void onEnable() {
		plugin = this;
		MCConfig.getInstance().setup();
		MCConfig.getInstance().saveData();
		registerCommands();
		getLogger().info("Minecraft-Italia ServerInfo plugin enabled!");
	}
	public void registerCommands() {
		getCommand("mcitalia").setExecutor(new mainCMD());
		getCommand("serverl").setExecutor(new serverlCMD());
	}
	public static String getPrefix() {
		return prefix;
	}
}