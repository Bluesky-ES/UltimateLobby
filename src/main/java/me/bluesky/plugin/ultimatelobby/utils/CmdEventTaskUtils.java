package me.bluesky.plugin.ultimatelobby.utils;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.commands.*;
import me.bluesky.plugin.ultimatelobby.listeners.*;
import me.bluesky.plugin.ultimatelobby.tasks.AnnouncementsTask;
import me.bluesky.plugin.ultimatelobby.tasks.TabListTask;
import me.bluesky.plugin.ultimatelobby.tasks.TimeLock;
import me.bluesky.plugin.ultimatelobby.tasks.ScoreboardTask;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.scheduler.BukkitTask;

public class CmdEventTaskUtils {
    public static void registerCommands() {
        Main plugin = Main.getInstance();
        plugin.getCommand("ultimatelobby").setExecutor(new MainCommand());
        plugin.getCommand("setlobby").setExecutor(new SetLobby());
        plugin.getCommand("spawn").setExecutor(new Spawn());
        plugin.getCommand("fly").setExecutor(new Fly());
        plugin.getCommand("vanish").setExecutor(new Vanish());
        plugin.getCommand("clearchat").setExecutor(new ClearChat());
        plugin.getCommand("broadcast").setExecutor(new Broadcast());
    }

    public static void registerListeners(Plugin plugin) {
        PluginManager pm = plugin.getServer().getPluginManager();

        pm.registerEvents(new LoginTeleport(), plugin);
        pm.registerEvents(new HideDeathMessage(), plugin);
        pm.registerEvents(new HideDeathScreen(), plugin);
        pm.registerEvents(new DisableDeath(), plugin);
        pm.registerEvents(new WeatherLock(), plugin);
        pm.registerEvents(new Block(), plugin);
        pm.registerEvents(new Inventory(), plugin);
        pm.registerEvents(new CreatureSpawn(), plugin);
        pm.registerEvents(new Damage(), plugin);
        pm.registerEvents(new VoidReturn(), plugin);
        pm.registerEvents(new Prompt(), plugin);
        pm.registerEvents(new PlayerJoinQuitMessage(), plugin);
        pm.registerEvents(new AutoFlight(), plugin);
        pm.registerEvents(new FoodLevelChange(), plugin);
        pm.registerEvents(new DoubleJump(), plugin);
        pm.registerEvents(new LaunchPad(), plugin);
        pm.registerEvents(new ChatFormat(), plugin);
        pm.registerEvents(new LobbyItems(), plugin);
        pm.registerEvents(new Update(), plugin);
        pm.registerEvents(new AntiSwearWords(), plugin);
    }

    public static void registerTasks(Plugin plugin) {
        BukkitTask TimeLock = new TimeLock().runTaskTimer(plugin, 0, 20);
        BukkitTask AnnouncementsTask = new AnnouncementsTask().runTaskTimer(plugin, 0, ConfigUtils.getAnnouncement().getInt("Announcement.Interval") * 20L);
        BukkitTask ScoreboardTask = new ScoreboardTask().runTaskTimer(plugin, 0, ConfigUtils.getScoreboard().getInt("Scoreboard.Refresh_Interval") * 20L);
        BukkitTask TabListTask = new TabListTask().runTaskTimer(plugin, 0, ConfigUtils.getTablist().getInt("TabList.Refresh_Interval") * 20L);
    }
}
