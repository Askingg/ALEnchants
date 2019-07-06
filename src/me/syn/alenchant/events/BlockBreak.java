package me.syn.alenchant.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import me.syn.alenchant.main.ALEnchantAPI;

public class BlockBreak implements Listener {

	@EventHandler
	public void onBreak(BlockBreakEvent e) {
		ALEnchantAPI.blockBreak(e.getPlayer(), e.getBlock());
	}
}
