package me.kolombooo.minesplugin;

import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConfigHandler {
	static Map<String, Integer> dropChances = new HashMap<>();
	static Map<String, Integer> rewards = new HashMap<>();

	static void reloadConfig() {
		Main plugin = Main.getPlugin(Main.class);
		plugin.saveDefaultConfig();
		FileConfiguration config = plugin.getConfig().options().copyDefaults(true).configuration();
		dropChances.clear();
		for (String s : Objects.requireNonNull(config.getConfigurationSection("drop-chance")).getKeys(false)) {
			dropChances.put(s, config.getInt("drop-chances." + s));
		}
		rewards.clear();
		for (String s : Objects.requireNonNull(config.getConfigurationSection("rewards")).getKeys(false)) {
			rewards.put(s, config.getInt("rewards." + s));
		}
	}
}
