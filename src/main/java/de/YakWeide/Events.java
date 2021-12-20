package de.YakWeide;

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getPlayer();
        Location location = p.getLocation();
        p.sendMessage( ChatColor.AQUA + "Todeskoordinaten: " + (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ());
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.getName().equalsIgnoreCase("AquaDrache")){
            e.quitMessage(PlainTextComponentSerializer.plainText().deserialize("test"));        //Falls das geht kann die zeile unten weg
            e.setQuitMessage(ChatColor.GOLD + "ciaooooooooo");
        }
    }



}

