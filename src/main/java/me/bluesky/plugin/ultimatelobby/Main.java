package me.bluesky.plugin.ultimatelobby;

import me.bluesky.plugin.ultimatelobby.metrics.Metrics;
import me.bluesky.plugin.ultimatelobby.update.UpdateChecker;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.java.JavaPlugin;
import me.bluesky.plugin.ultimatelobby.utils.*;

public class Main extends JavaPlugin {
    public static Main instance;
    public static PluginDescriptionFile descriptionFile;

    @Override
    public void onEnable() {
        instance = this;
        descriptionFile = getDescription();
        ConfigUtils.registerConfigs();
        CmdEventTaskUtils.registerCommands();
        CmdEventTaskUtils.registerListeners(this);
        CmdEventTaskUtils.registerTasks(this);

        int pluginId = 14266;
        Metrics metrics = new Metrics(this, pluginId);
        metrics.addCustomChart(new Metrics.SimplePie("chart_id", () -> "My value"));

        new UpdateChecker(99926).getLatestVersion(version -> {
            if (descriptionFile.getVersion().equalsIgnoreCase(version)) {
                SendMessageUtils.SMTC(
                        " ",
                        "§b[UltimateLobby] 当前插件是最新版本！",
                        "§b[UltimateLobby] The current plugin is the latest version!",
                        " "
                );
            } else {
                SendMessageUtils.SMTC(
                        " ",
                        "§b[UltimateLobby] 插件最新版本为v" + version +  "，你正在使用v" + descriptionFile.getVersion() + "！请前往https://www.spigotmc.org/resources/ultimatelobby-professional-lobby-integrated-management.99926/下载最新版。",
                        "§b[UltimateLobby] The latest version of the plugin is v" + version + ", you are using v" + descriptionFile.getVersion() +"! Please go to https://www.spigotmc.org/resources/ultimatelobby-professional-lobby-integrated-management.99926/ to download the latest version.",
                        " "
                );
            }
        });

        SendMessageUtils.SMTC(
                " ",
                "§b[UltimateLobby] 插件已成功被服务器加载！插件版本：v" + descriptionFile.getVersion() +  " 作者： Bluesky_ES",
                "§b[UltimateLobby] The plugin has been loaded on server! Plugin version: 1.0 Author: Bluesky_ES",
                "§6[UltimateLobby] 本插件正在运行在版本为" + getServer().getVersion() + "的服务器上。",
                "§6[UltimateLobby] This plugin is running on a server with version " + getServer().getVersion() + ".",
                "§6[UltimateLobby] Enjoy it!",
                " "
        );

        if (!LangUtils.getConfigLangType().equalsIgnoreCase("Chinese") && !LangUtils.getConfigLangType().equalsIgnoreCase("English")) {
            SendMessageUtils.SMTC(" ",
                    "§c[UltimateLobby] Warning! Unavailable language!",
                    "§c[UltimateLobby] Information:",
                    "§c[UltimateLobby] Language " + getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                    " ");
        }

    }

    @Override
    public void onDisable() {
        instance = null;

        SendMessageUtils.SMTC(
                " ",
                "§c[UltimateLobby] 插件已成功被服务器卸载！插件版本：1.0 作者： Bluesky_ES",
                "§c[UltimateLobby] The plugin has been unloaded on server! Plugin version: 1.0 Author: Bluesky_ES",
                " "
        );
    }

    public static Main getInstance() {
        return instance;
    }
}
