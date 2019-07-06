package me.syn.alenchant.enchants;

import java.util.Random;

import org.bukkit.inventory.ItemStack;

import me.syn.alenchant.main.Enchants;

public class EnchScavenger {

	public static double getChance(ItemStack i) {
		double d = 0.001;
		int lvl = Enchants.level(i, "Scavenger");
		if (lvl > 0) {
			d += 0.00004 * lvl;
		}
		return d;
	}

	public static boolean chance(ItemStack i) {
		Random r = new Random();
		if (r.nextDouble() <= getChance(i))
			return true;
		return false;
	}
}
