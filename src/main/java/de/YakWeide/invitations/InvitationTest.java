package de.YakWeide.invitations;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.entity.Player;

public class InvitationTest implements InvitationApiUser {
    Player inviter;
    Player invitee;

    public InvitationTest(Player inviter, Player invitee) {
        this.inviter = inviter;
        this.invitee = invitee;
    }

    @Override
    public void onAcceptedInvitation() {
        ChatApi chatApi = ChatApi.getInstance();
        chatApi.sendMessage(inviter, ChatApi.goodColor + "Hat geklappt!");
        chatApi.sendMessage(invitee, ChatApi.goodColor + "Hat geklappt!");

    }

    public String toString() {
        return "Test";
    }
}
