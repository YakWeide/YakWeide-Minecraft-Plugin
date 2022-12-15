package de.YakWeide.invitations;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvitationCommands implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("accept")) {
            Invitation invitation = InvitationContainer.findLatestReceivedInvitationOf(player);
            //TODO: null abfangen: accept sender Nachricht senden
            if(invitation == null){

            }
            InvitationApiUser type = invitation.getType();
            Player inviter = invitation.getInviter();
            Player invitee = invitation.getInvitee();
            //TODO: inviter und invitee müssen nachrichten bekommen, type muss onAccept aufgerufen bekommen

        }


        else if(label.equalsIgnoreCase("decline")){
            Invitation invitation = InvitationContainer.findLatestReceivedInvitationOf(player);
            //TODO: null abfangen: decline sender Nachricht senden
            InvitationApiUser type = invitation.getType();
            Player inviter = invitation.getInviter();
            Player invitee = invitation.getInvitee();
            //inviter muss benachrichtigt werden, dass abgelehnt wurde

        }
        return false;
    }
}
