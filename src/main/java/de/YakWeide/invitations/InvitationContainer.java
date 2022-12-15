package de.YakWeide.invitations;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.entity.Player;

import java.util.ArrayList;

//Todo: Mach die kagge englisch
//Verwalterklasse für alle Invitations
//Singleton Pattern
public class InvitationContainer {

    private final ArrayList<Invitation> invitations = new ArrayList<>();
    private static InvitationContainer instance;

    private InvitationContainer() {
    }

    ;

    public static InvitationContainer getInstance() {
        if (instance == null) {
            instance = new InvitationContainer();
        }
        return instance;
    }


    public static void addInvitation(Invitation invitation) {
        getInstance()._addInvitation(invitation);
    }


    public void _addInvitation(Invitation invitation) {
        Player inviter = invitation.getInviter();
        ArrayList<Invitation> inviterInvitationList = this._findAllOpenInvitationsOf(inviter);
        Player invitee = invitation.getInvitee();
        ArrayList<Invitation> inviteeInvitationList = this._findAllOpenInvitationsOf(invitee);
        if (inviterInvitationList != null) {
            for (Invitation i : inviterInvitationList) {
                invitations.remove(i);
            }
        }
        if (inviteeInvitationList != null) {
            for (Invitation i : inviteeInvitationList) {
                invitations.remove(i);
            }
        }
        invitations.add(invitation);
        ChatApi chatApi = ChatApi.getInstance();
        chatApi.sendMessage(invitee, chatApi.playerName(inviter) + ChatApi.prefixColor + " möchte dich zu " + invitation.getType().toString() + " herausfordern.");
        chatApi.sendMessage(invitee, "Klicke " + ChatApi.goodColor + "HIER " + ChatApi.prefixColor + "(/accept) um die Herausforderung anzunehmen oder " + ChatApi.badColor + "HIER " + ChatApi.prefixColor + "(/decline) um abzulehnen.");
    }

    public static void removeInvitation(Invitation invitation) {
        getInstance()._removeInvitation(invitation);
    }

    public void _removeInvitation(Invitation invitation) {
        invitations.remove(invitation);
    }

    public static Invitation findLatestReceivedInvitationOf(Player player) {
        return getInstance()._findLatestReceivedInvitationOf(player);
    }

    public Invitation _findLatestReceivedInvitationOf(Player player) {
        for (Invitation invitation : invitations) {
            if (invitation.containsInvitee(player)) {
                return invitation;
            }
        }
        return null;
    }

    public static Invitation findLatestSentInvitationOf(Player player) {
        return getInstance()._findLatestSentInvitationOf(player);
    }

    public Invitation _findLatestSentInvitationOf(Player player) {
        for (Invitation invitation : invitations) {
            if (invitation.containsInviter(player)) {
                return invitation;
            }
        }
        return null;
    }

    public static ArrayList<Invitation> findAllOpenInvitationsOf(Player player) {
        return getInstance()._findAllOpenInvitationsOf(player);
    }

    public ArrayList<Invitation> _findAllOpenInvitationsOf(Player player) {
        ArrayList<Invitation> list = new ArrayList<>();
        for (Invitation invitation : invitations) {
            if (invitation.containsInviter(player)) {
                list.add(invitation);
            }
            if (invitation.containsInvitee(player)) {
                list.add(invitation);
            }
        }
        return list;
    }


}
