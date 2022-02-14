package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.update.UpdateChecker;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import net.md_5.bungee.api.chat.*;
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
            if (!descriptionFile.getVersion().equalsIgnoreCase(version)) {
                BaseComponent line1 = new TextComponent("§b[UltimateLobby] A new version of the plugin is available: " + version + ", you are using: " + descriptionFile.getVersion() + ". ");
                BaseComponent line1a = new TextComponent("§6§lClick here");
                BaseComponent line1b = new TextComponent("§b to download.");
                line1a.setClickEvent(new ClickEvent(ClickEvent.Action.OPEN_URL, "https://www.spigotmc.org/resources/ultimatelobby-professional-lobby-integrated-management.99926/"));
                line1a.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new ComponentBuilder("§aClick to go to SpigotMC page.").create()));
                line1.addExtra(line1a);
                line1.addExtra(line1b);

                SendMessageUtils.SJMTPr(player, line1);
            }
        });
    }
}
