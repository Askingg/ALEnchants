package me.syn.alenchant.main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.entity.Player;

import me.syn.alecho.main.Artefacts;
import me.syn.alenchant.enchants.EnchCombo;
import me.syn.algangs.main.ALGangsAPI;

public class Multi {

	public static List<String> order = new ArrayList<String>();
	public static HashMap<String, Double> permMultis = new HashMap<String, Double>();

	public static double total(Player p) {
		return 1.0 + + permission(p) + artefact(p) + gang(p) + combo(p);
	}

	public static double permission(Player p) {
		for (String str : order) {
			if (p.hasPermission("altokens.multi." + str)) {
				return permMultis.get(str);
			}
		}
		return 0.0;
	}

	public static double artefact(Player p) {
		return (double) Artefacts.getBoost(p.getName()) / 100;
	}

	public static double gang(Player p) {
		if (ALGangsAPI.hasGang(p)) {
			return (ALGangsAPI.getTokenBoost(ALGangsAPI.getGang(p)) + 0.0) / 100;
		}
		return 0.0;
	}
	
	public static double applyMulti(Player p, double d) {
		return Math.round(d * total(p));
	}
	
	public static double combo(Player p) {
		return (double) EnchCombo.getCombo(p)/100;
	}
}
