package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HideDeathMessage implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        if (plugin.getConfig().getBoolean("Functions.Disable_Death.Switch")) return;
        if (!plugin.getConfig().getBoolean("Functions.Hide_Death_Message.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Hide_Death_Message.Worlds").contains(player.getWorld().getName())) return;
        e.setDeathMessage(null);
        e.setKeepInventory(true);
    }
}
