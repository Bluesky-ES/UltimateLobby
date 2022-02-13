package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;

public class OpenInventory implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onOpenInventory(InventoryOpenEvent e) {
        Player player = (Player) e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Inventory_Open.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Inventory_Open.Worlds").contains(player.getWorld().getName())) return;
        for (String str : plugin.getConfig().getStringList("Functions.Prevent_Inventory_Open.Except")) {
            InventoryType invt = InventoryType.valueOf(str);
            if (e.getInventory().getType() == invt) return;
        }
        e.setCancelled(true);
    }
}
