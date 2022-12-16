package de.YakWeide.invitations;

import org.bukkit.entity.Player;

import java.util.HashMap;

public class InvitationManager {
    private final HashMap<Player, InvitationPlayer> playerMap = new HashMap<>();
    private static InvitationManager instance;

    private InvitationManager() {
    }

    public static InvitationManager getInstance(){
        if(instance == null){
            instance = new InvitationManager();
        }
        return instance;
    }

    //Call this method when you want to send an invite.
    //The invitee will be notified and can accept/decline the invite
    //The inviter will be nofied if the invitee accepted/declined
    //The onAcceptedInvitation of your InvitationApiUser will be called when and if the invitee accepts
    public static void invite(Player inviter, Player invitee, InvitationApiUser obj){
        getInstance()._invite(inviter, invitee, obj);

    }

    public void _invite(Player inviter, Player invitee, InvitationApiUser obj){
        Invitation invitation = new Invitation(inviter, invitee, obj);
        InvitationPlayer inviterWrapper = this._getInvitationPlayer(inviter);
        InvitationPlayer inviteeWrapper = this._getInvitationPlayer(invitee);

        //inviter and invitee will be informed that the new invitation was created and waits to be accepted/ declined
        inviterWrapper.setMostRecentInvitation(invitation);
        inviteeWrapper.setMostRecentInvitation(invitation);

    }

    //Returns the InvitationPlayer for a Player: Searches if there already is an invitationPlayer to player, if not creates one.
    public static InvitationPlayer getInvitationPlayer(Player player){
        return getInstance()._getInvitationPlayer(player);
    }
    public InvitationPlayer _getInvitationPlayer(Player player){
        InvitationPlayer invitationPlayer = playerMap.get(player);
        if(invitationPlayer == null){
            invitationPlayer = new InvitationPlayer(player);
            playerMap.put(player, invitationPlayer);
        }
        return invitationPlayer;
    }

    public static void remove(Player player){
        getInstance()._remove(player);
    }

    public void _remove(Player player){
        playerMap.remove(player);
    }
}
