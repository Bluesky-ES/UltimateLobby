package me.bluesky.plugin.ultimatelobby.commands;

import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Spawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            SendMessageUtils.SMTC(LangUtils.getConfigLangMessage("Error.ExecutedByConsole"));
            return true;
        }

        Player player = (Player) sender;
        if (ConfigUtils.getSpawn().getString("Spawn.World") == null) {
            SendMessageUtils.SMTPr(player, LangUtils.getConfigLangMessage("Command.Spawn.NotSet"));
            return true;
        }

        World world = Bukkit.getWorld(ConfigUtils.getSpawn().getString("Spawn.World"));
        Location LobbyPoint = new Location(world, ConfigUtils.getSpawn().getInt("Spawn.X"), ConfigUtils.getSpawn().getInt("Spawn.Y"), ConfigUtils.getSpawn().getInt("Spawn.Z"));
        LobbyPoint.setYaw(ConfigUtils.getSpawn().getInt("Spawn.Yaw"));
        LobbyPoint.setPitch(ConfigUtils.getSpawn().getInt("Spawn.Pitch"));
        player.teleport(LobbyPoint);
        return false;
    }
}
