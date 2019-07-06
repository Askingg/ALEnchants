package me.syn.alenchant.gui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.FireworkEffectMeta;
import org.bukkit.inventory.meta.ItemMeta;

import me.syn.alenchant.main.Enchants;
import me.syn.alenchant.main.Main;
import me.syn.alenchant.main.Tokens;
import me.syn.alenchant.utils.Format;
import me.syn.alenchant.utils.Message;
import net.md_5.bungee.api.ChatColor;

public class EnchantGUI implements Listener {

	public static List<Player> open = new ArrayList<Player>();
	public static HashMap<String, List<String>> desc = new HashMap<String, List<String>>(); // enchant, Description

	public static void setUpDescriptionsp() {
		desc.put("Efficiency", Arrays.asList("&bBreak blocks faster"));
		desc.put("Fortune", Arrays.asList("&bGet more blocks from mining"));
		desc.put("Scavenger", Arrays.asList("&bFind tokens, credits and keys more often"));
		desc.put("AutoSell", Arrays.asList("&bAutomatically sell mined blocks"));
		desc.put("AutoSmelt", Arrays.asList("&bAutomatically smelt mined ores"));
		desc.put("Greed", Arrays.asList("&bMake more money from mining"));
		desc.put("TokenDesire", Arrays.asList("&bFind more tokens while mining"));
		desc.put("CreditDesire", Arrays.asList("&bFind more gang credits while mining"));
		desc.put("KeyDesire", Arrays.asList("&bFine more keys while mining"));
		desc.put("Flight", Arrays.asList("&bBe able to fly"));
		desc.put("Speed", Arrays.asList("&bPermanent speed effect"));
		desc.put("Haste", Arrays.asList("&bPermanent haste effect"));
		desc.put("Combo", Arrays.asList("&bThe more you mine the more you're rewarded"));
	}

	public static Inventory menu(Player p) {
		Inventory inv = Bukkit.createInventory(null, 45, Format.color("&b&lEnchanting"));
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		inv.setItem(0, i);
		inv.setItem(1, i);
		inv.setItem(2, i);
		inv.setItem(3, i);
		inv.setItem(4, i);
		inv.setItem(5, i);
		inv.setItem(6, i);
		inv.setItem(7, i);
		inv.setItem(8, i);
		inv.setItem(9, i);
		inv.setItem(11, i);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(20, i);
		inv.setItem(26, i);
		inv.setItem(27, i);
		inv.setItem(29, i);
		inv.setItem(35, i);
		inv.setItem(36, i);
		inv.setItem(37, i);
		inv.setItem(38, i);
		inv.setItem(39, i);
		inv.setItem(40, i);
		inv.setItem(41, i);
		inv.setItem(42, i);
		inv.setItem(43, i);
		inv.setItem(44, i);
		i.setDurability((byte) 0);
		m.setDisplayName(Format.color("&bPlace item between the white glass"));
		i.setItemMeta(m);
		inv.setItem(10, i);
		inv.setItem(28, i);
		i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 8);
		m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		while (inv.firstEmpty() != -1) {
			inv.setItem(inv.firstEmpty(), i);
		}
		return inv;
	}

	public static Inventory menu(Player p, ItemStack pick) {
		Inventory inv = Bukkit.createInventory(null, 45, Format.color("&b&lEnchanting"));
		ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 7);
		ItemMeta m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		inv.setItem(0, i);
		inv.setItem(1, i);
		inv.setItem(2, i);
		inv.setItem(3, i);
		inv.setItem(4, i);
		inv.setItem(5, i);
		inv.setItem(6, i);
		inv.setItem(7, i);
		inv.setItem(8, i);
		inv.setItem(9, i);
		inv.setItem(11, i);
		inv.setItem(17, i);
		inv.setItem(18, i);
		inv.setItem(20, i);
		inv.setItem(26, i);
		inv.setItem(27, i);
		inv.setItem(29, i);
		inv.setItem(35, i);
		inv.setItem(36, i);
		inv.setItem(37, i);
		inv.setItem(38, i);
		inv.setItem(39, i);
		inv.setItem(40, i);
		inv.setItem(41, i);
		inv.setItem(42, i);
		inv.setItem(43, i);
		inv.setItem(44, i);
		i.setDurability((byte) 0);
		m.setDisplayName(Format.color("&bPlace item between the white glass"));
		i.setItemMeta(m);
		inv.setItem(10, i);
		inv.setItem(28, i);
		inv.setItem(19, pick);
		enchantBooks("PICKAXE", pick, inv);
		i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 8);
		m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		while (inv.firstEmpty() != -1) {
			inv.setItem(inv.firstEmpty(), i);
		}
		return inv;
	}

	@EventHandler
	public void onClick(InventoryClickEvent e) {
		if (open.contains((Player) e.getWhoClicked())) {
			Player p = (Player) e.getWhoClicked();
			ItemStack ci = e.getCurrentItem();
			if (ci.getType().toString().contains("GLASS_PANE")) {
				e.setCancelled(true);
			}
			if (e.getRawSlot() < 44) {
				e.setCancelled(true);
				if (ci.getType().equals(Material.FIREWORK_CHARGE)) {
					ItemMeta cim = ci.getItemMeta();
					ItemStack i = e.getInventory().getItem(19);
					if (cim.getDisplayName().equals(Format.color("&b&lEfficiency"))
							|| cim.getDisplayName().equals(Format.color("&b&lFortune"))) {
						if (e.getClick() == ClickType.LEFT) {
							if (cim.getDisplayName().equals(Format.color("&b&lEfficiency"))) {
								if (Enchants.level(i, "Efficiency") < Enchants.max("Efficiency")) {
									if (Tokens.bal(p) >= Enchants.cost(i, "Efficiency")) {
										applyEnchant(p, i, e.getRawSlot(), Enchantment.DIG_SPEED, e.getInventory(),
												Arrays.asList("&bBreak blocks faster"), 1,
												Enchants.cost(i, "Efficiency"));
										return;
									} else {
										Message.player(Main.prefix + "Sorry, but you don't have enough tokens: &c"
												+ Format.decimals(0, Tokens.bal(p)) + "&8/&b"
												+ Format.decimals(0, Enchants.cost(i, "Efficiency") + 0.0), p);
										return;
									}
								} else {
									Message.player(
											Main.prefix + "Sorry, but &bEfficiency&7 is already maxed on this pickaxe",
											p);
									return;
								}
							}
							if (cim.getDisplayName().equals(Format.color("&b&lFortune"))) {
								if (Enchants.level(i, "Fortune") < Enchants.max("Fortune")) {
									if (Tokens.bal(p) >= Enchants.cost(i, "Fortune")) {
										applyEnchant(p, i, e.getRawSlot(), Enchantment.LOOT_BONUS_BLOCKS,
												e.getInventory(), Arrays.asList("&bGet more blocks from mining"), 1,
												Enchants.cost(i, "Fortune"));
										return;
									} else {
										Message.player(Main.prefix + "Sorry, but you don't have enough tokens: &c"
												+ Format.decimals(0, Tokens.bal(p)) + "&8/&b"
												+ Format.decimals(0, Enchants.cost(i, "Fortune") + 0.0), p);
										return;
									}
								} else {
									Message.player(
											Main.prefix + "Sorry, but &bFortune&7 is already maxed on this pickaxe", p);
									return;
								}
							}
						}
						if (e.getClick() == ClickType.RIGHT) {
							if (cim.getDisplayName().equals(Format.color("&b&lEfficiency"))) {
								if (Enchants.level(i, "Efficiency") < Enchants.max("Efficiency")) {
									int lvl = i.getEnchantmentLevel(Enchantment.DIG_SPEED);
									double cost = 0;
									if (lvl + 10 > Enchants.max("Efficiency")) {
										Message.player(
												Main.prefix + "Sorry, but that would exceed the limit for &cEfficiency",
												p);
										return;
									}
									for (int x = 0; x < 10; x++) {
										cost += Enchants.cost(lvl + x, "Efficiency");
									}
									if (Tokens.bal(p) >= cost) {
										applyEnchant(p, i, e.getRawSlot(), Enchantment.DIG_SPEED, e.getInventory(),
												Arrays.asList("&bBreak blocks faster"), 10, cost);
										return;
									} else {
										Message.player(Main.prefix + "Sorry, but you don't have enough tokens: &c"
												+ Format.decimals(0, Tokens.bal(p)) + "&8/&b"
												+ Format.decimals(0, cost + 0.0), p);
										return;
									}
								} else {
									Message.player(
											Main.prefix + "Sorry, but &bEfficiency&7 is already maxed on this pickaxe",
											p);
									return;
								}
							}
							if (cim.getDisplayName().equals(Format.color("&b&lFortune"))) {
								if (Enchants.level(i, "Fortune") < Enchants.max("Fortune")) {
									int lvl = i.getEnchantmentLevel(Enchantment.LOOT_BONUS_BLOCKS);
									double cost = 0;
									if (lvl + 10 > Enchants.max("Fortune")) {
										Message.player(
												Main.prefix + "Sorry, but that would exceed the limit for &cFortune",
												p);
										return;
									}
									for (int x = 0; x < 10; x++) {
										cost += Enchants.cost(lvl + x, "Fortune");
									}
									if (Tokens.bal(p) >= cost) {
										applyEnchant(p, i, e.getRawSlot(), Enchantment.LOOT_BONUS_BLOCKS,
												e.getInventory(), Arrays.asList("&bGet more blocks from mining"), 10,
												cost);
										return;
									} else {
										Message.player(Main.prefix + "Sorry, but you don't have enough tokens: &c"
												+ Format.decimals(0, Tokens.bal(p)) + "&8/&b"
												+ Format.decimals(0, cost + 0.0), p);
										return;
									}
								} else {
									Message.player(
											Main.prefix + "Sorry, but &bEfficiency&7 is already maxed on this pickaxe",
											p);
									return;
								}
							}

						}
					} else {
						String s = ChatColor.stripColor(Format.color(cim.getDisplayName()));
						if (Enchants.level(i, s) < Enchants.max(s)) {
							if (e.getClick() == ClickType.LEFT) {
								if (Tokens.bal(p) >= Enchants.cost(i, s)) {
									applyCustomEnchant(p, i, e.getRawSlot(), s, e.getInventory(), desc.get(s), 1,
											Enchants.cost(i, s));
									return;
								} else {
									Message.player(Main.prefix + "Sorry, but you don't have enough tokens: &c"
											+ Format.decimals(0, Tokens.bal(p)) + "&8/&b"
											+ Format.decimals(0, Enchants.cost(i, s) + 0.0), p);
									return;
								}
							}
							if (e.getClick() == ClickType.RIGHT) {
								double cost = 0.0;
								int lvl = Enchants.level(i, s);
								if ((lvl + 10) > Enchants.max(s)) {
									Message.player(
											Main.prefix + "Sorry, but that would exceed the max level for &c" + s, p);
									return;
								}
								for (int x = 0; x < 10; x++) {
									cost += Enchants.cost(lvl + x, s);
								}
								if (Tokens.bal(p) >= cost) {
									applyCustomEnchant(p, i, e.getRawSlot(), s, e.getInventory(), desc.get(s), 10,
											cost);
									return;
								} else {
									Message.player(Main.prefix + "Sorry, but you don't have enough tokens: &c"
											+ Format.decimals(0, Tokens.bal(p)) + "&8/&b"
											+ Format.decimals(0, cost + 0.0), p);
									return;
								}
							}
						} else {
							Message.player(Main.prefix + "Sorry, but &b" + s + "&7 is already maxed on this pickaxe",
									p);
							return;
						}

					}
				}
			}
			if (e.getRawSlot() == 19) {
				Inventory inv = e.getInventory();
				if (e.getCursor() != null) {
					if (ci.getType().equals(Material.STAINED_GLASS_PANE)) {
						String t = e.getCursor().getType().toString();
						if (!(t.contains("PICKAXE"))) {
							return;
						}
						enchantBooks(t, e.getCursor(), inv);
						inv.setItem(19, e.getCursor());
						p.setItemOnCursor(new ItemStack(Material.AIR, 0));
						p.updateInventory();
						return;
					}
				}
				if (!ci.getType().equals(Material.STAINED_GLASS_PANE)) {
					if (p.getInventory().firstEmpty() != -1) {
						p.getInventory().addItem(inv.getItem(19));
						ItemStack i = new ItemStack(Material.STAINED_GLASS_PANE);
						i.setDurability((byte) 8);
						ItemMeta m = i.getItemMeta();
						m.setDisplayName(Format.color("&7"));
						i.setItemMeta(m);
						inv.setItem(19, i);
						p.updateInventory();
						p.closeInventory();
						return;
					} else {
						Message.player(Main.prefix + "Sorry, but your inventory is full", p);
						return;
					}
				}
			}
		}
	}

	private static void applyCustomEnchant(Player p, ItemStack i, int slot, String ench, Inventory inv,
			List<String> desc, int levels, double cost) {
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		if (m.hasLore()) {
			Boolean b = false;
			l = m.getLore();
			int x = -1;
			for (String str : l) {
				x++;
				if (str.contains(ench)) {
					l.set(x, Format.color("&b" + ench + " " + (Enchants.level(i, str) + levels)));
					b = true;
					break;
				}
			}
			if (!b)
				l.add(Format.color("&b" + ench + " " + levels));
		} else {
			l.add(Format.color("&b" + ench + " " + levels));
		}
		Tokens.removeNoPM(p, cost);
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(slot, bookCustom(i, ench, desc));
		Message.player(Main.prefix + "&b+ " + levels + " " + ench + " &8&l┃&c - " + Format.number(cost) + " tokens", p);
	}

	private static void applyEnchant(Player p, ItemStack i, int slot, Enchantment ench, Inventory inv,
			List<String> desc, int levels, double cost) {
		ItemMeta m = i.getItemMeta();
		List<String> l = new ArrayList<String>();
		if (!m.hasItemFlag(ItemFlag.HIDE_ENCHANTS)) {
			m.addItemFlags(ItemFlag.HIDE_ENCHANTS);
		}
		i.setItemMeta(m);
		String enchs = "";
		if (ench.equals(Enchantment.DIG_SPEED)) {
			enchs = "Efficiency";
		}
		if (ench.equals(Enchantment.LOOT_BONUS_BLOCKS)) {
			enchs = "Fortune";
		}
		if (m.hasEnchant(ench)) {
			m.addEnchant(ench, m.getEnchantLevel(ench) + levels, true);
		} else {
			m.addEnchant(ench, levels, true);
		}
		if (m.hasLore()) {
			Boolean b = false;
			l = m.getLore();
			int x = -1;
			for (String str : l) {
				x++;
				if (str.contains(enchs)) {
					l.set(x, Format.color("&b" + enchs + " " + m.getEnchantLevel(ench)));
					b = true;
					break;
				}
			}
			if (!b)
				l.add(Format.color("&b" + enchs + " " + m.getEnchantLevel(ench)));
		} else {
			l.add(Format.color("&b" + enchs + " " + m.getEnchantLevel(ench)));
		}
		Tokens.removeNoPM(p, cost);
		m.setLore(l);
		i.setItemMeta(m);
		inv.setItem(slot, bookVanilla(i, enchs, ench, desc));
		Message.player(Main.prefix + "&b+ " + levels + " " + enchs + " &8&l┃&c - " + Format.number(cost) + " tokens",
				p);
	}

	public static ItemStack bookCustom(ItemStack item, String ench, List<String> desc) {
		int lvl = Enchants.level(item, ench);
		int max = Enchants.max(ench);
		double cost = Enchants.cost(item, ench);
		ItemStack i = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta m = (FireworkEffectMeta) i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&b&l" + ench));
		// FUCHSIA,
		m.setEffect(FireworkEffect.builder().withColor(Color.AQUA).build());
		l.add(Format.color("&7"));
		for (String str : desc) {
			l.add(Format.color("&7" + str));
		}
		l.add(Format.color("&7"));
		if (lvl >= max) {
			l.add(Format.color("&bThis enchantment is maxed"));
		} else {
			l.add(Format.color("&7Level &b" + Format.decimals(0, lvl + 0.0) + "&8/&b" + Format.decimals(0, max + 0.0)));
			l.add(Format.color("&7"));
			l.add(Format.color("&7&lPurchase:"));
			l.add(Format.color("&7 1 Level &b" + Format.decimals(0, cost)));
			if ((lvl + 10) <= max) {
				double x10 = 0.0;
				for (int x = 0; x < 10; x++) {
					x10 += Enchants.cost(lvl + x, ench);
				}
				l.add(Format.color("&7 10 Levels &b" + Format.decimals(0, x10)));
			}
		}
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static ItemStack bookVanilla(ItemStack item, String enchant, Enchantment ench, List<String> desc) {
		int lvl = item.getEnchantmentLevel(ench);
		int max = Enchants.max(enchant);
		double cost = Enchants.cost(item, enchant);
		ItemStack i = new ItemStack(Material.FIREWORK_CHARGE);
		FireworkEffectMeta m = (FireworkEffectMeta) i.getItemMeta();
		List<String> l = new ArrayList<String>();
		m.setDisplayName(Format.color("&b&l" + enchant));
		m.setEffect(FireworkEffect.builder().withColor(Color.AQUA).build());
		l.add(Format.color("&7"));
		for (String str : desc) {
			l.add(Format.color("&7" + str));
		}
		l.add(Format.color("&7"));
		if (lvl >= max) {
			l.add(Format.color("&bThis enchantment is maxed"));
		} else {
			if (item.getItemMeta().hasEnchant(ench)) {
				l.add(Format.color("&7Level &b" + Format.decimals(0, lvl + 0.0) + "&8/&b" + Format.decimals(0, max + 0.0)));
			} else {
				l.add(Format.color("&7Level &b0") + "&8/&b" + Format.decimals(0, max + 0.0));
			}
			l.add(Format.color("&7"));
			l.add(Format.color("&7&lPurchase:"));
			l.add(Format.color("&7 1 Level &b" + Format.decimals(0, cost)));
			if ((lvl + 10) <= max) {
				double x10 = 0.0;
				for (int x = 0; x < 10; x++) {
					x10 += Enchants.cost(lvl + x, enchant);
				}
				l.add(Format.color("&7 10 Levels &b" + Format.decimals(0, x10)));
			}
		}
		m.setLore(l);
		i.setItemMeta(m);
		return i;
	}

	public static void enchantBooks(String type, ItemStack item, Inventory inv) {
		ItemStack i = new ItemStack(Material.AIR);
		ItemMeta m = i.getItemMeta();
		inv.setItem(12, i);
		inv.setItem(13, i);
		inv.setItem(14, i);
		inv.setItem(15, i);
		inv.setItem(16, i);
		inv.setItem(21, i);
		inv.setItem(22, i);
		inv.setItem(23, i);
		inv.setItem(24, i);
		inv.setItem(25, i);
		inv.setItem(30, i);
		inv.setItem(31, i);
		inv.setItem(32, i);
		inv.setItem(33, i);
		inv.setItem(34, i);
		if (type.contains("PICKAXE")) {
			inv.addItem(bookVanilla(item, "Efficiency", Enchantment.DIG_SPEED, Arrays.asList("&bBreak blocks faster")));
			inv.addItem(bookVanilla(item, "Fortune", Enchantment.LOOT_BONUS_BLOCKS,
					Arrays.asList("&bGet more blocks from mining")));
			inv.addItem(bookCustom(item, "Scavenger", Arrays.asList("&bFind tokens, credits and keys more often")));
			inv.addItem(bookCustom(item, "AutoSell", Arrays.asList("&bAutomatically sell mined blocks")));
			inv.addItem(bookCustom(item, "AutoSmelt", Arrays.asList("&bAutomatically smelt mined ores")));
			inv.addItem(bookCustom(item, "Greed", Arrays.asList("&bMake more money from mining")));
			inv.addItem(bookCustom(item, "TokenDesire", Arrays.asList("&bFind more tokens while mining")));
			inv.addItem(bookCustom(item, "CreditDesire", Arrays.asList("&bFind more gang credits while mining")));
			inv.addItem(bookCustom(item, "KeyDesire", Arrays.asList("&bFine more keys while mining")));
			inv.addItem(bookCustom(item, "Flight", Arrays.asList("&bBe able to fly")));
			inv.addItem(bookCustom(item, "Speed", Arrays.asList("&bPermanent speed effect")));
			inv.addItem(bookCustom(item, "Haste", Arrays.asList("&bPermanent haste effect")));
			inv.addItem(bookCustom(item, "Combo", Arrays.asList("&bThe more you mine the more you're rewarded")));
		}
		i = new ItemStack(Material.STAINED_GLASS_PANE);
		i.setDurability((byte) 8);
		m = i.getItemMeta();
		m.setDisplayName(Format.color("&7"));
		i.setItemMeta(m);
		while (inv.firstEmpty() != -1) {
			inv.setItem(inv.firstEmpty(), i);
		}
	}

	@EventHandler
	public void onClose(InventoryCloseEvent e) {
		Player p = (Player) e.getPlayer();
		if (open.contains(p)) {
			open.remove(p);
			if (e.getInventory().getItem(19) != null
					&& e.getInventory().getItem(19).getType() != Material.STAINED_GLASS_PANE) {
				p.getInventory().addItem(e.getInventory().getItem(19));
				p.updateInventory();
				return;
			}
		}
	}
}
