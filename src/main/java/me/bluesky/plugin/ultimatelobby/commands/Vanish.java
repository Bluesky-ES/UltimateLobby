package me.bluesky.plugin.ultimatelobby.commands;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.ActionBarUtils;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;

public class Vanish implements CommandExecutor, Listener {
    Main plugin = Main.getInstance();
    Map<Player, String> vanishStats = new HashMap<>();
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Error.ExecutedByConsole"));
            return true;
        }

        Player player = (Player) sender;
        if (!player.hasPermission("ultimatelobby.vanish")) {
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseCommand"));
            return true;
        }

        if (!cmd.getName().equalsIgnoreCase(vanishStats.getOrDefault(player, null))) {
            vanishStats.put(player, cmd.getName());
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.Vanish.Message.Enabled"));
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                player1.hidePlayer(player);
            }
            new BukkitRunnable() {
                @Override
                public void run() {
                    if (cmd.getName().equalsIgnoreCase(vanishStats.getOrDefault(player, null))) {
                        ActionBarUtils.sendActionBar(player, LangUtils.getConfigNoPrefixLangMessage("Command.Vanish.ActionBar.Enabled"));
                    } else {
                        this.cancel();
                    }
                }
            }.runTaskTimerAsynchronously(plugin, 0, 20);
        } else {
            for (Player player1 : Bukkit.getOnlinePlayers()) {
                player1.showPlayer(player);
            }
            vanishStats.remove(player);
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.Vanish.Message.Disabled"));
            ActionBarUtils.sendActionBar(player, LangUtils.getConfigNoPrefixLangMessage("Command.Vanish.ActionBar.Disabled"));
        }

        return false;
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        vanishStats.remove(e.getPlayer());
    }
}
