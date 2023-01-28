package me.kolombooo.minesplugin;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Random;

public class MineListener implements Listener {
	static final Random random = new Random(System.currentTimeMillis());

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Block block = event.getBlock();
		String worldName = block.getWorld().getName();
		if (!worldName.equals("mine") && !worldName.equals("mine_vip") && !worldName.equals("mine_admin") && !worldName.equals("world"))
			return;
		String type = block.getType().toString();
		if (type.contains("DEEPSLATE_")) {
			event.setCancelled(true);
			return;
		}
		Player player = event.getPlayer();
		if (type.contains("ORE")) {
			BlocksHandler.addBlock(player, block.getType(), block.getLocation().clone());
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> block.setType(Material.valueOf("DEEPSLATE_" + type)), 1);
		} else if (type.equals("ANCIENT_DEBRIS")) {
			BlocksHandler.addBlock(player, block.getType(), block.getLocation().clone());
			Bukkit.getScheduler().runTaskLater(Main.getPlugin(Main.class), () -> block.setType(Material.BEDROCK), 1);
		} else {
			event.setCancelled(true);
			return;
		}
		if (random.nextInt(100) < ConfigHandler.dropChances.get(type)) {
			player.getInventory().addItem(event.getBlock().getDrops().toArray(new ItemStack[0])).forEach((k, v) -> player.getWorld().dropItemNaturally(player.getLocation(), v));
		} else {
			event.setExpToDrop(0);
		}
		event.setDropItems(false);
	}
}
