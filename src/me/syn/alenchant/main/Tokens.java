package me.syn.alenchant.main;

import org.bukkit.entity.Player;

import me.syn.alenchant.utils.Format;
import me.syn.alenchant.utils.Message;

public class Tokens {

	public static double bal(Player p) {
		return Main.tokens.get(p.getUniqueId().toString());
	}

	public static boolean hasTokens(Player p, double d) {
		if (bal(p) >= d) {
			return true;
		}
		return false;
	}
	
	public static void giveMulti(Player p, double d) {
		d = Multi.applyMulti(p, d);
		Main.tokens.put(p.getUniqueId().toString(), Main.tokens.get(p.getUniqueId().toString()) + d);
		Message.player(Main.prefix + "&b+ " + Format.number(d) + " tokens", p);
	}

	public static void giveNoPMMulti(Player p, double d) {
		d = Multi.applyMulti(p, d);
		Main.tokens.put(p.getUniqueId().toString(), Main.tokens.get(p.getUniqueId().toString()) + d);
	}
	
	public static void give(Player p, double d) {
		Main.tokens.put(p.getUniqueId().toString(), Main.tokens.get(p.getUniqueId().toString()) + d);
		Message.player(Main.prefix + "&b+ " + Format.number(d) + " tokens", p);
	}

	public static void giveNoPM(Player p, double d) {
		Main.tokens.put(p.getUniqueId().toString(), Main.tokens.get(p.getUniqueId().toString()) + d);
	}

	public static void remove(Player p, double d) {
		Main.tokens.put(p.getUniqueId().toString(), Main.tokens.get(p.getUniqueId().toString()) - d);
		Message.player(Main.prefix + "&c- " + Format.number(d) + " tokens", p);
	}

	public static void removeNoPM(Player p, double d) {
		Main.tokens.put(p.getUniqueId().toString(), Main.tokens.get(p.getUniqueId().toString()) - d);
	}
}
