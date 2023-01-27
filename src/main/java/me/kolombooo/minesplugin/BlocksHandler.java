package me.kolombooo.minesplugin;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;

import java.util.PriorityQueue;

public class BlocksHandler {
	static PriorityQueue<BrokenOre> blocks = new PriorityQueue<>(BrokenOre::compareTo);

	public static void addBlock(Player player, Material material, Location location) {
		int duration = 0, reward = 0;
		if (material.equals(Material.COAL_ORE)) {
			duration = 60_00;
			reward = ConfigHandler.rewards.get("COAL_ORE");
		}
		if (material.equals(Material.IRON_ORE)) {
			duration = 180_00;
			reward = ConfigHandler.rewards.get("IRON_ORE");
		}
		if (material.equals(Material.GOLD_ORE)) {
			duration = 300_00;
			reward = ConfigHandler.rewards.get("GOLD_ORE");
		}
		if (material.equals(Material.REDSTONE_ORE)) {
			duration = 420_00;
			reward = ConfigHandler.rewards.get("REDSTONE_ORE");
		}
		if (material.equals(Material.LAPIS_ORE)) {
			duration = 420_00;
			reward = ConfigHandler.rewards.get("LAPIS_ORE");
		}
		if (material.equals(Material.EMERALD_ORE)) {
			duration = 540_00;
			reward = ConfigHandler.rewards.get("EMERALD_ORE");
		}
		if (material.equals(Material.DIAMOND_ORE)) {
			duration = 900_00;
			reward = ConfigHandler.rewards.get("DIAMOND_ORE");
		}
		if (material.equals(Material.ANCIENT_DEBRIS)) {
			System.out.println("Ancient debris");
			duration = 1_800_00;
			reward = ConfigHandler.rewards.get("ANCIENT_DEBRIS");
		}
		blocks.add(new BrokenOre(material, location, System.currentTimeMillis() + duration));
		Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "aincome " + player.getName() + reward);
	}
}
