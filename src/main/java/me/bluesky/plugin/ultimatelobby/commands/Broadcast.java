package me.bluesky.plugin.ultimatelobby.commands;

import me.bluesky.plugin.ultimatelobby.utils.ColorUtils;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Broadcast implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            if (args.length == 0) {
                SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Error.Usage").replace("<command>", cmd.getName()).replace("<subcommand...>", "<message>"));
                return true;
            }

            StringBuilder message = new StringBuilder();
            for (String arg : args) {
                message.append(" ").append(arg);
            }

            StringBuilder broadcast = new StringBuilder();
            for (int i = 1; i < message.toString().toCharArray().length; i++) {
                broadcast.append(message.toString().toCharArray()[i]);
            }

            SendMessageUtils.SMTAPrs(ColorUtils.tMC(broadcast.toString()));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("ultimatelobby.broadcast")) {
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseCommand"));
            return true;
        }

        if (args.length == 0) {
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.Usage").replace("<command>", cmd.getName()).replace("<subcommand...>", "<message>"));
            return true;
        }

        StringBuilder message = new StringBuilder();
        for (String arg : args) {
            message.append(" ").append(arg);
        }

        StringBuilder broadcast = new StringBuilder();
        for (int i = 1; i < message.toString().toCharArray().length; i++) {
            broadcast.append(message.toString().toCharArray()[i]);
        }

        SendMessageUtils.SMTAPrs(ColorUtils.tMC(broadcast.toString()));
        return false;
    }
}
