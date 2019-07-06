package me.syn.alenchant.events;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import me.syn.alenchant.main.Main;

public class PlayerJoin implements Listener {

	@EventHandler
	public void onJoin(PlayerJoinEvent e) {
		Player p = e.getPlayer();
		if (!Main.tokens.containsKey(p.getUniqueId().toString())) {
			Main.tokens.put(p.getUniqueId().toString(), 0.0);
		}
	}
}
