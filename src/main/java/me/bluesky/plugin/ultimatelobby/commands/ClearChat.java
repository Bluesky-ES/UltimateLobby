package me.bluesky.plugin.ultimatelobby.commands;

import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ClearChat implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Command.ClearChat"));
            SendMessageUtils.SMTAPrs(" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
                    " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ");
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("ultimatelobby.clearchat")) {
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseCommand"));
            return true;
        }

        SendMessageUtils.SMTAPrs(" "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
                " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ",
                " "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," "," ");
        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.ClearChat"));
        return false;
    }
}
