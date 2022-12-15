package de.YakWeide.invitations;

import org.bukkit.entity.Player;

import java.lang.reflect.Array;
import java.util.ArrayList;

//Verwalterklasse für alle Invitations
//Singleton Pattern
public class InvitationContainer {
    private ArrayList<Invitation> invitations;
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


    public static void addInvitation(Player inviter, Player invitee, InvitationApiUser type) {
        getInstance()._addInvitation(inviter, invitee, type);
    }

    public void _addInvitation(Invitation invitation) {
        Player inviter = invitation.getInviter();
        ArrayList<Invitation> inviterInvitationList = this._findAllOpenInvitationsOf(inviter);
        Player invitee = invitation.getInvitee();
        ArrayList<Invitation> inviteeInvitationList = this._findAllOpenInvitationsOf(invitee);
        if(inviterInvitationList != null){
            for(Invitation i : inviterInvitationList){
                invitations.remove(i);
            }

        }
        if(inviteeInvitationList != null){
            for(Invitation i : inviteeInvitationList){
                invitations.remove(i);
            }
        }
        invitations.add(invitation);

    }

    public void _addInvitation(Player inviter, Player invitee, InvitationApiUser type) {
        Invitation invitation = new Invitation(inviter, invitee, type);
        invitations.add(invitation);
    }

    public static void removeInvitation(Invitation invitation){
        getInstance()._removeInvitation(invitation);
    }

    public void _removeInvitation(Invitation invitation){
        invitations.remove(invitation);
    }

    public static Invitation findLatestReceivedInvitationOf(Player player){
        Invitation invitation = getInstance()._findLatestReceivedInvitationOf(player);
        return invitation;
    }

    public Invitation _findLatestReceivedInvitationOf(Player player){
        for(int i=0; i < invitations.size(); i++){
            if(this.invitations.get(i).containsInvitee(player)){
                return invitations.get(i);
            }
        }
        return null;
    }

    public static Invitation findLatestSentInvitationOf(Player player){
        Invitation invitation = getInstance()._findLatestSentInvitationOf(player);
        return invitation;
    }

    public Invitation _findLatestSentInvitationOf(Player player){
        for (Invitation invitation : invitations) {
            if (invitation.containsInviter(player)) {
                return invitation;
            }
        }
        return null;
    }

    public static ArrayList<Invitation> findAllOpenInvitationsOf(Player player){
        ArrayList<Invitation> list = getInstance()._findAllOpenInvitationsOf(player);
        return list;
    }
    public ArrayList<Invitation> _findAllOpenInvitationsOf(Player player){
        ArrayList<Invitation> list = new ArrayList<>();
        for(int i=0; i<invitations.size(); i++){
            if(this.invitations.get(i).containsInviter(player)){
                list.add(invitations.get(i));
            }
            if(this.invitations.get(i).containsInvitee(player)){
                list.add(invitations.get(i));
            }
        }
        return list;
    }








}
