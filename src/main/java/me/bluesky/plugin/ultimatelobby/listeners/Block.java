package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public class Block implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Block_Place.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Block_Place.Worlds").contains(player.getWorld().getName())) return;
        if (player.isOp()) return;
        e.setCancelled(true);
        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Listener.Block.Place"));
    }

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Block_Break.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Block_Break.Worlds").contains(player.getWorld().getName()))return;
        if (player.isOp()) return;
        e.setCancelled(true);
        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Listener.Block.Break"));
    }
}
