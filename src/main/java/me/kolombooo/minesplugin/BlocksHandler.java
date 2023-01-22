package me.kolombooo.minesplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;

import java.util.PriorityQueue;

public class BlocksHandler {
	static PriorityQueue<BrokenOre> blocks = new PriorityQueue<>(BrokenOre::compareTo);

	public static void addBlock(Material material, Location location) {
		int duration = 0;
		if (material.equals(Material.DEEPSLATE_COAL_ORE)) duration = 3_000;
		if (material.equals(Material.DEEPSLATE_IRON_ORE)) duration = 180_000;
		if (material.equals(Material.DEEPSLATE_GOLD_ORE)) duration = 300_000;
		if (material.equals(Material.DEEPSLATE_REDSTONE_ORE)) duration = 420_000;
		if (material.equals(Material.DEEPSLATE_LAPIS_ORE)) duration = 420_000;
		if (material.equals(Material.DEEPSLATE_EMERALD_ORE)) duration = 540_000;
		if (material.equals(Material.DEEPSLATE_DIAMOND_ORE)) duration = 900_000;
		if (material.equals(Material.BEDROCK)) duration = 1_800_000;
		System.out.println("duration = " + duration);
		System.out.println("material = " + material);
		blocks.add(new BrokenOre(material, location, System.currentTimeMillis() + duration));
	}
}
