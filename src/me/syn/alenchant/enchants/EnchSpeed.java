package me.syn.alenchant.enchants;

import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class EnchSpeed {

	public static void enable(Player p, int i) {
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, Integer.MAX_VALUE, i, true, false));
	}

	public static  void disable(Player p) {
		p.removePotionEffect(PotionEffectType.SPEED);
	}
}
