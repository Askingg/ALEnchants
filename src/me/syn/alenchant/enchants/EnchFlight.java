package me.syn.alenchant.enchants;

import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class EnchFlight {

	public static void enable(Player p) {
		p.setAllowFlight(true);
		p.setFlying(true);
	}

	public static  void disable(Player p) {
		if (p.getGameMode() == GameMode.CREATIVE || p.getGameMode() == GameMode.SPECTATOR) {
			return;
		}
		if (!p.hasPermission("essentials.fly")) {
			p.setAllowFlight(false);
			p.setFlying(false);
		}
	}
}
