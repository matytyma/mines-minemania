package me.kolombooo.minesplugin;


public class RespawnChecker implements Runnable {
	@Override
	public void run() {
		while (BlocksHandler.blocks.size() != 0 && BlocksHandler.blocks.peek().respawnTime <= System.currentTimeMillis()) {
			BrokenOre ore = BlocksHandler.blocks.poll();
			ore.location.getWorld().setBlockData(ore.location, ore.blockData);
		}
	}
}
