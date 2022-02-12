package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class FoodLevelChange implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent e) {
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Food_Level_Change.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Food_Level_Change.Worlds").contains(e.getEntity().getWorld().getName())) return;
        e.setFoodLevel(20);
        e.setCancelled(true);
    }
}
