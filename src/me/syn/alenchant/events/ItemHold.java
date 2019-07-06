package me.syn.alenchant.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import me.syn.alenchant.enchants.EnchFlight;
import me.syn.alenchant.enchants.EnchHaste;
import me.syn.alenchant.enchants.EnchSpeed;
import me.syn.alenchant.main.Enchants;

public class ItemHold implements Listener {

	@EventHandler
	public void hold(PlayerItemHeldEvent e) {
		Player p = e.getPlayer();
		ItemStack i = p.getInventory().getItem(e.getNewSlot());
		if (Enchants.level(i, "Flight") > 0) {
			EnchFlight.enable(p);
		} else {
			EnchFlight.disable(p);
		}
		if (Enchants.level(i, "Speed") > 0) {
			EnchSpeed.enable(p, Enchants.level(i, "Speed") - 1);
		} else {
			EnchSpeed.disable(p);
		}
		if (Enchants.level(i, "Haste") > 0) {
			EnchHaste.enable(p, Enchants.level(i, "Haste") - 1);
		} else {
			EnchHaste.disable(p);
		}
		return;
	}
}
