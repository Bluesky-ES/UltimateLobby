package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.List;

public class AntiSwearWords implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!ConfigUtils.getAntiSwearWords().getBoolean("Anti-SwearWords.Switch")) return;
        List<String> swearwords = ConfigUtils.getAntiSwearWords().getStringList("Anti-SwearWords.Disable_Words");
        for (String str : swearwords) {
            if (e.getMessage().contains(str)) {
                e.setCancelled(true);
                SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Listener.Anti-SwearWords"));
            }
        }
    }
}
