package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class LoginTeleport implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Login_Teleport_to_Lobby_Point.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Login_Teleport_to_Lobby_Point.Worlds").contains(player.getWorld().getName())) return;
        if (ConfigUtils.getSpawn().getString("Spawn.World") == null) return;
        World world = Bukkit.getWorld(ConfigUtils.getSpawn().getString("Spawn.World"));
        Location LobbyPoint = new Location(world, ConfigUtils.getSpawn().getInt("Spawn.X"), ConfigUtils.getSpawn().getInt("Spawn.Y"), ConfigUtils.getSpawn().getInt("Spawn.Z"));
        LobbyPoint.setYaw(ConfigUtils.getSpawn().getInt("Spawn.Yaw"));
        LobbyPoint.setPitch(ConfigUtils.getSpawn().getInt("Spawn.Pitch"));
        player.teleport(LobbyPoint);
    }
}
