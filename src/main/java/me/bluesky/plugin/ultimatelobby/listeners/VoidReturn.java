package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class VoidReturn implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onVoid(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Void_Return.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Void_Return.Worlds").contains(player.getWorld().getName())) return;
        if (player.getLocation().getY() < -1) {
            if (ConfigUtils.getSpawn().getString("Spawn.World") != null) {
                World world = Bukkit.getWorld(ConfigUtils.getSpawn().getString("Spawn.World"));
                Location LobbyPoint = new Location(world, ConfigUtils.getSpawn().getInt("Spawn.X"), ConfigUtils.getSpawn().getInt("Spawn.Y"), ConfigUtils.getSpawn().getInt("Spawn.Z"));
                LobbyPoint.setYaw(ConfigUtils.getSpawn().getInt("Spawn.Yaw"));
                LobbyPoint.setPitch(ConfigUtils.getSpawn().getInt("Spawn.Pitch"));
                player.teleport(LobbyPoint);
            }
        }
    }
}
