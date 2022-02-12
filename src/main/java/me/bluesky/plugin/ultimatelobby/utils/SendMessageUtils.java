package me.bluesky.plugin.ultimatelobby.utils;

import com.avaje.ebean.validation.NotNull;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SendMessageUtils {

    public static void SendMessageToConsole(String... message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }
    public static void SMTC(String... message) {
        Bukkit.getConsoleSender().sendMessage(message);
    }

    public static void SendMessageToPlayer(Player player, String... message) {
        player.sendMessage(message);
    }

    public static void SendMessageToPlayer(CommandSender sender, String... message) {
        sender.sendMessage(message);
    }
    public static void SMTPr(Player player, String... message) {
        player.sendMessage(message);
    }

    public static void SMTPr(CommandSender sender, String... message) {
       sender.sendMessage(message);
    }

    public static void SendMessageToAllPlayers(String... message) {
        if (Bukkit.getOnlinePlayers().size() != 0) {
            for (Player players : Bukkit.getOnlinePlayers())
                players.sendMessage(message);
        }
    }

    public static void SMTAPrs(String... message) {
        if (Bukkit.getOnlinePlayers().size() != 0) {
            for (Player players : Bukkit.getOnlinePlayers())
                players.sendMessage(message);
        }
    }
}
