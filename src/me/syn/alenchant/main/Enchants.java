package me.syn.alenchant.main;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import me.syn.alenchant.utils.Format;

public class Enchants {

	public static int max(String ench) {
		if (ench.equalsIgnoreCase("efficiency"))
			return 250;
		if (ench.equalsIgnoreCase("fortune"))
			return 10000;
		if (ench.equalsIgnoreCase("Scavenger"))// Event to find tokens/credits
			return 100;
		if (ench.equalsIgnoreCase("AutoSell")) // Automatically sell blocks
			return 1;
		if (ench.equalsIgnoreCase("AutoSmelt")) // Automatically smelt ores
			return 1;
		if (ench.equalsIgnoreCase("TokenDesire")) // Find more tokens
			return 5000;
		if (ench.equalsIgnoreCase("Greed")) // Get more money
			return 5000;
		if (ench.equalsIgnoreCase("CreditDesire")) // Find more gang credits
			return 2500;
		if (ench.equalsIgnoreCase("KeyDesire")) // Find more keys
			return 10;
		if (ench.equalsIgnoreCase("Flight")) // Be able to fly
			return 1;
		if (ench.equalsIgnoreCase("Speed")) // Permanent speed effect
			return 5;
		if (ench.equalsIgnoreCase("Haste")) // Permanent haste effect
			return 5;
		if (ench.equalsIgnoreCase("Combo")) // Permanent haste effect
			return 500;
		return 0;
	}

	public static double cost(ItemStack i, String ench) {
		if (ench.equalsIgnoreCase("efficiency")) {
			return Format.removeDecimals(100 * Math.pow(1.01, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("fortune")) {
			return Format.removeDecimals(100 * Math.pow(1.008, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("Scavenger")) {
			return Format.removeDecimals(100 * Math.pow(1.0575, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("AutoSell"))
			return 100000;
		if (ench.equalsIgnoreCase("AutoSmelt"))
			return 50000;
		if (ench.equalsIgnoreCase("TokenDesire")) {
			return Format.removeDecimals(200 * Math.pow(1.02, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("Greed")) {
			return Format.removeDecimals(250 * Math.pow(1.03, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("CreditDesire")) {
			return Format.removeDecimals(750 * Math.pow(1.04, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("KeyDesire")) {
			return Format.removeDecimals(10000 * Math.pow(5, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("Flight"))
			return 75000;
		if (ench.equalsIgnoreCase("Speed")) {
			return Format.removeDecimals(500 * Math.pow(2, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("Haste")) {
			return Format.removeDecimals(500 * Math.pow(2, level(i, ench)));
		}
		if (ench.equalsIgnoreCase("Combo")) {
			return Format.removeDecimals(250 * Math.pow(1.0225, level(i, ench)));
		}
		return 0;
	}

	public static double cost(int i, String ench) {
		if (ench.equalsIgnoreCase("efficiency")) {
			return Format.removeDecimals(100 * Math.pow(1.01, i));
		}
		if (ench.equalsIgnoreCase("fortune")) {
			return Format.removeDecimals(100 * Math.pow(1.008, i));
		}
		if (ench.equalsIgnoreCase("Scavenger")) {
			return Format.removeDecimals(100 * Math.pow(1.0575, i));
		}
		if (ench.equalsIgnoreCase("AutoSell"))
			return 100000;
		if (ench.equalsIgnoreCase("AutoSmelt"))
			return 50000;
		if (ench.equalsIgnoreCase("TokenDesire")) {
			return Format.removeDecimals(200 * Math.pow(1.02, i));
		}
		if (ench.equalsIgnoreCase("Greed")) {
			return Format.removeDecimals(250 * Math.pow(1.03, i));
		}
		if (ench.equalsIgnoreCase("CreditDesire")) {
			return Format.removeDecimals(750 * Math.pow(1.04, i));
		}
		if (ench.equalsIgnoreCase("KeyDesire")) {
			return Format.removeDecimals(10000 * Math.pow(5, i));
		}
		if (ench.equalsIgnoreCase("Flight"))
			return 75000;
		if (ench.equalsIgnoreCase("Speed")) {
			return Format.removeDecimals(500 * Math.pow(2, i));
		}
		if (ench.equalsIgnoreCase("Haste")) {
			return Format.removeDecimals(500 * Math.pow(2, i));
		}
		if (ench.equalsIgnoreCase("Combo")) {
			return Format.removeDecimals(250 * Math.pow(1.0225, i));
		}
		return 0;
	}

	public static int level(ItemStack i, String ench) {
		if (i == null) {
			return 0;
		}
		if (i.hasItemMeta()) {
			ItemMeta m = i.getItemMeta();
			if (m.hasLore()) {
				for (String l : m.getLore()) {
					if (l.contains(ench)) {
						return Integer.valueOf(ChatColor.stripColor(Format.color(l)).split(" ")[1]);
					}
				}
			}
		}
		return 0;
	}
}
