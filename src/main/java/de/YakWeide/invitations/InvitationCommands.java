package de.YakWeide.invitations;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class InvitationCommands implements CommandExecutor {

    private ChatApi chatApi = ChatApi.getInstance();

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("accept")) {
            Invitation invitation = InvitationContainer.findLatestReceivedInvitationOf(player);
            if(invitation == null){
                chatApi.sendMessage(player, ChatApi.badColor + "Du hast keine offenen Einladungen!");
                return false;
            }
            InvitationApiUser type = invitation.getType();
            Player inviter = invitation.getInviter();
            Player invitee = invitation.getInvitee();
            chatApi.sendMessage(inviter, chatApi.playerName(invitee) + "hat deine Einladung angenommen, " + ChatApi.goodColor + invitation.getType().toString() + ChatApi.prefixColor + " beginnt JETZT!");
            chatApi.sendMessage(invitee, ChatApi.goodColor + invitation.getType().toString() + ChatApi.prefixColor + " gegen " + chatApi.playerName(inviter) + "beginnt JETZT!");
            //TODO: Play Sound
            invitation.getType().onAcceptedInvitation();
        }


        else if(label.equalsIgnoreCase("decline")){
            Invitation invitation = InvitationContainer.findLatestReceivedInvitationOf(player);
            if(invitation == null){
                chatApi.sendMessage(player, ChatApi.badColor + "Du hast keine offenen Einladungen!");
                return false;
            }
            InvitationApiUser type = invitation.getType();
            Player inviter = invitation.getInviter();
            Player invitee = invitation.getInvitee();
            chatApi.sendMessage(inviter, chatApi.playerName(invitee) + ChatApi.badColor + "hat deine Einladung abgelehnt!");
            chatApi.sendMessage(invitee, ChatApi.badColor + "Du hast die Einladung gegen " + chatApi.playerName(inviter) + ChatApi.badColor + "abgelehnt!");
        }
        return false;
    }
}
