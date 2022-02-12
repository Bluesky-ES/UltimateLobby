package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;

public class Inventory implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Item_Drop.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Item_Drop.Worlds").contains(player.getWorld().getName())) return;
        if (player.isOp()) return;
        e.setCancelled(true);
        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Listener.Inventory.Drop"));
    }

    @EventHandler
    public void onPick(PlayerPickupItemEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Item_Pick_Up.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Item_Pick_Up.Worlds").contains(player.getWorld().getName())) return;
        if (player.isOp()) return;
        e.setCancelled(true);
        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Listener.Inventory.PickUp"));
    }

    @EventHandler
    public void onClick(InventoryClickEvent e) {
        Player player = (Player) e.getWhoClicked();
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Item_Move.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Item_Move.Worlds").contains(player.getWorld().getName())) return;
        if (player.isOp()) return;
        e.setCancelled(true);
    }
}
