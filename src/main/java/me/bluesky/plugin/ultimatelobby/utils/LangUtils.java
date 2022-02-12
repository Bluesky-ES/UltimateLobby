package me.bluesky.plugin.ultimatelobby.utils;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.ChatColor;

public class LangUtils {
    static Main plugin = Main.getInstance();

    public static String getConfigLangMessage(String path) {
        if (plugin.getConfig().getString("Language").equalsIgnoreCase("zh_CN")) {
            return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + ConfigUtils.getChinese().getString(path));
        } else if (plugin.getConfig().getString("Language").equalsIgnoreCase("en_US")) {
            return ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("Prefix") + ConfigUtils.getEnglish().getString(path));
        } else {
            SendMessageUtils.SMTC(" ",
                    "§c[UltimateLobby] Warning! Unavailable language!",
                    "§c[UltimateLobby] Information:",
                    "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                    " ");
            return "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!";
        }
    }

    public static String getConfigNoPrefixLangMessage(String path) {
        if (plugin.getConfig().getString("Language").equalsIgnoreCase("zh_CN")) {
            return ChatColor.translateAlternateColorCodes('&', ConfigUtils.getChinese().getString(path));
        } else if (plugin.getConfig().getString("Language").equalsIgnoreCase("en_US")) {
            return ChatColor.translateAlternateColorCodes('&', ConfigUtils.getEnglish().getString(path));
        } else {
            SendMessageUtils.SMTC(" ",
                    "§c[UltimateLobby] Warning! Unavailable language!",
                    "§c[UltimateLobby] Information:",
                    "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                    " ");
            return "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!";
        }
    }

    public static String getConfigLangType() {
        if (plugin.getConfig().getString("Language").equalsIgnoreCase("zh_CN")) {
            return "Chinese";
        } else if (plugin.getConfig().getString("Language").equalsIgnoreCase("en_US")) {
            return "English";
        } else {
            SendMessageUtils.SMTC(" ",
                    "§c[UltimateLobby] Warning! Unavailable language!",
                    "§c[UltimateLobby] Information:",
                    "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                    " ");
            return "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!";
        }
    }

    public static void setLang(String type) {
        if (type.equalsIgnoreCase("chinese") || type.equalsIgnoreCase("zh_CN")) {
            plugin.getConfig().set("Language", "zh_CN");
            plugin.saveConfig();
        } else if (type.equalsIgnoreCase("english") || type.equalsIgnoreCase("en_US")) {
            plugin.getConfig().set("Language", "en_US");
            plugin.saveConfig();
        } else {
            SendMessageUtils.SMTC("§c错误的语言切换！");
        }
    }
}
