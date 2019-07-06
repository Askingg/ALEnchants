package me.syn.alenchant.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import me.syn.alenchant.main.Main;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.ComponentBuilder;
import net.md_5.bungee.api.chat.HoverEvent;

public class Message {

	public static void noPermission(Player p) {
		p.sendMessage(Format.color(Main.prefix + "Sorry, but you don't have permission to do that"));
	}

	public static void noPermission(CommandSender sender) {
		sender.sendMessage(Format.color(Main.prefix + "Sorry, but you don't have permission to do that"));
	}

	public static void console(String msg) {
		Bukkit.getConsoleSender().sendMessage(Format.color(msg));
	}

	public static void broadcast(String msg) {
		Bukkit.broadcastMessage(Format.color(msg));
	}

	public static void player(String msg, Player p) {
		p.sendMessage(Format.color(msg));
	}

	public static void sender(String msg, CommandSender sender) {
		sender.sendMessage(Format.color(msg));
	}

	public static void hover(Player p, String chat, String click, String hover) {
		p.spigot().sendMessage(new ComponentBuilder(Format.color(chat))
				.event(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder(Format.color(hover)).create()))
				.event(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, click)).create());
	}
}
