package me.syn.alenchant.enchants;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.syn.alenchant.main.Enchants;
import me.syn.alenchant.main.Main;
import me.syn.alenchant.main.Tokens;
import me.syn.alenchant.utils.Format;
import me.syn.alenchant.utils.Message;

public class EnchTokenDesire {

	public static int max(Player p, ItemStack i) {
		int x = 300;
		int l = Enchants.level(i, "TokenDesire");
		if (l > 0) {
			x += l * 80;
		}
		return x;
	}

	public static int min(Player p, ItemStack i) {
		int x = 90;
		int l = Enchants.level(i, "TokenDesire");
		if (l > 0) {
			x += l * 30;
		}
		return x;
	}

	public static double amount(Player p, int max, int min) {
		Random r = new Random();
		return r.nextInt(max - min) + min;
	}

	public static void tokenEvent(Player p, ItemStack i, double d) {
		Tokens.give(p, d);
		Message.hover(p, Main.prefix + "&7You found &b" + Format.number(d) + "&7 Tokens &8 &8 (&bhover&8)", "token bal",
				"&b&l&nTokens:\n\n&7Max &b" + Format.decimals(0, max(p, i) + 0.0) + "\n&7Min &b"
						+ Format.decimals(0, min(p, i) + 0.0) + "\n&7Total &b" + Format.decimals(0, d));
	}
}
