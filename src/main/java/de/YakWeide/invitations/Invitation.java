package de.YakWeide.invitations;

import org.bukkit.entity.Player;

public class Invitation {
    private Player inviter;
    private Player invitee;
    private InvitationApiUser type;

    public Invitation(Player inviter, Player invitee, InvitationApiUser type) {
        this.inviter = inviter;
        this.invitee = invitee;
        this.type = type;
    }

    public Player getInviter() {
        return inviter;
    }

    public void setInviter(Player inviter) {
        this.inviter = inviter;
    }

    public Player getInvitee() {
        return invitee;
    }

    public void setInvitee(Player invitee) {
        this.invitee = invitee;
    }

    public boolean containsInvitee(Player p) {
        return getInvitee() == p;
    }

    public boolean containsInviter(Player p) {
        return getInviter() == p;
    }

    public InvitationApiUser getType() {
        return type;
    }

    public void setType(InvitationApiUser type) {
        this.type = type;
    }


}
