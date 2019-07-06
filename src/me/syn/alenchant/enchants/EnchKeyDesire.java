package me.syn.alenchant.enchants;

import java.util.Random;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.syn.alcrates.main.Keys;
import me.syn.alcrates.main.Main;
import me.syn.alenchant.main.Enchants;
import me.syn.algangs.utils.Message;

public class EnchKeyDesire {

	public static double getChance(ItemStack i) {
		int lvl = Enchants.level(i, "KeyDesire");
		double d = 0.002;
		if (lvl > 0) {
			d += (d / 10) * lvl;
		}
		return d;
	}

	public static boolean chance(ItemStack i) {
		Random r = new Random();
		if (r.nextDouble() <= getChance(i))
			return true;
		return false;
	}

	public static int amount(ItemStack i) {
		int lvl = Enchants.level(i, "KeyDesire");
		return lvl + 1;
	}

	public static String keyType() {
		Random r = new Random();
		int x = r.nextInt(1000) + 1;
		if (x <= 400)
			return "Common";
		if (x > 400 && x <= 700)
			return "Uncommon";
		if (x > 700 && x <= 900)
			return "Rare";
		if (x > 900 && x <= 950)
			return "Epic";
		if (x > 950 && x <= 990)
			return "Legendary";
		if (x > 990 && x <= 999)
			return "Ultimate";
		if (x > 999 && x <= 1000)
			return "Insane";
		return null;
	}
	
	public static void giveKeys(Player p, ItemStack i) {
		if (keyType().equals(null)) {
			Message.broadcast("&4&lERROR: &cIn KeyDesire Enchantment");
			return;
		}
		String k = keyType();
		int a = amount(i);
		Message.player(Main.prefix + "You found &b" + a + " " + k + "&7 keys", p);
		Keys.giveKeys(p.getName(),k, a);
	}
}
