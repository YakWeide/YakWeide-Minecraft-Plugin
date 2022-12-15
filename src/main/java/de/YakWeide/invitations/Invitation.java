package de.YakWeide.invitations;

import org.bukkit.entity.Player;

public class Invitation {
    private Player inviter;
    private Player invitee;
    private InvitationType invitation;

    public Invitation(Player inviter, Player invitee, InvitationType invitation) {
        this.inviter = inviter;
        this.invitee = invitee;
        this.invitation = invitation;
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

    public InvitationType getInvitation() {
        return invitation;
    }

    public void setInvitation(InvitationType invitation) {
        this.invitation = invitation;
    }




}
