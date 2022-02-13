package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.ColorUtils;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class ChatFormat implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Chat_Format.Switch")) return;
        if (ConfigUtils.getAntiSwearWords().getBoolean("Anti-SwearWords.Switch")) {
            List<String> swearwords = ConfigUtils.getAntiSwearWords().getStringList("Anti-SwearWords.Disable_Words");
            for (String str : swearwords)
                if (e.getMessage().contains(str)) return;
        }
        e.setCancelled(true);
        SendMessageUtils.SMTAPrs(
                ColorUtils.tMC(plugin.getConfig().getString("Functions.Chat_Format.Format")
                        .replace("%player%", player.getDisplayName()).replace("%message%", e.getMessage())));
    }
}
