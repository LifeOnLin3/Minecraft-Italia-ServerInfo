package me.eduproard.MCItaliaServerInfo.Commands;

import me.eduproard.MCItaliaServerInfo.MCConfig;
import me.eduproard.MCItaliaServerInfo.Main;
import me.eduproard.MCItaliaServerInfo.Networking.JSONReader;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.permissions.Permission;
import org.bukkit.permissions.PermissionDefault;
import org.json.simple.parser.ParseException;

public class serverlCMD implements CommandExecutor {
	
	
    private String spacer = "§4§l=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=";
	
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if(cmd.getName().equalsIgnoreCase("serverl")) {
			Player p = (Player)sender;
			if(!p.hasPermission("mcitalia.command.serverl") || !p.hasPermission("mcitalia.command.serverl.position") || !p.hasPermission("mcitalia.command.serverl.votes") || !p.hasPermission("mcitalia.command.serverl.votestoday") || !p.hasPermission("mcitalia.command.serverl.allinfo") || !p.hasPermission("mcitalia.command.serverl.getserver")) {
				p.sendMessage(MCConfig.getInstance().getData().getString("No-Permission").replaceAll("&", "§"));
				return false;
			}
			if(p.hasPermission(new Permission("mcitalia.command.serverl", PermissionDefault.OP))) {
				if(args.length == 0 || args.length >= 3) {
			         p.sendMessage(Main.getPrefix() + "§4La sintassi corretta e': /serverl <allinfo/position/votes/votes_today/getserver <serverName/ID>>!");
			         return false;
				}
			}
			Object position = 0;
			Object votes = 0;
			Object votes_today = 0;
			if(args.length == 1) {
				String reqJ = args[0];
				if(JSONReader.existServer(MCConfig.getInstance().getData().getString("ServerName"))) {
					if((p.hasPermission(new Permission("mcitalia.command.serverl.position", PermissionDefault.OP)))) {
						if(reqJ.equalsIgnoreCase("position")) {
							try {
								position = JSONReader.getPosition(MCConfig.getInstance().getData().getString("ServerName"));
							} catch (ParseException e) {
								p.sendMessage(Main.getPrefix() + " §4Si e' verificato un problema! {ParseException}");
							}
							p.sendMessage(Main.getPrefix() + "§2La posizione di " + MCConfig.getInstance().getData().getString("ServerName") + " sulla lista servers di Minecraft-Italia e' §2§l" + position + "§r§2!");
						}
					}
					if((p.hasPermission(new Permission("mcitalia.command.serverl.votes", PermissionDefault.OP)))) {
						if(reqJ.equalsIgnoreCase("votes")) {
							try {
								votes = JSONReader.getVotes(MCConfig.getInstance().getData().getString("ServerName"));
							} catch (ParseException e) {
								p.sendMessage(Main.getPrefix() + " §4Si e' verificato un problema! {ParseException}");
							}
							p.sendMessage(Main.getPrefix() + "§2" + MCConfig.getInstance().getData().getString("ServerName") + " sulla lista servers di Minecraft-Italia");
							p.sendMessage("§2ha §2§l" + votes + " §r§2voti in totale!");
						}
					}
					if((p.hasPermission(new Permission("mcitalia.command.serverl.votestoday", PermissionDefault.OP)))) {
						if(reqJ.equalsIgnoreCase("votes_today")) {
							try {
								votes_today = JSONReader.getVotes_Today(MCConfig.getInstance().getData().getString("ServerName"));
							} catch (ParseException e) {
								p.sendMessage(Main.getPrefix() + " §4Si e' verificato un problema! {ParseException}");
							}
							p.sendMessage(Main.getPrefix() + "§2" + MCConfig.getInstance().getData().getString("ServerName") + " sulla lista servers di Minecraft-Italia");
							p.sendMessage("§2oggi ha §2§l" + votes_today + " §r§2voti in totale!");
						}
					}
					if((p.hasPermission(new Permission("mcitalia.command.serverl.allinfo", PermissionDefault.OP)))) {
						if(reqJ.equalsIgnoreCase("allinfo")) {
							try {
								position = JSONReader.getPosition(MCConfig.getInstance().getData().getString("ServerName"));
								votes = JSONReader.getVotes(MCConfig.getInstance().getData().getString("ServerName"));
								votes_today = JSONReader.getVotes_Today(MCConfig.getInstance().getData().getString("ServerName"));
							} catch (ParseException e) {
								p.sendMessage(Main.getPrefix() + " §4Si e' verificato un problema! {ParseException}");
							}
							p.sendMessage(spacer);
							p.sendMessage(Main.getPrefix() + "§2Le informazioni riguardo " + MCConfig.getInstance().getData().getString("ServerName") + " sono:");
							p.sendMessage(" §bPosizione: §3" + position);
							p.sendMessage(" §bVoti(in totale): §3" + votes);
							p.sendMessage(" §bVoti(oggi): §3" + votes_today);
							p.sendMessage(spacer);
						}
					}
				} else {
					p.sendMessage(Main.getPrefix() + "§4" + MCConfig.getInstance().getData().getString("ServerName") + " non esiste sulla lista servers!");
				}
				return false;
			}
			if(args.length == 2) {
				String getServer = args[0];
				String server = args[1];
				if(JSONReader.existServer(server)) {
					if((p.hasPermission(new Permission("mcitalia.command.serverl.position", PermissionDefault.OP)))) {
						if(getServer.equalsIgnoreCase("getserver")) {
							try {
								position = JSONReader.getPosition(server);
								votes = JSONReader.getVotes(server);
								votes_today = JSONReader.getVotes_Today(server);
							} catch (ParseException e) {
								p.sendMessage(Main.getPrefix() + " §4Si e' verificato un problema! {ParseException}");
							}
							p.sendMessage(spacer);
							p.sendMessage(Main.getPrefix() + "§2Le informazioni riguardo " + server + " sono:");
							p.sendMessage(" §bPosizione: §3" + position);
							p.sendMessage(" §bVoti(in totale): §3" + votes);
							p.sendMessage(" §bVoti(oggi): §3" + votes_today);
							p.sendMessage(spacer);
						}
					}
				} else {
					p.sendMessage(Main.getPrefix() + "§4" + server + " non esiste sulla lista servers!");
				}
				return false;
			}
		}
		return false;
	}
}
