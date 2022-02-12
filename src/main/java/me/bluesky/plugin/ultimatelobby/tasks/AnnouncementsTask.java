package me.bluesky.plugin.ultimatelobby.tasks;

import me.bluesky.plugin.ultimatelobby.utils.ColorUtils;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.scheduler.BukkitRunnable;

public class AnnouncementsTask extends BukkitRunnable {
    int i = 0;
    @Override
    public void run() {
        if (ConfigUtils.getAnnouncement().getBoolean("Announcement.Switch")) {
            if (i < ConfigUtils.getAnnouncement().getStringList("Announcement.Messages").size()) i++;
            if (i == ConfigUtils.getAnnouncement().getStringList("Announcement.Messages").size()) i=0;
            SendMessageUtils.SMTAPrs(ColorUtils.tMC(ConfigUtils.getAnnouncement().getStringList("Announcement.Messages").get(i)));
        }
    }
}
