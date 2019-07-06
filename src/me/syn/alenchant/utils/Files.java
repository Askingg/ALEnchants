package me.syn.alenchant.utils;

import java.io.File;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import me.syn.alenchant.main.Main;
import me.syn.alenchant.main.Multi;

public class Files {

	public static File dataFile;
	public static FileConfiguration data;

	public static void base() {
		Main main = Main.getPlugin(Main.class);
		if (!main.getDataFolder().exists()) {
			main.getDataFolder().mkdirs();
			Message.console(
					Main.prefix + "&7Created the '&b" + main.getDataFolder().getName().toString() + "&7' folder");
		}
		dataFile = new File(main.getDataFolder(), "data.yml");
		if (!dataFile.exists()) {
			main.saveResource("data.yml", false);
			Message.console(Main.prefix + "&7Created the '&bdata.yml&7' file");
		}
		data = YamlConfiguration.loadConfiguration(dataFile);
		for (String str : data.getConfigurationSection("tokens").getKeys(false)) {
			Main.tokens.put(str, data.getDouble("tokens." + str));
		}
		for (String str : data.getConfigurationSection("multipliers").getKeys(false)) {
			Multi.order.add(str);
			Multi.permMultis.put(str, data.getDouble("multipliers." + str));
		}
	}
}