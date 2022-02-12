package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.update.UpdateChecker;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.PluginDescriptionFile;

public class Update implements Listener {
    Main plugin = Main.getInstance();
    private static PluginDescriptionFile descriptionFile;

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        descriptionFile = plugin.getDescription();
        if (!player.isOp()) return;
        if (!plugin.getConfig().getBoolean("Update_Checker")) return;
        new UpdateChecker(99926).getLatestVersion(version -> {
            if (descriptionFile.getVersion().equalsIgnoreCase(version)) return;
            SendMessageUtils.SMTPr(player,
                    "§b[UltimateLobby] A new version of the plugin is available: " + version + ", you are using: " + descriptionFile.getVersion(),
                    "§bDownload link: §6§lhttps://www.spigotmc.org/resources/ultimatelobby-professional-lobby-integrated-management.99926/"
            );
        });
    }
}
