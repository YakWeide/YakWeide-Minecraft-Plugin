package de.YakWeide.invitations;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class InvitationCommands implements CommandExecutor {

    private final ChatApi chatApi = ChatApi.getInstance();

    @Override
    public boolean onCommand(@NonNull CommandSender sender,@NonNull Command cmd,@NonNull String label, String[] args) {
        //Only accept Commands coming from human
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        //What to do if player types command /accept
        if (label.equalsIgnoreCase("accept")) {
            InvitationPlayer invitationPlayer = InvitationManager.getInvitationPlayer(player);
            Invitation invitation = invitationPlayer.getMostRecentInvitation();

            //Check if player has an open Invitation
            if(invitation == null || invitation.getInviter() == player){
                chatApi.sendMessage(player, ChatApi.badColor + "Du hast keine offenen Einladungen!");
            }

            //Invitation was accepted, notify inviter and invitee
            InvitationPlayer inviter = InvitationManager.getInvitationPlayer(invitation.getInviter());
            InvitationPlayer invitee = InvitationManager.getInvitationPlayer(invitation.getInvitee());
            inviter.notifyPlayerAboutAcceptedInvitation(invitation);
            invitee.notifyPlayerAboutAcceptedInvitation(invitation);

            //TODO: Play Sound
            invitation.getType().onAcceptedInvitation();
            return true;
        }


        else if(label.equalsIgnoreCase("decline")){
            InvitationPlayer invitationPlayer = InvitationManager.getInvitationPlayer(player);
            Invitation invitation = invitationPlayer.getMostRecentInvitation();

            //Check if player has an open Invitation
            if(invitation == null || invitation.getInviter() == player){
                chatApi.sendMessage(player, ChatApi.badColor + "Du hast keine offenen Einladungen!");
            }

            //Invitation was declined, notify inviter and invitee
            InvitationPlayer inviter = InvitationManager.getInvitationPlayer(invitation.getInviter());
            InvitationPlayer invitee = InvitationManager.getInvitationPlayer(invitation.getInvitee());
            inviter.notifyPlayerAboutDeclinedInvitation(invitation);
            invitee.notifyPlayerAboutDeclinedInvitation(invitation);
            return true;
        }


        else if(label.equalsIgnoreCase("invitationtest")) {
            Player invitedPlayer = null;
            for(Player p : Bukkit.getOnlinePlayers()){
                if(p.getName().equals(args[0])){
                    invitedPlayer = p;
                    break;
                }
            }
            InvitationTest test= new InvitationTest(player, invitedPlayer);
            InvitationManager.invite(player, invitedPlayer, test);
            return true;
        }
        return false;
    }
}
