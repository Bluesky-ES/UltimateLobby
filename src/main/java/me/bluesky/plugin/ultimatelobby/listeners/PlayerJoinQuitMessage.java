package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.ColorUtils;
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
            if (plugin.getConfig().getBoolean("Functions.Player.Join.Title.Switch")) {
                TitleUtils.STTPr(player, ColorUtils.tMC(plugin.getConfig().getString("Functions.Player.Join.Title.MainTitle")) .replace("%player%", player.getDisplayName()),
                        ColorUtils.tMC(plugin.getConfig().getString("Functions.Player.Join.Title.SubTitle")).replace("%player%", player.getDisplayName()),
                        plugin.getConfig().getInt("Functions.Player.Join.Title.Times.FadeIn"),
                        plugin.getConfig().getInt("Functions.Player.Join.Title.Times.Stay"),
                        plugin.getConfig().getInt("Functions.Player.Join.Title.Times.FadeOut"));
            }
            if (plugin.getConfig().getBoolean("Functions.Player.Join.Message.Switch")) {
                e.setJoinMessage(null);
                SendMessageUtils.SMTAPrs(ColorUtils.tMC(plugin.getConfig().getString("Functions.Player.Join.Message.Content")).replace("%player%", player.getDisplayName()));
            }
            if (plugin.getConfig().getBoolean("Functions.Player.Join.Sound.Switch")) {
                Sound sound = Sound.valueOf(plugin.getConfig().getString("Functions.Player.Join.Sound.Type"));
                player.playSound(player.getLocation(), sound, 100, 1);
            }
            if (plugin.getConfig().getBoolean("Functions.Player.Join.Motd.Switch")) {
                for (String string : plugin.getConfig().getStringList("Functions.Player.Join.Motd.Content")) {
                    SendMessageUtils.SMTPr(player, ColorUtils.tMC(string));
                }
            }
        }
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Player player = e.getPlayer();
        if (plugin.getConfig().getBoolean("Functions.Player.Quit.Message.Switch")) {
            if (player.hasPermission("ultimatelobby.join_quit_message")) {
                e.setQuitMessage(null);
                SendMessageUtils.SMTAPrs(ColorUtils.tMC(plugin.getConfig().getString("Functions.Player.Quit.Message.Content")).replace("%player%", player.getDisplayName()));
            } else {
                e.setQuitMessage(null);
            }
        }
    }
}
