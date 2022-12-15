package de.YakWeide;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    private final ChatApi chatApi = ChatApi.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event){
        Player p = event.getEntity();
        Location location = p.getLocation();
        chatApi.sendMessage(p, ChatApi.badColor + "Todeskoordinaten: " + ChatApi.prefixColor + (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ());
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event){
        Player p = event.getPlayer();
        if(p.getName().equalsIgnoreCase("AquaDrachit")){
            event.setQuitMessage("ciaooooooooo");
        }
        if(p.getName().equalsIgnoreCase("TonkHD")){
            event.setQuitMessage(ChatColor.AQUA + "WIEDERSCHAUN UND REINGEHAUN!");
        }
    }



}

