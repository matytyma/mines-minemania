package me.kolombooo.minesplugin;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
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
			Bukkit.getLogger().warning("Tento příkaz mohou používat pouze hráči!");
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
			case "info" ->
					sender.sendMessage(Component.text("\n§f-----------§6§l Informace o Dolech §f-----------\n§6Zábava v dolech s obnovujícími bloky úspěšně zajištěna!\n§6Plugin s láskou vytvořil ")
							.append(Component.text("§ematytyma").clickEvent(ClickEvent.clickEvent(ClickEvent.Action.OPEN_URL, "https://github.com/matytyma"))));
			case "reload" -> {
				if (player.hasPermission("mines.reload")) {
					ConfigHandler.reloadConfig();
					player.sendMessage("§6§l[Doly] §r§aKonfigurace pluginu byla úspěšně načtena!");
				} else {
					player.sendMessage("§6§l[Doly] §r§cNemáš dostatečná oprávnění!");
				}
			}
		}
		return true;
	}

	public static void sendCommandHelp(CommandSender sender) {
		if (sender.hasPermission("mines.reload")) {
			sender.sendMessage("""

					§f----------- §6§lNápověda pro Doly §f-----------
					§6/mines tp <důl> §7-§e Teleportuje tě do daného dolu
					§6/mines info §7-§e Zobrazí informace o pluginu
					§6/mines reload §7-§e Znovu načte konfiguraci pluginu
					§6/mines help §7-§e Zobrazí tuto nápovědu
					""");
		} else {
			sender.sendMessage("""

					§f----------- §6§lNápověda pro Doly §f-----------
					§6/mines tp <důl> §7-§e Teleportuje tě do daného dolu
					§6/mines info §7-§e Zobrazí informace o pluginu
					§6/mines help §7-§e Zobrazí tuto nápovědu
					""");
		}
	}

	public static void sendTeleportHelp(CommandSender sender) {
		if (sender.hasPermission("mines.world.mine-admin")) {
			sender.sendMessage("""
					 
					§f----------- §6§lNápověda pro Doly §f-----------
					§6/mines tp admin §7-§e Teleportuje tě do admin dolu
					§6/mines tp vip §7-§e Teleportuje tě do vip dolu
					§6/mines tp §7-§e Teleportuje tě do základního dolu
					""");
		} else if (sender.hasPermission("mines.world.mine-vip")) {
			sender.sendMessage("""
					     
					§f----------- §6§lNápověda pro Doly §f-----------
					§6/mines tp vip §7-§e Teleportuje tě do vip dolu
					§6/mines tp §7-§e Teleportuje tě do základního dolu
					""");
		} else {
			sender.sendMessage("""
					     
					§f----------- §6§lNápověda pro Doly §f-----------
					§6/mines tp §7-§e Teleportuje tě do základního dolu
					""");
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
