package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class Prompt implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!player.isOp()) return;
        if (ConfigUtils.getSpawn().getString("Spawn.World") != null) return;
        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Listener.Prompt"));
    }
}
