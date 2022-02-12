package me.bluesky.plugin.ultimatelobby.commands;

import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Fly implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Error.ExecutedByConsole"));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("ultimatelobby.fly")) {
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseCommand"));
            return true;
        }

        if (!player.getAllowFlight()) {
            player.setAllowFlight(true);
            player.setFlying(true);
            player.setFlySpeed(0.1F);
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.Fly.Enabled"));
        } else {
            player.setAllowFlight(false);
            player.setFlying(false);
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.Fly.Disabled"));
        }
        return false;
    }
}