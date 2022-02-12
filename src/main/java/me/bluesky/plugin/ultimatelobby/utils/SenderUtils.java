package me.bluesky.plugin.ultimatelobby.utils;

import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

public class SenderUtils {
    public static ConsoleCommandSender ConsoleSender() {
        return Bukkit.getConsoleSender();
    }

    public static ConsoleCommandSender CS() {
        return Bukkit.getConsoleSender();
    }

    @Deprecated
    public static CommandSender PlayerSender(Player sender) {
        return sender;
    }

    @Deprecated
    public static CommandSender PS(Player sender) {
        return sender;
    }
}
