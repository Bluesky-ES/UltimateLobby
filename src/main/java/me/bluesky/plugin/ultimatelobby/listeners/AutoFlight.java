package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class AutoFlight implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Auto_Flight.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Auto_Flight.Worlds").contains(player.getWorld().getName())) return;
        if (!player.hasPermission("ultimatelobby.fly")) return;
        player.setAllowFlight(true);
        player.setFlying(true);
        player.setFlySpeed(0.1F);
    }
}
