package me.syn.alenchant.enchants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import me.syn.alenchant.main.Enchants;
import me.syn.alenchant.main.Main;
import me.syn.algangs.utils.Message;

public class EnchCombo {

	public static HashMap<Player, Integer> combo = new HashMap<Player, Integer>();
	public static List<Player> noReset = new ArrayList<Player>();
	public static long resetTime = 0;

	public static int getCombo(Player p) {
		if (combo.containsKey(p)) {
			return combo.get(p);
		}
		return 0;
	}

	public static void addCombo(Player p) {
		if (combo.containsKey(p)) {
			combo.put(p, getCombo(p) + 1);
		} else {
			combo.put(p, 1);
		}
		Message.player(Main.prefix + "Your combo increased to &b" + getCombo(p), p);
	}

	public static void endCombo(Player p) {
		combo.remove(p);
	}

	public static double getIncreaseChance(Player p) {
		double d = 0.001;
		if (p.getItemInHand() != null) {
			int lvl = Enchants.level(p.getItemInHand(), "Combo");
			if (lvl > 0) {
				d = d + ((double) lvl / 25000);
			}
		}
		return d;
	}

	public static void blockBreak(Player p) {
		Random r = new Random();
		double d = r.nextDouble();
		if (d < getIncreaseChance(p)) {
			addCombo(p);
		}
		if (!noReset.contains(p)) {
			noReset.add(p);
		}
	}

	@SuppressWarnings("deprecation")
	public static void resetTimer(Main main) {
		Bukkit.getServer().getScheduler().scheduleAsyncRepeatingTask(main, new Runnable() {
			@Override
			public void run() {
				resetTime = (System.currentTimeMillis() / 1000) + 60;
				if (combo.size() > 0) {
					for (Player p : combo.keySet()) {
						if (!noReset.contains(p)) {
							Message.player(Main.prefix + "Your &bCombo&8 (&b" + getCombo(p) + "&8)&7 has ended", p);
							endCombo(p);
						} else {
							noReset.remove(p);
						}
					}
				}
			}
		}, 0, 60 * 20);

	}
}
