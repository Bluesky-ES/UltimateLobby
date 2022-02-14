package me.bluesky.plugin.ultimatelobby.commands;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.CmdEventTaskUtils;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.PluginDescriptionFile;

public class MainCommand implements CommandExecutor {
    private PluginDescriptionFile description;

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Main plugin = Main.getInstance();
        description = plugin.getDescription();

        if (args.length == 0) {
            if (LangUtils.getConfigLangType().equalsIgnoreCase("Chinese")) {
                SendMessageUtils.SMTPr(sender,
                        "§9§m---------------------------------------------",
                        "§6UltimateLobby 插件信息:",
                        "§e版本： §7v" + description.getVersion(),
                        "§e作者： §bBluesky_ES",
                        "§e正在运行的服务器版本： §7" + plugin.getServer().getVersion(),
                        "§c更多信息：/ultimatelobby help",
                        "§9§m---------------------------------------------");
                return true;
            }

            if (LangUtils.getConfigLangType().equalsIgnoreCase("English")) {
                SendMessageUtils.SMTPr(sender,
                        "§9§m---------------------------------------------",
                        "§6UltimateLobby Plugin Information:",
                        "§eVersion: §7v" + description.getVersion(),
                        "§eAuthor: §bBluesky_ES",
                        "§eRunning server version: §7" + plugin.getServer().getVersion(),
                        "§cFor more information：/ultimatelobby help",
                        "§9§m---------------------------------------------");
                return true;
            }

            SendMessageUtils.SMTC(" ",
                    "§c[UltimateLobby] Warning! Unavailable language!",
                    "§c[UltimateLobby] Information:",
                    "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                    " ");
            SendMessageUtils.SMTPr(sender,
                    "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!");
            return true;
        }

        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("info")) {
                if (LangUtils.getConfigLangType().equalsIgnoreCase("Chinese")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby 插件信息:",
                            "§e版本： §7v" + description.getVersion(),
                            "§e作者： §bBluesky_ES",
                            "§e正在运行的服务器版本： §7" + plugin.getServer().getVersion(),
                            "§c更多信息：/ultimatelobby help",
                            "§9§m---------------------------------------------");
                    return true;
                }

                if (LangUtils.getConfigLangType().equalsIgnoreCase("English")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby Plugin Information:",
                            "§eVersion: §7v" + description.getVersion(),
                            "§eAuthor: §bBluesky_ES",
                            "§eRunning server version: §7" + plugin.getServer().getVersion(),
                            "§cFor more information：/ultimatelobby help",
                            "§9§m---------------------------------------------");
                    return true;
                }

                SendMessageUtils.SMTC(" ",
                        "§c[UltimateLobby] Warning! Unavailable language!",
                        "§c[UltimateLobby] Information:",
                        "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                        " ");
                SendMessageUtils.SMTPr(sender,
                        "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!");
                return true;
            }

            if (args[0].equalsIgnoreCase("help")) {
                if (LangUtils.getConfigLangType().equalsIgnoreCase("Chinese")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby 插件帮助: (§e1§7/§b2§6)",
                            "§e/ultimatelobby §7§o- 主命令",
                            "§e    reload §7§o- 重载所有配置文件",
                            "§e    lang <语言类型> §7§o- 切换插件全局语言",
                            "§e    info §7§o- 查看插件信息",
                            "§e    help §7§o- 查看插件帮助",
                            "§e/fly §7§o- 开启/关闭飞行",
                            "§e/vanish §7§o- 开启/关闭隐身",
                            "§9§m---------------------------------------------");
                    return true;
                }

                if (LangUtils.getConfigLangType().equalsIgnoreCase("English")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby Plugin Help: (§e1§7/§b2§6)",
                            "§e/ultimatelobby §7§o- Main Command",
                            "§e    reload §7§o- Reload all configuration files",
                            "§e    lang <Language Type> §7§o- Switch plugin global language",
                            "§e    info §7§o- View plugin information",
                            "§e    help §7§o- View plugin help",
                            "§e/fly §7§o- Flight on/off",
                            "§e/vanish §7§o- Vanish on/off",
                            "§9§m---------------------------------------------");
                    return true;
                }

                SendMessageUtils.SMTC(" ",
                        "§c[UltimateLobby] Warning! Unavailable language!",
                        "§c[UltimateLobby] Information:",
                        "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                        " ");
                SendMessageUtils.SMTPr(sender,
                        "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!");
                return true;
            }

            if (args[0].equalsIgnoreCase("reload")) {
                if (!(sender instanceof Player)) {
                    SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Command.Reload"));
                    ConfigUtils.SaveReloadConfigs();
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission("ultimatelobby.reload")) {
                    SendMessageUtils.SMTPr(sender, LangUtils.getConfigLangMessage("Command.Reload"));
                    ConfigUtils.SaveReloadConfigs();
                    plugin.getServer().getScheduler().cancelTasks(plugin);
                    CmdEventTaskUtils.registerTasks(plugin);
                    return true;
                }

                SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseSubCommand"));
                return true;
            }

            if (args[0].equalsIgnoreCase("lang")) {
                if (!(sender instanceof Player)) {
                    SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Error.Usage").replace("<command>", cmd.getName()).replace("<subcommand...>", "lang <语言类型>"));
                    ConfigUtils.SaveReloadConfigs();
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission("ultimatelobby.lang")) {
                    SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.Usage").replace("<command>", cmd.getName()).replace("<subcommand...>", "lang <语言类型>"));
                    return true;
                }

                SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseSubCommand"));
                return true;
            }
        }

        if (args.length == 2) {
            if (args[0].equalsIgnoreCase("lang")) {
                if (!(sender instanceof Player)) {
                    if (args[1].equalsIgnoreCase("chinese") || args[1].equalsIgnoreCase("english") || args[1].equalsIgnoreCase("zh_CN") || args[1].equalsIgnoreCase("en_US")) {
                        SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Command.Lang.ChangeSuccessful").replace("%lang%", args[1]));
                        LangUtils.setLang(args[1]);
                        return true;
                    }
                    SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Command.Lang.ChangeFailed").replace("%lang%", args[1]));
                    return true;
                }
                Player player = (Player) sender;
                if (player.hasPermission("ultimatelobby.lang")) {
                    if (args[1].equalsIgnoreCase("chinese") || args[1].equalsIgnoreCase("english") || args[1].equalsIgnoreCase("zh_CN") || args[1].equalsIgnoreCase("en_US")) {
                        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.Lang.ChangeSuccessful").replace("%lang%", args[1]));
                        LangUtils.setLang(args[1]);
                        return true;
                    }
                    SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.Lang.ChangeFailed").replace("%lang%", args[1]));
                    return true;
                }

                SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseSubCommand"));
                return true;
            }

            if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("1")) {
                if (LangUtils.getConfigLangType().equalsIgnoreCase("Chinese")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby 插件帮助: (§e1§7/§b2§6)",
                            "§e/ultimatelobby §7§o- 主命令",
                            "§e    reload §7§o- 重载所有配置文件",
                            "§e    lang <语言类型> §7§o- 切换插件全局语言",
                            "§e    info §7§o- 查看插件信息",
                            "§e    help §7§o- 查看插件帮助",
                            "§e/fly §7§o- 开启/关闭飞行",
                            "§e/vanish §7§o- 开启/关闭隐身",
                            "§9§m---------------------------------------------");
                    return true;
                }

                if (LangUtils.getConfigLangType().equalsIgnoreCase("English")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby Plugin Help: (§e1§7/§b2§6)",
                            "§e/ultimatelobby §7§o- Main Command",
                            "§e    reload §7§o- Reload all configuration files",
                            "§e    lang <Language Type> §7§o- Switch plugin global language",
                            "§e    info §7§o- View plugin information",
                            "§e    help §7§o- View plugin help",
                            "§e/fly §7§o- Flight on/off",
                            "§e/vanish §7§o- Vanish on/off",
                            "§9§m---------------------------------------------");
                    return true;
                }

                SendMessageUtils.SMTC(" ",
                        "§c[UltimateLobby] Warning! Unavailable language!",
                        "§c[UltimateLobby] Information:",
                        "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                        " ");
                SendMessageUtils.SMTPr(sender,
                        "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!");
                return true;
            }

            if (args[0].equalsIgnoreCase("help") && args[1].equalsIgnoreCase("2")) {
                if (LangUtils.getConfigLangType().equalsIgnoreCase("Chinese")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby 插件帮助: (§e2§7/§b2§6)",
                            "§e/setlobby §7§o- 设置主城点",
                            "§e/spawn §7§o- 返回主城点",
                            "§e/clearchat §7§o- 清除所有玩家的聊天",
                            "§9§m---------------------------------------------");
                    return true;
                }

                if (LangUtils.getConfigLangType().equalsIgnoreCase("English")) {
                    SendMessageUtils.SMTPr(sender,
                            "§9§m---------------------------------------------",
                            "§6UltimateLobby Plugin Help: (§e2§7/§b2§6)",
                            "§e/setlobby §7§o- Set lobby point",
                            "§e/spawn §7§o- Back to lobby point",
                            "§e/clearchat §7§o- Clear chat for all players",
                            "§9§m---------------------------------------------");
                    return true;
                }

                SendMessageUtils.SMTC(" ",
                        "§c[UltimateLobby] Warning! Unavailable language!",
                        "§c[UltimateLobby] Information:",
                        "§c[UltimateLobby] Language " + plugin.getConfig().getString("Language") + " cannot be applied because this language is not supported. Available languages are zh_CN, en_US. Please go to config.yml to modify the Language value until available.",
                        " ");
                SendMessageUtils.SMTPr(sender,
                        "§c[UltimateLobby] The language configuration is wrong, please check whether the Language configuration in config is correct or go to the console to check the specific error!");
                return true;
            }
        }
        return false;
    }
}
