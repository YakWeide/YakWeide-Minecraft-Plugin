package de.YakWeide;

import de.YakWeide.chatApi.ChatApi;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {

    private final ChatApi chatApi = ChatApi.getInstance();

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent e){
        Player p = e.getPlayer();
        Location location = p.getLocation();
        chatApi.sendMessage(p, ChatApi.badColor + "Todeskoordinaten: " + ChatApi.prefixColor + (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ());
    }
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent e){
        Player p = e.getPlayer();
        if(p.getName().equalsIgnoreCase("AquaDrache")){
            e.quitMessage(PlainTextComponentSerializer.plainText().deserialize("ciaooooooooo"));
        }
    }



}

