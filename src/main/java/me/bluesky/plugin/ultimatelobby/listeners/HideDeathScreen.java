package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class HideDeathScreen implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onDeath(PlayerDeathEvent e) {
        Player player = e.getEntity().getPlayer();
        if (plugin.getConfig().getBoolean("Functions.Disable_Death.Switch")) return;
        if (!plugin.getConfig().getBoolean("Functions.Hide_Death_Screen.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Hide_Death_Screen.Worlds").contains(player.getWorld().getName())) return;
        player.setRemainingAir(player.getMaximumAir());
        player.setHealth(player.getMaxHealth());
        player.setFireTicks(0);
        player.setFoodLevel(20);
        player.setSaturation(20);
        e.setKeepInventory(true);
        if (player.getBedSpawnLocation() != null) {
            player.teleport(player.getBedSpawnLocation());
        } else {
            player.teleport(player.getWorld().getSpawnLocation());
        }
    }
}
