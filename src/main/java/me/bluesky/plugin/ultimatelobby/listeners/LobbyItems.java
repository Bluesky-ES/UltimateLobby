package me.bluesky.plugin.ultimatelobby.listeners;


import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class LobbyItems implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Lobby_Items.Switch")) return;
        ConfigUtils.getItems().getRoot().getKeys(false).forEach(item -> {
            ConfigurationSection section = ConfigUtils.getItems().getConfigurationSection(item);
            ItemStack itemStack = new ItemStack(Material.getMaterial(section.getString("Material", "STONE").toUpperCase()),
                    section.getInt("Amount", 1), (short) section.getInt("Data", 0));
            ItemMeta itemMeta = itemStack.getItemMeta();
            itemMeta.setDisplayName(section.getString("Name", "Unnamed"));
            itemMeta.setLore(section.getStringList("Lore"));
            itemStack.setItemMeta(itemMeta);

            player.getInventory().setItem(section.getInt("Slot", 0), itemStack);
        });
    }

    @EventHandler
    public void onClick(PlayerInteractEvent e) {
        Player player = e.getPlayer();

        if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK ||
                e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            ConfigUtils.getItems().getRoot().getKeys(false).forEach(item -> {
                ConfigurationSection section = ConfigUtils.getItems().getConfigurationSection(item);
                if (e.getItem() == null || e.getItem().getType() == Material.AIR) return;
                if (e.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(section.getString("Name"))) {
                    for (String command : section.getStringList("Commands"))
                        Bukkit.dispatchCommand(player, command);
                }
            });
        }
    }
}