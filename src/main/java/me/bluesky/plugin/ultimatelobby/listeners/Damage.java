package me.bluesky.plugin.ultimatelobby.listeners;

import me.bluesky.plugin.ultimatelobby.Main;
import me.bluesky.plugin.ultimatelobby.utils.LangUtils;
import me.bluesky.plugin.ultimatelobby.utils.SendMessageUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

public class Damage implements Listener {
    Main plugin = Main.getInstance();

    @EventHandler
    public void onPVP(EntityDamageByEntityEvent e) {
        if (!plugin.getConfig().getBoolean("Functions.Prevent_PVP.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_PVP.Worlds").contains(e.getEntity().getWorld().getName())) return;
        if (!(e.getEntity() instanceof Player) && !(e.getDamager() instanceof  Player)) return;
        if (e.getDamager().isOp()) return;
        e.setCancelled(true);
        SendMessageUtils.SMTPr((Player) e.getDamager(), LangUtils.getConfigLangMessage("Listener.PVP"));

    }

    @EventHandler
    public void onDamageByBlock(EntityDamageByBlockEvent e) {
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Block_Source_Damage.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Block_Source_Damage.Worlds").contains(e.getEntity().getWorld().getName())) return;
        e.setCancelled(true);
    }

    @EventHandler
    public void onDamage(EntityDamageEvent e) {
        if (!plugin.getConfig().getBoolean("Functions.Prevent_Damage.Switch")) return;
        if (!plugin.getConfig().getStringList("Functions.Prevent_Damage.Worlds").contains(e.getEntity().getWorld().getName())) return;
        e.setCancelled(true);
    }
}
