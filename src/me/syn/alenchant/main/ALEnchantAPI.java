package me.syn.alenchant.main;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;

import me.syn.alenchant.enchants.EnchCombo;
import me.syn.alenchant.enchants.EnchCreditDesire;
import me.syn.alenchant.enchants.EnchKeyDesire;
import me.syn.alenchant.enchants.EnchScavenger;
import me.syn.alenchant.enchants.EnchTokenDesire;
import me.syn.alenchant.utils.Format;
import me.syn.alenchant.utils.Message;
import me.syn.algangs.main.ALGangsAPI;
import me.syn.almisc.marry.MarryCore;

public class ALEnchantAPI {

	public static int enchantLevel(ItemStack i, String enchant) {
		return Enchants.level(i, enchant);
	}
	
	public static void blockBreak(Player p, Block b) {
		if (!WorldGuardPlugin.inst().canBuild(p, b))
			return;
		if (p.getGameMode() != GameMode.SURVIVAL)
			return;
		ItemStack i = p.getItemInHand();
		if (i != null) {
			if (i.getType().toString().contains("PICKAXE")) {
				if (EnchScavenger.chance(i)) {
					double t = EnchTokenDesire.amount(p, EnchTokenDesire.max(p, i), EnchTokenDesire.min(p, i));
					double tm = Multi.applyMulti(p, t);
					double c = EnchCreditDesire.amount(p, EnchCreditDesire.max(p, i), EnchCreditDesire.min(p, i));
					if (MarryCore.isMarried(p)) {
						Player pa = Bukkit.getPlayer(MarryCore.getPartner(p));
						if (pa == null) {
							Message.hover(p, Main.prefix + "&7You found &b" + Format.number(tm) + " &7eTokens & &5"
									+ Format.number(c)
									+ " &7gCredits &8(&dYour partner is offline&8)", "",
									"&b&l&nTokens:\n\n&7Max &b" + Format.decimals(0, EnchTokenDesire.max(p, i) + 0.0)
											+ "\n&7Min &b" + Format.decimals(0, EnchTokenDesire.min(p, i) + 0.0)
											+ "\n&7Base &b" + Format.decimals(0, t) + "\n&7Multi &b"
											+ Format.decimals(1, ((Multi.total(p) - 1) * 100)) + "%\n&7Total &b"
											+ Format.decimals(0, tm) + "\n\n\n&5&l&ngCredits:\n\n&7Max &5"
											+ Format.decimals(0, EnchCreditDesire.max(p, i) + 0.0) + "\n&7Min &5"
											+ Format.decimals(0, EnchCreditDesire.min(p, i) + 0.0) + "\n&7Total &5"
											+ Format.decimals(0, c) + "\n\n&7Partner: &d" + MarryCore.getPartner(p));
							Tokens.giveNoPM(p, tm);
							if (ALGangsAPI.hasGang(p)) {
								ALGangsAPI.addCredits(ALGangsAPI.getGang(p), c);
							}
						} else {
							Message.hover(p, Main.prefix + "&7You found &b" + Format.number(tm) + " &7eTokens & &5"
									+ Format.number(c)
									+ " &7gCredits &8(&dYour partner received " + MarryCore.getLoyalty(p) + "% of all rewards&8)",
									"",
									"&b&l&nTokens:\n\n&7Max &b" + Format.decimals(0, EnchTokenDesire.max(p, i) + 0.0)
											+ "\n&7Min &b" + Format.decimals(0, EnchTokenDesire.min(p, i) + 0.0)
											+ "\n&7Base &b" + Format.decimals(0, t) + "\n&7Multi &b"
											+ Format.decimals(1, ((Multi.total(p) - 1) * 100)) + "%\n&7Total &b"
											+ Format.decimals(0, tm) + "\n\n\n&5&l&ngCredits:\n\n&7Max &5"
											+ Format.decimals(0, EnchCreditDesire.max(p, i) + 0.0) + "\n&7Min &5"
											+ Format.decimals(0, EnchCreditDesire.min(p, i) + 0.0) + "\n&7Total &5"
											+ Format.decimals(0, c) + "\n\n&7Partner: &d" + MarryCore.getPartner(p));
							Message.player(Main.prefix + "&7You received &b" + Format.number(tm * MarryCore.getLoyaltyMultiplier(p.getName())) + " &7eTokens & &5"
									+ Format.number(c * MarryCore.getLoyaltyMultiplier(p.getName())) + " &7gCredits from your partner's Scavenger", pa);
							Tokens.giveNoPM(p, tm);
							Tokens.giveNoPM(pa, (tm * MarryCore.getLoyaltyMultiplier(p.getName())));
							if (ALGangsAPI.hasGang(p)) {
								ALGangsAPI.addCredits(ALGangsAPI.getGang(p), c);
							}
							if (ALGangsAPI.hasGang(pa)) {
								ALGangsAPI.addCredits(ALGangsAPI.getGang(pa), (c * MarryCore.getLoyaltyMultiplier(p.getName())));
							}
						}
					} else {
						Message.hover(p,
								Main.prefix + "&7You found &b" + Format.number(tm) + " &7eTokens & &5"
										+ Format.number(c)
										+ " &7gCredits &8(&dYou're not married&8)",
								"",
								"&b&l&nTokens:\n\n&7Max &b" + Format.decimals(0, EnchTokenDesire.max(p, i) + 0.0)
										+ "\n&7Min &b" + Format.decimals(0, EnchTokenDesire.min(p, i) + 0.0)
										+ "\n&7Base &b" + Format.decimals(0, t) + "\n&7Multi &b"
										+ Format.decimals(1, ((Multi.total(p) - 1) * 100)) + "%\n&7Total &b"
										+ Format.decimals(0, tm) + "\n\n\n&5&l&ngCredits:\n\n&7Max &5"
										+ Format.decimals(0, EnchCreditDesire.max(p, i) + 0.0) + "\n&7Min &5"
										+ Format.decimals(0, EnchCreditDesire.min(p, i) + 0.0) + "\n&7Total &5"
										+ Format.decimals(0, c));
						Tokens.giveNoPM(p, tm);
						if (ALGangsAPI.hasGang(p)) {
							ALGangsAPI.addCredits(ALGangsAPI.getGang(p), c);
						}
					}
				}
				if (EnchKeyDesire.chance(i)) {
					EnchKeyDesire.giveKeys(p, i);
				}
				EnchCombo.blockBreak(p);
				p.getItemInHand().setDurability((short) 0);
				p.updateInventory();
			}
		}
	}
	
	public static void blockBreak(Player p, Location l) {
		if (!WorldGuardPlugin.inst().canBuild(p, l))
			return;
		if (p.getGameMode() != GameMode.SURVIVAL)
			return;
		ItemStack i = p.getItemInHand();
		if (i != null) {
			if (i.getType().toString().contains("PICKAXE")) {
				if (EnchScavenger.chance(i)) {
					double t = EnchTokenDesire.amount(p, EnchTokenDesire.max(p, i), EnchTokenDesire.min(p, i));
					double tm = Multi.applyMulti(p, t);
					double c = EnchCreditDesire.amount(p, EnchCreditDesire.max(p, i), EnchCreditDesire.min(p, i));
					if (MarryCore.isMarried(p)) {
						Player pa = Bukkit.getPlayer(MarryCore.getPartner(p));
						if (pa == null) {
							Message.hover(p, Main.prefix + "&7You found &b" + Format.number(tm) + " &7eTokens & &5"
									+ Format.number(c)
									+ " &7gCredits &8(&dYour partner is offline&8)", "",
									"&b&l&nTokens:\n\n&7Max &b" + Format.decimals(0, EnchTokenDesire.max(p, i) + 0.0)
											+ "\n&7Min &b" + Format.decimals(0, EnchTokenDesire.min(p, i) + 0.0)
											+ "\n&7Base &b" + Format.decimals(0, t) + "\n&7Multi &b"
											+ Format.decimals(1, ((Multi.total(p) - 1) * 100)) + "%\n&7Total &b"
											+ Format.decimals(0, tm) + "\n\n\n&5&l&ngCredits:\n\n&7Max &5"
											+ Format.decimals(0, EnchCreditDesire.max(p, i) + 0.0) + "\n&7Min &5"
											+ Format.decimals(0, EnchCreditDesire.min(p, i) + 0.0) + "\n&7Total &5"
											+ Format.decimals(0, c) + "\n\n&7Partner: &d" + MarryCore.getPartner(p));
							Tokens.giveNoPM(p, tm);
							if (ALGangsAPI.hasGang(p)) {
								ALGangsAPI.addCredits(ALGangsAPI.getGang(p), c);
							}
						} else {
							Message.hover(p, Main.prefix + "&7You found &b" + Format.number(tm) + " &7eTokens & &5"
									+ Format.number(c)
									+ " &7gCredits &8(&dYour partner received " + MarryCore.getLoyalty(p) + "% of all rewards&8)",
									"",
									"&b&l&nTokens:\n\n&7Max &b" + Format.decimals(0, EnchTokenDesire.max(p, i) + 0.0)
											+ "\n&7Min &b" + Format.decimals(0, EnchTokenDesire.min(p, i) + 0.0)
											+ "\n&7Base &b" + Format.decimals(0, t) + "\n&7Multi &b"
											+ Format.decimals(1, ((Multi.total(p) - 1) * 100)) + "%\n&7Total &b"
											+ Format.decimals(0, tm) + "\n\n\n&5&l&ngCredits:\n\n&7Max &5"
											+ Format.decimals(0, EnchCreditDesire.max(p, i) + 0.0) + "\n&7Min &5"
											+ Format.decimals(0, EnchCreditDesire.min(p, i) + 0.0) + "\n&7Total &5"
											+ Format.decimals(0, c) + "\n\n&7Partner: &d" + MarryCore.getPartner(p));
							Message.player(Main.prefix + "&7You received &b" + Format.number(tm * MarryCore.getLoyaltyMultiplier(p.getName())) + " &7eTokens & &5"
									+ Format.number(c * MarryCore.getLoyaltyMultiplier(p.getName())) + " &7gCredits from your partner's Scavenger", pa);
							Tokens.giveNoPM(p, tm);
							Tokens.giveNoPM(pa, (tm * MarryCore.getLoyaltyMultiplier(p.getName())));
							if (ALGangsAPI.hasGang(p)) {
								ALGangsAPI.addCredits(ALGangsAPI.getGang(p), c);
							}
							if (ALGangsAPI.hasGang(pa)) {
								ALGangsAPI.addCredits(ALGangsAPI.getGang(pa), (c * MarryCore.getLoyaltyMultiplier(p.getName())));
							}
						}
					} else {
						Message.hover(p,
								Main.prefix + "&7You found &b" + Format.number(tm) + " &7eTokens & &5"
										+ Format.number(c)
										+ " &7gCredits &8(&dYou're not married&8)",
								"",
								"&b&l&nTokens:\n\n&7Max &b" + Format.decimals(0, EnchTokenDesire.max(p, i) + 0.0)
										+ "\n&7Min &b" + Format.decimals(0, EnchTokenDesire.min(p, i) + 0.0)
										+ "\n&7Base &b" + Format.decimals(0, t) + "\n&7Multi &b"
										+ Format.decimals(1, ((Multi.total(p) - 1) * 100)) + "%\n&7Total &b"
										+ Format.decimals(0, tm) + "\n\n\n&5&l&ngCredits:\n\n&7Max &5"
										+ Format.decimals(0, EnchCreditDesire.max(p, i) + 0.0) + "\n&7Min &5"
										+ Format.decimals(0, EnchCreditDesire.min(p, i) + 0.0) + "\n&7Total &5"
										+ Format.decimals(0, c));
						Tokens.giveNoPM(p, tm);
						if (ALGangsAPI.hasGang(p)) {
							ALGangsAPI.addCredits(ALGangsAPI.getGang(p), c);
						}
					}
				}
				if (EnchKeyDesire.chance(i)) {
					EnchKeyDesire.giveKeys(p, i);
				}
				EnchCombo.blockBreak(p);
				p.getItemInHand().setDurability((short) 0);
				p.updateInventory();
			}
		}
	}
}
