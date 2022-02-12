package me.bluesky.plugin.ultimatelobby.tasks;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.TimeType;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.scheduler.BukkitRunnable;

public class TimeLock extends BukkitRunnable {
    Main plugin = Main.getInstance();
    @Override
    public void run() {
        if (plugin.getConfig().getBoolean("Functions.Time_Lock.Switch")) {
            for (int i = 0; i < plugin.getConfig().getStringList("Functions.Time_Lock.Worlds").size(); i++) {
                if (Bukkit.getWorld(plugin.getConfig().getStringList("Functions.Time_Lock.Worlds").get(i)) != null){
                    World world = Bukkit.getWorld(plugin.getConfig().getStringList("Functions.Time_Lock.Worlds").get(i));
                    if (plugin.getConfig().getString("Functions.Time_Lock.Type").equalsIgnoreCase("DAY"))
                        world.setTime(TimeType.DAY.toTick());
                    if (plugin.getConfig().getString("Functions.Time_Lock.Type").equalsIgnoreCase("NIGHT"))
                        world.setTime(TimeType.NIGHT.toTick());
                }
            }
        }
    }
}
