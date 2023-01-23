package me.kolombooo.minesplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

public class MineListener implements Listener {
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		String worldName = block.getWorld().getName();
		if (!worldName.equals("mine") && !worldName.equals("mine_vip") && !worldName.equals("mine_admin") && !worldName.equals("world")) return;
		String type = block.getType().toString();
		if (type.contains("DEEPSLATE_")) {
			event.setCancelled(true);
			return;
		}
		if (type.contains("ORE")) {
			if (type.equals("BEDROCK")) {
				block.setType(Material.BEDROCK);
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> block.setType(Material.BEDROCK), 1);
			} else {
				Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> block.setType(Material.valueOf("DEEPSLATE_" + type)), 1);
			}
			BlocksHandler.addBlock(block.getType(), block.getLocation().clone());
		} else {
			event.setCancelled(true);
		}
	}
}
