package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Monster;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;

public class CreatureSpawn implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onMonsterSpawn(CreatureSpawnEvent e) {
        if (!plugin.getConfig().getBoolean("Functions.Disable_Monster_Spawn.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Disable_Monster_Spawn.Worlds").contains(e.getEntity().getWorld().getName())) return;
        if (!(e.getEntity() instanceof Monster)) return;
        e.setCancelled(true);

    }

    @EventHandler
    public void onAnimalSpawn(CreatureSpawnEvent e) {
        if (!plugin.getConfig().getBoolean("Functions.Disable_Animal_Spawn.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Disable_Animal_Spawn.Worlds").contains(e.getEntity().getWorld().getName())) return;
        if (!(e.getEntity() instanceof Animals)) return;
        e.setCancelled(true);
    }
}
