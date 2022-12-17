package de.YakWeide.miniGameApi;

import de.YakWeide.invitations.InvitationApiUser;
import org.bukkit.entity.Player;

/**
 * Your MiniGame has to implement this Interface if you want to use the MiniGame API
 *
 * @author Jan Reitz
 */
public abstract class MiniGame implements InvitationApiUser {
    /**
     * This name will be used for chat messages regarding invitations, ...
     *
     * @return The Name of your MiniGame. NOT NULL!
     */
    public abstract String getName();

    /**
     * Every miniGame has a Challenger and a Challenged, the MiniGameApi has to know who is who
     *
     * @return The Challenger. NOT NULL!
     */
    public abstract Player getChallenger();

    /**
     * Every miniGame has a Challenger and a Challenged, the MiniGameApi has to know who is who
     *
     * @return The Challenged. NOT NULL!
     */
    public abstract Player getChallenged();

    /**
     * Your method that will be called when and if the invitation is accepted
     */
    public abstract void onGameStart();

    //abstrahiere InvitationAPI
    public void onAcceptedInvitation() {
        MGPlayerManager.addMGPlayer(this.getChallenger(), this);
        MGPlayerManager.addMGPlayer(this.getChallenged(), this);
        this.onGameStart();
    }


}
