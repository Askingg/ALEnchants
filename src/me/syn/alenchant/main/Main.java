package me.syn.alenchant.main;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import me.syn.alenchant.commands.ALComboCMD;
import me.syn.alenchant.commands.ALCustomEnchantCMD;
import me.syn.alenchant.commands.ALTokenCMD;
import me.syn.alenchant.enchants.EnchCombo;
import me.syn.alenchant.events.BlockBreak;
import me.syn.alenchant.events.ItemHold;
import me.syn.alenchant.events.PlayerJoin;
import me.syn.alenchant.gui.EnchantGUI;
import me.syn.alenchant.utils.Files;
import me.syn.alenchant.utils.Message;

public class Main extends JavaPlugin {

	public static String prefix = "&8&l(&b&l!&8&l)&b &l»&7 ";
	public static HashMap<String, Double> tokens = new HashMap<String, Double>(); // UUID, TokenBal

	public void onEnable() {
		Message.console("&7");

		Files.base();
		EnchantGUI.setUpDescriptionsp();
		getServer().getPluginManager().registerEvents(new PlayerJoin(), this);
		getServer().getPluginManager().registerEvents(new EnchantGUI(), this);
		getServer().getPluginManager().registerEvents(new BlockBreak(), this);
		getServer().getPluginManager().registerEvents(new ItemHold(), this);
		getCommand("altokens").setExecutor(new ALTokenCMD());
		getCommand("alcustomenchant").setExecutor(new ALCustomEnchantCMD());
		getCommand("alcombo").setExecutor(new ALComboCMD());
		if (Bukkit.getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
			new Placeholders().register();
		}
		EnchCombo.resetTimer(this);

		Message.console("&7");
	}

	public void onDisable() {
		if (tokens.size() > 0) {
			for (String str : tokens.keySet()) {
				Files.data.set("tokens." + str, tokens.get(str));
			}
			try {
				Files.data.save(Files.dataFile);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}
}
