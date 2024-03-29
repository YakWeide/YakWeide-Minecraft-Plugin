package de.YakWeide;

import de.YakWeide.YakPlayerUtility.VerwaltungsYak;
import de.YakWeide.YakPlayerUtility.YakPlayer;
import de.YakWeide.chatApi.ChatApi;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class Events implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player p = event.getEntity();
        Location location = p.getLocation();
        ChatApi.sendMessage(p, ChatApi.badColor + "Todeskoordinaten: " + ChatApi.prefixColor + (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        Player player = event.getPlayer();
        YakPlayer yakPlayer = VerwaltungsYak.getYakPlayer(player);
        assert yakPlayer != null;
        if (yakPlayer.getCustomQuitMessage() != null && !yakPlayer.getCustomQuitMessage().isEmpty()) {
            event.setQuitMessage(yakPlayer.getCustomQuitMessage());
        }


        if (player.getName().equalsIgnoreCase("AquaDrachit")) {
            event.setQuitMessage("ciaooooooooo");
        }
        if (player.getName().equalsIgnoreCase("TonkHD")) {
            event.setQuitMessage(ChatColor.AQUA + "WIEDERSCHAUN UND REINGEHAUN!");
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        YakPlayer yakPlayer;
        if (!VerwaltungsYak.isInYakPlayerList(player)) {
            VerwaltungsYak.addYakPlayer(player);
        }
        yakPlayer = VerwaltungsYak.getYakPlayer(player);
        assert yakPlayer != null;
        if (yakPlayer.getCustomJoinMessage() != null && !yakPlayer.getCustomJoinMessage().isEmpty()) {
            event.setJoinMessage(yakPlayer.getCustomJoinMessage());
        }
    }


}

