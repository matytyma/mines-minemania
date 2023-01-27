package me.kolombooo.minesplugin;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.data.BlockData;
import org.jetbrains.annotations.NotNull;

public class BrokenOre implements Comparable<BrokenOre> {
	long respawnTime;
	Location location;
	BlockData blockData;

	public BrokenOre(Material material, Location location, long respawnTime) {
		if (!material.toString().equals("ANCIENT_DEBRIS")) {
			this.blockData = Material.valueOf(material.toString().replace("DEEPSLATE_", "")).createBlockData();
		} else {
			this.blockData = Material.ANCIENT_DEBRIS.createBlockData();
		}
		this.location = location;
		this.respawnTime = respawnTime;
	}

	@Override
	public int compareTo(@NotNull BrokenOre o) {
		return Long.compare(respawnTime, o.respawnTime);
	}
}
