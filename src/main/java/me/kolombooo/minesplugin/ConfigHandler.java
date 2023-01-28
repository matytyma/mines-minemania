package me.kolombooo.minesplugin;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ConfigHandler {
	static Map<String, Integer> dropChances = new HashMap<>();
	static Map<String, Integer> rewards = new HashMap<>();
	static String prefix;
	static String onlyPlayers;
	static String noPermission;
	static String pluginInfo;
	static String successfulReload;
	static String help;
	static String helpReload;
	static String teleportHelp;
	static String teleportHelpVip;
	static String teleportHelpAdmin;

	static void reloadConfig() {
		Main plugin = Main.getPlugin(Main.class);
		plugin.saveDefaultConfig();
		plugin.reloadConfig();
		FileConfiguration config = plugin.getConfig().options().copyDefaults(true).configuration();
		dropChances.clear();
		for (String s : Objects.requireNonNull(config.getConfigurationSection("drop-chances")).getKeys(false)) {
			dropChances.put(s, config.getInt("drop-chances." + s));
		}
		rewards.clear();
		for (String s : Objects.requireNonNull(config.getConfigurationSection("rewards")).getKeys(false)) {
			rewards.put(s, config.getInt("rewards." + s));
		}
		ConfigurationSection messages = Objects.requireNonNull(config.getConfigurationSection("messages"));
		prefix = (String) messages.get("prefix");
		onlyPlayers = (String) messages.get("only-players");
		noPermission = (String) messages.get("no-permission");
		pluginInfo = (String) messages.get("plugin-info");
		successfulReload = (String) messages.get("successful-reload");
		help = (String) messages.get("help.normal");
		helpReload = (String) messages.get("help.reload");
		teleportHelp = (String) messages.get("teleport-help.normal");
		teleportHelpVip = (String) messages.get("teleport-help.vip");
		teleportHelpAdmin = (String) messages.get("teleport-help.admin");
	}
}
