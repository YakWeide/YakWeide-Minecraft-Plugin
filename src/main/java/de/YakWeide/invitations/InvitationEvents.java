package de.YakWeide.invitations;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class InvitationEvents implements Listener {
    //Players InvitationPlayer will no longer be stored if the Player quits.
    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        InvitationManager.remove(event.getPlayer());
    }
}
