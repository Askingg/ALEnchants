package me.syn.alenchant.main;

import org.bukkit.entity.Player;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import me.syn.alcore.Core;
import me.syn.alenchant.enchants.EnchCombo;
import me.syn.alenchant.utils.Format;
import me.syn.almisc.marry.MarryCore;

public class Placeholders extends PlaceholderExpansion {

	public String getIdentifier() {
		return "altokens";
	}

	public String getPlugin() {
		return null;
	}

	public String getAuthor() {
		return "Synysterrr";
	}

	public String getVersion() {
		return "1.0";
	}

	public String onPlaceholderRequest(Player p, String identifier) {

		// Placeholder: %altokens_tokens_balance%
		if (identifier.equalsIgnoreCase("tokens_balance")) {
			return Format.number(Tokens.bal(p));
		}

		// Placeholder: %altokens_multi_decimal%
		if (identifier.equalsIgnoreCase("multi_decimal")) {
			return Format.decimals(3, Multi.total(p) - 1);
		}

		// Placeholder: %altokens_multi_percent%
		if (identifier.equalsIgnoreCase("multi_percent")) {
			return Format.decimals(1, (Multi.total(p) - 1) * 100) + "%";
		}

		// Placeholder: %altokens_combo%
		if (identifier.equalsIgnoreCase("combo")) {
			if (EnchCombo.noReset.contains(p))
				return "&b" + Core.decimals(0, EnchCombo.getCombo(p));
			return "&c" + Core.decimals(0, EnchCombo.getCombo(p));
		}

		// Placeholder: %altokens_combo_reset%
		if (identifier.equalsIgnoreCase("combo_reset")) {
			if (EnchCombo.noReset.contains(p))
				return "&b" + Core.time((int) (EnchCombo.resetTime - (System.currentTimeMillis() / 1000)) + 1);
			return "&c" + Core.time((int) (EnchCombo.resetTime - (System.currentTimeMillis() / 1000)) + 1);
		}

		// Placeholder: %altokens_marryprefix%
		if (identifier.equalsIgnoreCase("marryprefix")) {
			if (MarryCore.isMarried(p)) {
				return "&4❤&8 &l|&7 ";
			}
			return "";
		}

		// Placeholder: %altokens_marrypartner%
		if (identifier.equalsIgnoreCase("marrypartner")) {
			if (MarryCore.isMarried(p)) {
				return MarryCore.getPartner(p);
			}
			return "";
		}

		// Placeholder: %altokens_marrypartner%
		if (identifier.equalsIgnoreCase("marrypartner")) {
			if (MarryCore.isMarried(p)) {
				return MarryCore.getPartner(p);
			}
			return "";
		}

		return null;
	}
}