package de.YakWeide.invitations;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.entity.Player;

public class InvitationPlayer {
    private final Player player;
    private Invitation mostRecentInvitation;

    public InvitationPlayer(Player player) {
        this.player = player;
    }

    public Invitation getMostRecentInvitation() {
        return mostRecentInvitation;
    }

    public void setMostRecentInvitation(Invitation mostRecentInvitation) {
        this.mostRecentInvitation = mostRecentInvitation;
        this.notifyPlayerAboutNewInvitation(mostRecentInvitation);
    }

    public void notifyPlayerAboutNewInvitation(Invitation invitation) {
        if (invitation == null) {
            return;
        }
        Player inviter = invitation.getInviter();
        Player invitee = invitation.getInvitee();
        ChatApi chatApi = ChatApi.getInstance();
        if (this.player == inviter) {
            chatApi.sendMessage(inviter, ChatApi.prefixColor + "Du hast " + chatApi.playerName(invitee) + " zu " + invitation.getType().toString() + " herausgefordert.");
        }
        if (this.player == invitee) {
            chatApi.sendMessage(invitee, chatApi.playerName(inviter) + ChatApi.prefixColor + " möchte dich zu " + invitation.getType().toString() + " herausfordern.");
            chatApi.sendMessage(invitee, "Klicke " + ChatApi.goodColor + "HIER " + ChatApi.prefixColor + "(/accept) um die Herausforderung anzunehmen oder " + ChatApi.badColor + "HIER " + ChatApi.prefixColor + "(/decline) um abzulehnen.");
        }
    }

    public void notifyPlayerAboutAcceptedInvitation(Invitation invitation) {
        ChatApi chatApi = ChatApi.getInstance();
        Player inviter = invitation.getInviter();
        Player invitee = invitation.getInvitee();
        if (this.player == inviter) {
            chatApi.sendMessage(inviter, chatApi.playerName(invitee) + " hat deine Einladung angenommen, " + ChatApi.goodColor + invitation.getType().toString() + ChatApi.prefixColor + " beginnt JETZT!");
        }
        if (this.player == invitee) {
            chatApi.sendMessage(invitee, ChatApi.goodColor + invitation.getType().toString() + ChatApi.prefixColor + " gegen " + chatApi.playerName(inviter) + " beginnt JETZT!");
        }
    }

    public void notifyPlayerAboutDeclinedInvitation(Invitation invitation) {
        ChatApi chatApi = ChatApi.getInstance();
        Player inviter = invitation.getInviter();
        Player invitee = invitation.getInvitee();
        if (this.player == inviter) {
            chatApi.sendMessage(inviter, chatApi.playerName(invitee) + ChatApi.badColor + " hat deine Einladung abgelehnt!");
        }
        if (this.player == invitee) {
            chatApi.sendMessage(invitee, ChatApi.badColor + "Du hast die Einladung gegen " + chatApi.playerName(inviter) + ChatApi.badColor + " abgelehnt!");
        }
    }
}
