package me.syn.alenchant.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.syn.alcore.Core;
import me.syn.alenchant.main.Main;
import me.syn.alenchant.main.Multi;
import me.syn.alenchant.main.Tokens;
import me.syn.alenchant.utils.Format;
import me.syn.alenchant.utils.Message;

public class ALTokenCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
		if (args.length == 0) {
			Message.sender(Main.prefix + "Commands for &bAfterLife-Tokens", sender);
			Message.sender("&8 ● &b/ALToken&7 View the help list", sender);
			Message.sender("&8 ● &b/ALToken Balance&7 View a user's token balance", sender);
			Message.sender("&8 ● &b/ALToken Baltop&7 View the top token balances", sender);
			Message.sender("&8 ● &b/ALToken Pay&7 Pay a user tokens", sender);
			Message.sender("&8 ● &b/ALToken Multi&7 View your token multiplier", sender);
			Message.sender("&8 ● &b/ALToken MultiTop&7 View the top token multipliers", sender);
			Message.sender("&8 ● &b/ALToken Give&7 Give a user tokens", sender);
			Message.sender("&8 ● &b/ALToken Remove&7 Remove tokens from a user's balance", sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("balance") || args[0].equalsIgnoreCase("bal")) {
				if (args.length == 1) { // ALToken Bal
					if (sender instanceof Player) {
						Message.sender(Main.prefix + "You have &b"
								+ Format.decimals(0, Main.tokens.get(((Player) sender).getUniqueId().toString()))
								+ "&7 tokens", sender);
						return true;
					}
				}
				if (args.length == 2) { // Token Bal <Player>
					Player p = Bukkit.getPlayer(args[1]);
					if (p == null) {
						Message.sender(Main.prefix + "Sorry, but &c" + args[1] + "&7 is an invalid player", sender);
						return true;
					}
					Message.sender(Main.prefix + "&b" + args[1] + "&7 has &b"
							+ Format.decimals(0, Main.tokens.get(((Player) sender).getUniqueId().toString()))
							+ "&7 tokens", sender);
					return true;
				}
				Message.sender(Main.prefix + "Usage &b/ALToken " + args[0].toLowerCase() + " <Player>", sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("top") || args[0].equalsIgnoreCase("baltop")) {
				Core.message(Core.prefix("b") + "Sorry, but this command is temporarily disabled", sender);
				// int x = 1;
				// if (args.length >= 2) {
				// try {
				// x = Integer.parseInt(args[1]);
				// } catch (Exception ex) {
				// Message.sender(Core.prefix("a") + "&7Sorry, but &C" + args[1] + "&7 is an
				// invalid integer",
				// sender);
				// return true;
				// }
				// if (x < 1)
				// x = 1;
				// }
				// Core.leaderboardDouble("Token Balances", "b", Main.tokens, 0, x, 15, false,
				// sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("pay")) {// Token Pay <Player> <Amount>
				if (args.length >= 3) {
					Player p = (Player) sender;
					Player p2 = Bukkit.getPlayer(args[1]);
					if (p2 == null) {
						Message.player(Main.prefix + "Sorrry, but &c" + args[1] + "&7 is an invalid player", p);
						return true;
					}
					int x = 0;
					try {
						x = Integer.parseInt(args[2]);
					} catch (Exception ex) {
						Message.player(Main.prefix + "Sorry, but &c" + args[2] + "&7 is an invalid integer", p);
						return true;
					}
					if (x < 1) {
						Message.player(Main.prefix + "Sorry, but the amount must be &c> 0", p);
						return true;
					}
					if (p.equals(p2)) {
						Message.player(Main.prefix + "Sorry, but you cannot pay yourself", p);
						return true;
					}
					if (!Tokens.hasTokens(p, x + 0.0)) {
						Message.player(Main.prefix + "Sorry, but you don't have enough Tokens", p2);
						return true;
					}
					Tokens.remove(p, x);
					Tokens.give(p2, x + 0.0);
					Message.player(
							Main.prefix + "You paid &a" + Format.decimals(0, x + 0.0) + "&7 Tokens to &a" + args[1], p);
					Message.player(Main.prefix + "You received &a" + Format.decimals(0, x + 0.0) + "&7 Tokens from &a"
							+ p.getName(), p2);
				} else {
					Message.sender(Main.prefix + "Usage&a /Tokens Pay <Player> <Amount>", sender);
					return true;
				}
				return true;
			}
			if ((args[0].equalsIgnoreCase("multi")) || (args[0].equalsIgnoreCase("multiplier"))) {
				if ((sender instanceof Player)) {
					Player p = (Player) sender;
					Message.player(Main.prefix + "Your current token multiplier information:", p);
					Message.player("&8 ● &7Total: &b&l"
							+ Format.decimals(Integer.valueOf(1), Double.valueOf((Multi.total(p) - 1.0D) * 100.0D))
							+ "%", p);
					Message.player("&7", p);
					Message.player("&8 ● &7Permission: &b"
							+ Format.decimals(Integer.valueOf(1), Double.valueOf(Multi.permission(p) * 100.0D)) + "%",
							p);
					Message.player("&8 ● &7EchoArtefact: &b"
							+ Format.decimals(Integer.valueOf(1), Double.valueOf(Multi.artefact(p) * 100.0D)) + "%", p);
					Message.player(
							"&8 ● &7GangBoost: &b"
									+ Format.decimals(Integer.valueOf(1), Double.valueOf(Multi.gang(p) * 100.0D)) + "%",
							p);
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("multitop")) {
				if (Bukkit.getOnlinePlayers().size() > 0) {
					HashMap<String, Double> m = new HashMap<String, Double>();
					int x = 1;
					if (args.length >= 2) {
						try {
							x = Integer.parseInt(args[1]);
						} catch (Exception ex) {
							Message.sender(Core.prefix("a") + "&7Sorry, but &C" + args[1] + "&7 is an invalid integer",
									sender);
							return true;
						}
						if (x < 1)
							x = 1;
					}
					Core.leaderboardDouble("Token Balances", "b", m, 0, x, 15, false, sender);
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("give")) {
				if (((sender instanceof ConsoleCommandSender)) || (sender.hasPermission("altokens.commands.give"))) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							Message.sender(Main.prefix + "Sorry, but &C" + args[1] + "&7 is an invalid player", sender);
							return true;
						}
						int x = 0;
						try {
							x = Integer.parseInt(args[2]);
						} catch (Exception ex) {
							Message.sender(Main.prefix + "Sorry, but &c" + args[2] + "&7 is an invalid integer",
									sender);
							return true;
						}
						if (x > 0) {
							Tokens.give(p, x + 0.0D);
							Message.sender(Main.prefix + "&7You gave &b"
									+ Format.decimals(Integer.valueOf(0), Double.valueOf(x + 0.0D)) + "&7 tokens to &b"
									+ args[1], sender);
							return true;
						}
						Message.sender(Main.prefix + "Sorry, but the amount must be greater than 0", sender);
						return true;
					}

					Message.sender(Main.prefix + "usage &b/ALToken Give <Player> <Amount>", sender);
					return true;
				}

				Message.noPermission(sender);
				return true;
			}

			if (args[0].equalsIgnoreCase("remove")) {
				if (((sender instanceof ConsoleCommandSender)) || (sender.hasPermission("altokens.commands.remove"))) {
					if (args.length == 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							Message.sender(Main.prefix + "Sorry, but &C" + args[1] + "&7 is an invalid player", sender);
							return true;
						}
						int x = 0;
						try {
							x = Integer.parseInt(args[2]);
						} catch (Exception ex) {
							Message.sender(Main.prefix + "Sorry, but &c" + args[2] + "&7 is an invalid integer",
									sender);
							return true;
						}
						if (x > 0) {
							if (Tokens.bal(p) - x < 0.0D) {
								Message.sender(Main.prefix + "Sorry, but that would put &c" + args[1]
										+ "&7 into a negative balance", sender);
								return true;
							}
							Tokens.remove(p, x + 0.0D);
							Message.sender(Main.prefix + "&7You removed &b"
									+ Format.decimals(Integer.valueOf(0), Double.valueOf(x + 0.0D))
									+ "&7 tokens from &b" + args[1], sender);
							return true;
						}
						Message.sender(Main.prefix + "Sorry, but the amount must be greater than 0", sender);
						return true;
					}

					Message.sender(Main.prefix + "usage &b/ALToken Remove <Player> <Amount>", sender);
					return true;
				}

				Message.noPermission(sender);
				return true;
			}

			Message.sender(Main.prefix + "Commands for &bAfterLife-Tokens", sender);
			Message.sender("&8 ● &b/ALToken&7 View the help list", sender);
			Message.sender("&8 ● &b/ALToken Balance&7 View a user's token balance", sender);
			Message.sender("&8 ● &b/ALToken Baltop&7 View the top token balances", sender);
			Message.sender("&8 ● &b/ALToken Multi&7 View your token multiplier", sender);
			Message.sender("&8 ● &b/ALToken MultiTop&7 View the top token multipliers", sender);
			Message.sender("&8 ● &b/ALToken Give&7 Give a user tokens", sender);
			Message.sender("&8 ● &b/ALToken Remove&7 Remove tokens from a user's balance", sender);

			return false;
		}
	}
}
