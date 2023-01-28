package me.kolombooo.minesplugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class Main extends JavaPlugin {
	@Override
	public void onEnable() {
		ConfigHandler.reloadConfig();
		Objects.requireNonNull(getCommand("mines")).setExecutor(new MinesCommand());
		Bukkit.getPluginManager().registerEvents(new MineListener(), this);
		Bukkit.getScheduler().runTaskTimer(this, new RespawnChecker(), 0, 20);
	}

	@Override
	public void onDisable() {
		for (BrokenOre ore : BlocksHandler.blocks) {
			ore.location.getWorld().setBlockData(ore.location, ore.blockData);
		}
	}
}
