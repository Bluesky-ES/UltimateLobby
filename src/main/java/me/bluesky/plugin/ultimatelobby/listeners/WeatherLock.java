package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.weather.WeatherChangeEvent;

public class WeatherLock implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onWeatherChange(WeatherChangeEvent e) {
        if (!plugin.getConfig().getBoolean("Functions.Weather_Lock.Switch") && !e.toWeatherState()) return;
        if (!plugin.getConfig().getStringList("Functions.Weather_Lock.Worlds").contains(e.getWorld().getName())) return;
        e.setCancelled(true);
    }
}
