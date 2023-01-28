package me.kolombooo.minesplugin;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.Objects;

public class MinesTeleport {
	public static void teleportToMine(Player player, String mineName) {
		if (player.hasPermission("mines.teleport." + mineName)) {
			player.teleport(Objects.requireNonNull(Bukkit.getWorld(mineName)).getSpawnLocation());
		} else {
			player.sendMessage("§6§l[Doly] §r§cNemáš dostatečná oprávnění!");
		}
	}
}
