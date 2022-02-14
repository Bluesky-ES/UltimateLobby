package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class LaunchPad implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onMove(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Launch_Pad.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Launch_Pad.Worlds").contains(player.getWorld().getName())) return;
        Material block = Material.valueOf(plugin.getConfig().getString("Functions.Launch_Pad.Block"));
        if (player.getLocation().getBlock().getType() == block) {
            player.setVelocity(player.getLocation().getDirection().multiply(plugin.getConfig().getDouble("Functions.Launch_Pad.Power.Launch"))
                    .setY(plugin.getConfig().getDouble("Functions.Launch_Pad.Power.Launch_Y")));
            Sound sound = Sound.valueOf(plugin.getConfig().getString("Functions.Launch_Pad.Sound"));
            player.playSound(player.getLocation(), sound, 100, 1);
        }
    }
}
