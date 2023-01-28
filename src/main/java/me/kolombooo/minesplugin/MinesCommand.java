package me.kolombooo.minesplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MinesCommand implements CommandExecutor, TabCompleter {
	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (!(sender instanceof Player player)) {
			Bukkit.getLogger().warning(ConfigHandler.onlyPlayers);
			return true;
		}
		if (args.length == 0 || args[0].equals("help")) {
			sendCommandHelp(player);
			return true;
		}
		switch (args[0]) {
			case "tp" -> {
				if (args.length > 2 || args.length == 2 && (!args[1].equals("vip") && !args[1].equals("normal") && !args[1].equals("admin"))) {
					sendTeleportHelp(player);
				} else {
					switch (args.length == 2 ? args[1] : "normal") {
						case "normal" -> MinesTeleport.teleportToMine(player, "mine");
						case "vip" -> MinesTeleport.teleportToMine(player, "mine_vip");
						case "admin" -> MinesTeleport.teleportToMine(player, "mine_admin");
					}
				}
			}
			case "info" -> sender.sendMessage(ConfigHandler.pluginInfo);
			case "reload" -> {
				if (player.hasPermission("mines.reload")) {
					ConfigHandler.reloadConfig();
					player.sendMessage(ConfigHandler.prefix + ConfigHandler.successfulReload);
				} else {
					player.sendMessage(ConfigHandler.prefix + ConfigHandler.noPermission);
				}
			}
		}
		return true;
	}

	public static void sendCommandHelp(CommandSender sender) {
		if (sender.hasPermission("mines.reload")) {
			sender.sendMessage(ConfigHandler.helpReload);
		} else {
			sender.sendMessage(ConfigHandler.help);
		}
	}

	public static void sendTeleportHelp(CommandSender sender) {
		if (sender.hasPermission("mines.world.mine-admin")) {
			sender.sendMessage(ConfigHandler.teleportHelpAdmin);
		} else if (sender.hasPermission("mines.world.mine-vip")) {
			sender.sendMessage(ConfigHandler.teleportHelpVip);
		} else {
			sender.sendMessage(ConfigHandler.teleportHelp);
		}
	}

	static final List<String> emptyTabCompletions = new ArrayList<>();

	@Override
	public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (args.length == 1) {
			if (sender.hasPermission("mines.reload")) {
				return List.of("tp", "info", "reload", "help");
			} else {
				return List.of("tp", "info", "help");
			}
		}
		if (args.length == 2 && args[0].equals("tp")) {
			if (sender.hasPermission("mines.world.mine-admin")) {
				return List.of("admin", "vip", "default");
			} else if (sender.hasPermission("mines.world.mine-vip")) {
				return List.of("vip", "default");
			}
		}
		return emptyTabCompletions;
	}
}
