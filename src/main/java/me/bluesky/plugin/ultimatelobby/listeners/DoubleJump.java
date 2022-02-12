package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;

import java.util.HashMap;
import java.util.Map;

public class DoubleJump implements Listener {
    Main plugin = Main.getInstance();
    public Map<Player, Long> cooldown = new HashMap<>();

    @EventHandler
    public void onToggleFlight(PlayerToggleFlightEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Double_Jump.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Double_Jump.Worlds").contains(player.getWorld().getName())) return;
        if (player.getGameMode() == GameMode.CREATIVE || player.getGameMode() == GameMode.SPECTATOR) return;
        if (player.hasPermission("ultimatelobby.fly")) return;
        if (!e.isFlying()) return;
        e.setCancelled(true);
        if (cooldown.getOrDefault(player, -1L) + plugin.getConfig().getInt("Functions.Double_Jump.CoolDown") * 1000L > System.currentTimeMillis()) {
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.CoolDown").replace("%s%", "" + plugin.getConfig().getInt("Functions.Double_Jump.CoolDown")));
        } else {
            player.setVelocity(player.getLocation().getDirection().multiply(plugin.getConfig().getDouble("Functions.Double_Jump.Power.Launch"))
                    .setY(plugin.getConfig().getDouble("Functions.Double_Jump.Power.Launch_Y")));
            cooldown.put(player, System.currentTimeMillis());
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player player = e.getPlayer();
        if (!plugin.getConfig().getBoolean("Functions.Double_Jump.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Double_Jump.Worlds").contains(player.getWorld().getName())) return;
        if (player.hasPermission("ultimatelobby.fly")) return;
        player.setAllowFlight(true);
    }
}
