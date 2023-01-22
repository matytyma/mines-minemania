package me.kolombooo.minesplugin;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class MinesCommand implements CommandExecutor {

	@Override
	public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
		if (! (sender instanceof Player)) {
			sender.sendMessage("§cTento příkaz mohou používat pouze hráči!");
			return true;
		}
		if (args.length == 0) {
			sender.sendMessage("""
				§6MinesPlugin by matytyma
				§7--------------------
				§6/mines tp <mine> §7-§e Teleportuje tě do daného dolu
				§6/mines help §7-§e Zobrazí tuto nápovědu
				""");
		}
		return true;
	}
}
