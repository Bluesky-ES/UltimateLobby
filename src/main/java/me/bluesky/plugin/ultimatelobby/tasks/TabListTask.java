package me.bluesky.plugin.ultimatelobby.tasks;

import me.bluesky.plugin.ultimatelobby.utils.ColorUtils;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import net.minecraft.server.v1_8_R3.ChatComponentText;
import net.minecraft.server.v1_8_R3.PacketPlayOutPlayerListHeaderFooter;
import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.lang.reflect.Field;

public class TabListTask extends BukkitRunnable {
    String StrHeader = ColorUtils.tMC(ConfigUtils.getTablist().getString("TabList.Tab.Header"));
    String StrFooter = ColorUtils.tMC(ConfigUtils.getTablist().getString("TabList.Tab.Footer"));
    Object header = new ChatComponentText(StrHeader);
    Object footer = new ChatComponentText(StrFooter);

    PacketPlayOutPlayerListHeaderFooter packet = new PacketPlayOutPlayerListHeaderFooter();

    @Override
    public void run() {
        try {
            if (Bukkit.getOnlinePlayers().size() != 0) {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    Field a = packet.getClass().getDeclaredField("a");
                    a.setAccessible(true);
                    Field b = packet.getClass().getDeclaredField("b");
                    b.setAccessible(true);

                    a.set(packet, header);
                    b.set(packet, footer);

                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
