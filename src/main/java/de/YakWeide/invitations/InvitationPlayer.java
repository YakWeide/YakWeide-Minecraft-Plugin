package de.YakWeide.invitations;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.entity.Player;

public class InvitationPlayer {
    private Player player;
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

    public void notifyPlayerAboutNewInvitation(Invitation invitation){
        Player inviter = invitation.getInviter();
        Player invitee = invitation.getInvitee();
        ChatApi chatApi = ChatApi.getInstance();
        if(this.player == inviter){
            chatApi.sendMessage(inviter,  ChatApi.prefixColor + " Du hast " + chatApi.playerName(invitee) + " zu " + invitation.getType().toString() + " herausgefordert.");
        }
        if(this.player == invitee){
            chatApi.sendMessage(invitee, chatApi.playerName(inviter) + ChatApi.prefixColor + " möchte dich zu " + invitation.getType().toString() + " herausfordern.");
            chatApi.sendMessage(invitee, "Klicke " + ChatApi.goodColor + "HIER " + ChatApi.prefixColor + "(/accept) um die Herausforderung anzunehmen oder " + ChatApi.badColor + "HIER " + ChatApi.prefixColor + "(/decline) um abzulehnen.");
        }
    }
}
