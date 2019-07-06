package me.syn.alenchant.commands;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.syn.alenchant.gui.EnchantGUI;
import me.syn.alenchant.main.Enchants;
import me.syn.alenchant.main.Main;
import me.syn.alenchant.utils.Message;
import me.syn.almisc.utils.Format;

public class ALCustomEnchantCMD implements CommandExecutor {

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String lab, String[] args) {
		if (args.length == 0) {
			Message.sender(Main.prefix + "Commands for &bAfterLife-CustomEnchant", sender);
			Message.sender("&8 ● &b/ALCustomEnchant&7 View the help list", sender);
			Message.sender("&8 ● &b/ALCustomEnchant Enchant&7 Open the enchanting menu", sender);
			Message.sender("&8 ● &b/ALCustomEnchant Calculate&7 Find the cost for enchantment levels, x to y", sender);
			return true;
		} else {
			if (args[0].equalsIgnoreCase("enchant")) {
				if (sender instanceof Player) {
					Player p = (Player) sender;
					if (p.getInventory().getItemInHand() != null) {
						if (p.hasPermission("alenchant.autopickaxe")
								&& p.getInventory().getItemInHand().getType().toString().contains("PICKAXE")) {

							p.openInventory(EnchantGUI.menu(p, p.getItemInHand()));
							EnchantGUI.open.add(p);
							p.setItemInHand(new ItemStack(Material.AIR));
							p.updateInventory();
						}
						return true;
					}
					p.openInventory(EnchantGUI.menu(p));
					EnchantGUI.open.add(p);
					return true;
				}
				return true;
			}
			if (args[0].equalsIgnoreCase("calculate") || args[0].equalsIgnoreCase("calc")
					|| args[0].equalsIgnoreCase("getprice") || args[0].equalsIgnoreCase("price")) {
				// CE Calc <Enchant> <FromLevl> <ToLevel>
				if (args.length >= 4) {
					String ce = args[1];
					if (Enchants.max(ce) == 0) {
						Message.sender(Main.prefix + "Sorry, buc &c" + ce + "&7 is an invalid enchantment", sender);
						return true;
					}
					int from = 0;
					try {
						from = Integer.parseInt(args[2]);
					} catch (Exception ex) {
						Message.sender(Main.prefix + "Sorry, but &c" + args[2] + "&7 is an invalid integer", sender);
					}
					if (from < 0) {
						Message.sender(Main.prefix + "Sorry, but the minimum level for&c" + ce + "&7 is &c0", sender);
						return true;
					}
					int to = 0;
					try {
						to = Integer.parseInt(args[3]);
					} catch (Exception ex) {
						Message.sender(Main.prefix + "Sorry, but &c" + args[3] + "&7 is an invalid integer", sender);
					}
					if (to > Enchants.max(ce)) {
						Message.sender(
								Main.prefix + "Sorry, but the maxmimum level for  &c" + ce + "&7 is &c" + Enchants.max(ce),
								sender);
						return true;
					}
					double cost = Enchants.cost(to, ce) - Enchants.cost(from, ce);
					Message.sender(Main.prefix + "To get &b" + ce + "&7 from level &b" + from + "&7 to level &b" + to
							+ "&7 will cost &b, " + Format.decimals(0, cost) + " Tokens", sender);
					return true;
				} else {
					Message.sender(Main.prefix + "Usage &b/CE Calc <Enchant> <FromLevl> <ToLevel>", sender);
					return true;
				}
			}
			Message.sender(Main.prefix + "Commands for &bAfterLife-CustomEnchant", sender);
			Message.sender("&8 ● &b/ALCustomEnchant&7 View the help list", sender);
			Message.sender("&8 ● &b/ALCustomEnchant Enchant&7 Open the enchanting menu", sender);
			return true;
		}
	}
}