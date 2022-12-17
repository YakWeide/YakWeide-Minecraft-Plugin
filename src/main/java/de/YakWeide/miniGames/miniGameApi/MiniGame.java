package de.YakWeide.miniGames.miniGameApi;

import de.YakWeide.invitations.InvitationApiUser;
import org.bukkit.entity.Player;

/**
 * Your MiniGame has to implement this Interface if you want to use the MiniGame API
 * Your MiniGame has to have the attributes:
 * Player challenger
 * Player challenged
 * public static final String NAME
 *
 * @author Jan Reitz, Tim Lisemer
 */
public abstract class MiniGame implements InvitationApiUser {

    /**
     * This name will be used for chat messages regarding invitations, ...
     * Is also used to find out if a player is in a minigame of your type
     * give your MiniGame an Attribute public static final NAME = "whatever name you like" and return it
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
     * Used to set the challenged attribute of your game
     *
     * @param challenged the challanged
     */
    public abstract void setChallenged(Player challenged);

    /**
     * Your method that will be called when and if the invitation is accepted
     * challenger and challenged are automatically informed, that the challenged accepted and that the game starts
     */
    public abstract void onGameStart();

    //abstrahiere InvitationAPI
    public void onAcceptedInvitation() {
        MGPlayerManager.addMGPlayer(this.getChallenger(), this);
        MGPlayerManager.addMGPlayer(this.getChallenged(), this);
        this.onGameStart();
    }


}
