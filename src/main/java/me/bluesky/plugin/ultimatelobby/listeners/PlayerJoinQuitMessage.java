package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import me.bluesky.plugin.ultimatelobby.utils.TitleUtils;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinQuitMessage implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (player.hasPermission("ultimatelobby.join_quit_message")) {
            if (plugin.getConfig().getBoolean("Functions.Player.Join.Title")) {
                TitleUtils.STTPr(player, LangUtils.getConfigNoPrefixLangMessage("Listener.Player.Join.Title").replace("%player%", player.getDisplayName()),
                        LangUtils.getConfigNoPrefixLangMessage("Listener.Player.Join.SubTitle").replace("%player%", player.getDisplayName()),
                        plugin.getConfig().getInt("Functions.Player.Join.Title_Times.FadeIn"),
                        plugin.getConfig().getInt("Functions.Player.Join.Title_Times.Stay"),
                        plugin.getConfig().getInt("Functions.Player.Join.Title_Times.FadeOut"));
            }
            if (plugin.getConfig().getBoolean("Functions.Player.Join.Message")) {
                e.setJoinMessage(null);
                SendMessageUtils.SMTAPrs(LangUtils.getConfigNoPrefixLangMessage("Listener.Player.Join.Message").replace("%player%", player.getDisplayName()));
            }
            if (plugin.getConfig().getBoolean("Functions.Player.Join.Sound.Switch")) {
                Sound sound = Sound.valueOf(plugin.getConfig().getString("Functions.Player.Join.Sound.Type"));
                player.playSound(player.getLocation(), sound, 100, 1);
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (plugin.getConfig().getBoolean("Functions.Player.Quit.Message")) {
            if (player.hasPermission("ultimatelobby.join_quit_message")) {
                e.setQuitMessage(null);
                SendMessageUtils.SMTAPrs(LangUtils.getConfigNoPrefixLangMessage("Listener.Player.Quit.Message").replace("%player%", player.getDisplayName()));
            } else {
                e.setQuitMessage(null);
            }
        }
    }
}
