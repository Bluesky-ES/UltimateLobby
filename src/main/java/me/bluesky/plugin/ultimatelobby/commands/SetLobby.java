package me.bluesky.plugin.ultimatelobby.commands;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class SetLobby implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        Main plugin = Main.getInstance();
        if (!(sender instanceof Player)) {
            SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Error.ExecutedByConsole"));
            return true;
        }

        Player player = (Player) sender;
        if (player.hasPermission("ultimatelobby.setlobby")) {
            if (player.getLocation().subtract(0, 1, 0).getBlock().getType() == Material.AIR) {
                SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.SetLobby.AirUnderFoot"));
                return true;
            }

            double x = player.getLocation().getX();
            double y = player.getLocation().getY();
            double z = player.getLocation().getZ();
            int ix = (int) x;
            int iy = (int) y;
            int iz = (int) z;
            String location = ix + "," + iy + "," + iz;
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.SetLobby.SetupSuccessful").replace("%location%", location));
            ConfigUtils.getSpawn().set("Spawn.World", player.getWorld().getName());
            ConfigUtils.getSpawn().set("Spawn.X", player.getLocation().getX());
            ConfigUtils.getSpawn().set("Spawn.Y", player.getLocation().getY());
            ConfigUtils.getSpawn().set("Spawn.Z", player.getLocation().getZ());
            ConfigUtils.getSpawn().set("Spawn.Yaw", player.getLocation().getYaw());
            ConfigUtils.getSpawn().set("Spawn.Pitch", player.getLocation().getPitch());
            ConfigUtils.saveSpawn();
            return true;
        }
        SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Error.NoPermissionToUseCommand"));
        return false;
    }
}
