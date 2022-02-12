package me.bluesky.plugin.ultimatelobby.utils;

import org.bukkit.ChatColor;

import java.util.List;

public class ColorUtils {
    public static String toMinecraftColor(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String tMC(String message) {
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public static String toMinecraftColor(List<String> messages) {
        String msg = null;
        for (String message : messages)
            msg = message;
        return msg;
    }
}
