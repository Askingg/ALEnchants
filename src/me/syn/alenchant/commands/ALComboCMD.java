package me.syn.alenchant.commands;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

import me.syn.alcore.Core;
import me.syn.alenchant.enchants.EnchCombo;
import me.syn.alenchant.main.Main;

public class ALComboCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
		if (args.length == 0) {
			Core.message(Core.prefix("b") + "Your combo is currently &b" + EnchCombo.getCombo((Player) sender), sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("help")) {
				Core.message(Main.prefix + "Commands for &bAfterLife-CustomEnchants&8 (&bCombo&8)", sender);
				Core.message("&8 ● &b/ALCombo&7 View your current combo", sender);
				Core.message("&8 ● &b/ALCombo Help&7 View the help list", sender);
				Core.message("&8 ● &b/ALCombo Set&7 Set a user's combo", sender);
				Core.message("&8 ● &b/ALCombo Add&7 Increase a user's combo", sender);
				Core.message("&8 ● &b/ALCombo Top&7 View the help list", sender);
				return true;
			}
			if (args[0].equalsIgnoreCase("set")) { // Combo Set <Player> <Combo>
				if (sender instanceof ConsoleCommandSender || sender.hasPermission("alcombo.command.set")) {
					if (args.length >= 3) {
						Player p = Bukkit.getPlayer(args[1]);
						if (p == null) {
							Core.message(Core.prefix("b") + "Sorry, but &c" + args[1] + "&7 is an invalid player",
									sender);
							return true;
						}
						int x = 0;
						try {
							x = Integer.parseInt(args[2]);
						}catch (Exception ex) {
							Core.message(Core.prefix("b") + "Sorry, but &c"+args[2]+"&7 is an invalid integer", sender);
							return true;
						}
						if (x < 1) {
							Core.message(Core.prefix("b") + "Sorry, but the amount must be &c> 0", sender);
							return true;
						}
						EnchCombo.combo.put(p, x);
						Core.message(Core.prefix("b") + "You set &b" + args[1] + "&7's Combo to &b" + Core.decimals(0, x),sender);
						Core.message(Core.prefix("b") + "&b" + sender.getName() + "&7 set your combo to &b" + Core.decimals(0, x), p);
						return true;
					} else {
						Core.message(Core.prefix("b") + "Usage&b /ALCombo Set <Player> <Combo>", sender);
						return true;
					}
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("top") ) {
				int x = 1;
				if (args.length >= 2) {
					try {
						x = Integer.parseInt(args[1]);
					} catch (Exception ex) {
						Core.message(Core.prefix("a") + "&7Sorry, but &C" + args[1] + "&7 is an invalid integer",
								sender);
						return true;
					}
					if (x < 1)
						x = 1;
				}
				HashMap<String, Integer> m = new HashMap<String, Integer>();
				for (Player p : EnchCombo.combo.keySet()) {
					m.put(p.getName(), EnchCombo.getCombo(p));
				}
				Core.leaderboardInt("Combos", "b", m, x, 15, true, sender);
				return true;
			}
		}
		return false;
	}
}