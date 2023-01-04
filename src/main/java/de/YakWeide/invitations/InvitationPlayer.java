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
        if (this.player == inviter) {
            ChatApi.sendMessage(inviter, ChatApi.prefixColor + "Du hast " + ChatApi.playerName(invitee) + " zu " + invitation.getType().toString() + " herausgefordert.");
        }
        if (this.player == invitee) {
            ChatApi.sendMessage(invitee, ChatApi.playerName(inviter) + ChatApi.prefixColor + " möchte dich zu " + invitation.getType().toString() + " herausfordern.");
            ChatApi.sendMessage(invitee, "Klicke " + ChatApi.goodColor + "HIER " + ChatApi.prefixColor + "(/accept) um die Herausforderung anzunehmen oder " + ChatApi.badColor + "HIER " + ChatApi.prefixColor + "(/decline) um abzulehnen.");
        }
    }

    public void notifyPlayerAboutAcceptedInvitation(Invitation invitation) {
        Player inviter = invitation.getInviter();
        Player invitee = invitation.getInvitee();
        if (this.player == inviter) {
            ChatApi.sendMessage(inviter, ChatApi.playerName(invitee) + " hat deine Einladung angenommen, " + ChatApi.goodColor + invitation.getType().toString() + ChatApi.prefixColor + " beginnt JETZT!");
        }
        if (this.player == invitee) {
            ChatApi.sendMessage(invitee, ChatApi.goodColor + invitation.getType().toString() + ChatApi.prefixColor + " gegen " + ChatApi.playerName(inviter) + " beginnt JETZT!");
        }
    }

    public void notifyPlayerAboutDeclinedInvitation(Invitation invitation) {
        Player inviter = invitation.getInviter();
        Player invitee = invitation.getInvitee();
        if (this.player == inviter) {
            ChatApi.sendMessage(inviter, ChatApi.playerName(invitee) + ChatApi.badColor + " hat deine Einladung abgelehnt!");
        }
        if (this.player == invitee) {
            ChatApi.sendMessage(invitee, ChatApi.badColor + "Du hast die Einladung gegen " + ChatApi.playerName(inviter) + ChatApi.badColor + " abgelehnt!");
        }
    }
}
