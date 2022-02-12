package me.bluesky.plugin.ultimatelobby.utils;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigUtils {
    static Main plugin = Main.getInstance();

    public static File chineseFile;
    public static File englishFile;
    public static File spawnFile;
    public static File announcementFile;
    public static File scoreboardFile;
    public static File tablistFile;
    public static File itemsFile;
    public static File antiswearwordsFile;

    public static File langFile;
    public static File boardFile;

    public static FileConfiguration chineseConfig;
    public static FileConfiguration englishConfig;
    public static FileConfiguration spawnConfig;
    public static FileConfiguration announcementConfig;
    public static FileConfiguration scoreboardConfig;
    public static FileConfiguration tablistConfig;
    public static FileConfiguration itemsConfig;
    public static FileConfiguration antiswearwordsConfig;

    public static void registerConfigs() {
        //加载config.yml
        plugin.saveDefaultConfig();

        //定义langFile的路径
        langFile = new File(plugin.getDataFolder() + "\\lang");

        //定义boardFile的路径
        boardFile = new File(plugin.getDataFolder() + "\\board");

        //定义chineseFile的路径
        chineseFile = new File(langFile, "zh_CN.yml");

        //生成zh_CN.yml
        if (!chineseFile.exists())
            plugin.saveResource("lang\\zh_CN.yml", false);
        chineseConfig = YamlConfiguration.loadConfiguration(chineseFile);
        SendMessageUtils.SMTC("The zh_CN.yml has been created.");

        //定义englishFile的路径
        englishFile = new File(langFile, "en_US.yml");

        //生成en_US.yml
        if (!englishFile.exists())
            plugin.saveResource("lang\\en_US.yml", false);
        englishConfig = YamlConfiguration.loadConfiguration(englishFile);
        SendMessageUtils.SMTC("The en_US.yml has been created.");

        //定义announcementFile的路径
        announcementFile = new File(plugin.getDataFolder(), "announcement.yml");

        //生成announcement.yml
        if (!announcementFile.exists())
            plugin.saveResource("announcement.yml", false);
        announcementConfig = YamlConfiguration.loadConfiguration(announcementFile);
        SendMessageUtils.SMTC("The announcement.yml has been created.");

        //定义scoreboardFile的路径
        scoreboardFile = new File(boardFile, "scoreboard.yml");

        //生成scoreboard.yml
        if (!scoreboardFile.exists())
            plugin.saveResource("board\\scoreboard.yml", false);
        scoreboardConfig = YamlConfiguration.loadConfiguration(scoreboardFile);
        SendMessageUtils.SMTC("The scoreboard.yml has been created.");

        //定义tablistFile的路径
        tablistFile = new File(boardFile, "tablist.yml");

        //生成tablist.yml
        if (!tablistFile.exists())
            plugin.saveResource("board\\tablist.yml", false);
        tablistConfig = YamlConfiguration.loadConfiguration(tablistFile);
        SendMessageUtils.SMTC("The tablist.yml has been created.");

        //定义spawnFile的路径
        spawnFile = new File(plugin.getDataFolder(), "spawn.yml");

        //生成spawn.yml
        if (!spawnFile.exists())
            plugin.saveResource("spawn.yml", false);
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
        SendMessageUtils.SMTC("The spawn.yml has been created.");

        //定义itemsFile的路径
        itemsFile = new File(plugin.getDataFolder(), "items.yml");

        //生成items.yml
        if (!itemsFile.exists())
            plugin.saveResource("items.yml", false);
        itemsConfig = YamlConfiguration.loadConfiguration(itemsFile);
        SendMessageUtils.SMTC("The items.yml has been created.");

        //定义antiswearwordsFile的路径
        antiswearwordsFile = new File(plugin.getDataFolder(), "anti-swearwords.yml");

        //生成antiswearwords.yml
        if (!antiswearwordsFile.exists())
            plugin.saveResource("anti-swearwords.yml", false);
        antiswearwordsConfig = YamlConfiguration.loadConfiguration(antiswearwordsFile);
        SendMessageUtils.SMTC("The anti-swearwords.yml has been created.");
    }

    public static FileConfiguration getChinese() {
        return chineseConfig;
    }

    public static FileConfiguration getEnglish() {
        return englishConfig;
    }

    public static FileConfiguration getAnnouncement() {
        return announcementConfig;
    }

    public static FileConfiguration getScoreboard() {
        return scoreboardConfig;
    }

    public static FileConfiguration getTablist() {
        return tablistConfig;
    }

    public static FileConfiguration getSpawn() {
        return spawnConfig;
    }

    public static FileConfiguration getItems() {
        return itemsConfig;
    }

    public static FileConfiguration getAntiSwearWords() {
        return antiswearwordsConfig;
    }

    public static void SaveReloadConfigs() {
        plugin.reloadConfig();
        chineseConfig = YamlConfiguration.loadConfiguration(chineseFile);
        englishConfig = YamlConfiguration.loadConfiguration(englishFile);
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
        announcementConfig = YamlConfiguration.loadConfiguration(announcementFile);
        scoreboardConfig = YamlConfiguration.loadConfiguration(scoreboardFile);
        tablistConfig = YamlConfiguration.loadConfiguration(tablistFile);
        itemsConfig = YamlConfiguration.loadConfiguration(itemsFile);
    }

    public static void saveSpawn() {
        try {
            spawnConfig.save(spawnFile);
        } catch (IOException var1) {
            SendMessageUtils.SMTC("§c[UltimateLobby] Could not save the spawn.yml!");
        }
        spawnConfig = YamlConfiguration.loadConfiguration(spawnFile);
    }
}
