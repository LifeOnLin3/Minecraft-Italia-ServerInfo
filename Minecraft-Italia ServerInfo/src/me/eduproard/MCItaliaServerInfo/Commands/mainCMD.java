package me.eduproard.MCItaliaServerInfo.Commands;

import me.eduproard.MCItaliaServerInfo.MCConfig;
import me.eduproard.MCItaliaServerInfo.Main;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;

public class mainCMD implements CommandExecutor {
	
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(args.length == 0 || args.length >= 3) {
			Player p = (Player) sender;
			if(cmd.getName().equalsIgnoreCase("mcitalia")) {
				if(p.hasPermission(new Permission("mcitalia.help", PermissionDefault.OP))) {
					p.sendMessage("§4§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
					p.sendMessage("§2Comandi:");
					p.sendMessage("§7 - §b/mcitalia setserver <nomeServer/ID>");
					p.sendMessage("§f§o    Per settare il nome/ID del tuo server ricavabile dall'URL della pagina di quest'ultimo!");
					p.sendMessage("§7 - §b/serverl allinfo");
					p.sendMessage("§f§o    Ottieni il numero dei voti/voti_odierni/posizione_lista!");
					p.sendMessage("§7 - §b/serverl votes");
					p.sendMessage("§f§o    Ottieni solamente il numero dei voti!");
					p.sendMessage("§7 - §b/serverl votes_today");
					p.sendMessage("§f§o    Ottieni solamente il numero dei voti odierni!");
					p.sendMessage("§7 - §b/serverl position");
					p.sendMessage("§f§o    Ottieni la posizione del tuo server!");
					p.sendMessage("§7 - §b/serverl getserver <nomeServer/ID>");
					p.sendMessage("§f§o    Ottieni la posizione del tuo server!");
					p.sendMessage("§4§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
				} else {
					p.sendMessage(MCConfig.getInstance().getData().getString("No-Permission").replaceAll("&", "§"));
				}
			}
			return false;
		}
		if(args.length == 2) {
			String command = args[0];
			String name = args[1];
			Player p = (Player) sender;
			if(command.equalsIgnoreCase("setserver")) {
				if((p.hasPermission(new Permission("mcitalia.command.setserver", PermissionDefault.OP)))) {
					MCConfig.getInstance().getData().set("ServerName", name);
					MCConfig.getInstance().saveData();
					p.sendMessage(Main.getPrefix() + "§2Hai settato il nome del server!");
				} else {
					p.sendMessage(MCConfig.getInstance().getData().getString("No-Permission").replaceAll("&", "§"));
				}
			}
			return false;
		}
		return false;
	}
}