package de.YakWeide.miniGames.miniGameApi;

import de.YakWeide.chatApi.ChatApi;
import de.YakWeide.invitations.InvitationApiUser;
import de.YakWeide.invitations.InvitationManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitScheduler;
import org.checkerframework.checker.nullness.qual.NonNull;

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

    private boolean resetGameTimer = false;
    private int gameTimerSchedulerID;

    public MiniGame(int gameTimeLength) {
        //gameTimer(gameTimeLength);
    }

    /**
     * Call this method if you want to start a miniGame Object,make sure name and challenger are not null.
     * How it is thought to be used:
     * Your game is started by a command of the challenger
     * The command should look like this /[YourCommand] args, once you receive this command, call this method
     * it will check if args is the name of a player on your server, if it is, the game will start, else the challenger will be notified.
     *
     * @param args the String[] you get from your command
     */
    public boolean startGame(@NonNull String[] args) throws NullPointerException {
        if (!(getName() != null && getChallenger() != null)) {
            throw new NullPointerException("name, challenger or challenged of your game Object == null");
        }

        //Check if challenger already is in a minigame
        if (PlayerInfo.getMiniGameOf(getChallenger(), null).isPresent()) {
            ChatApi.sendMessage(getChallenger(), ChatApi.badColor + "Du bist bereits in einem Minispiel!");
            return false;
        }

        //Versuche challenged zu finden und setze challenged attribut von game auf ihn
        if (args.length != 1) {
            ChatApi.sendMessage(getChallenger(), ChatApi.badColor + "Du musst genau einen Namen als Argument angeben!");
            return false;
        }
        Player challenged = null;
        for (Player p : Bukkit.getOnlinePlayers()) {
            if (p.getName().equalsIgnoreCase(args[0])) {
                challenged = p;
                break;
            }
        }
        setChallenged(challenged);

        //Check if challenged already is in a minigame
        if (PlayerInfo.getMiniGameOf(getChallenged(), null).isPresent()) {
            ChatApi.sendMessage(getChallenger(), ChatApi.badColor + getChallenged().getName() + " ist bereits in einem Minispiel!");
            return false;
        }
        // Prüft auch ob challenged == null und fängt das ab
        InvitationManager.invite(getChallenger(), getChallenged(), this);
        return true;
    }

    /**
     * If the game is finished, and you want to announce the winner call this method
     * Will automatically cancel the minigame
     * If you don't use this method please you cancelGame at the end of your minigame
     *
     * @param winner             The winner of the minigame, either winner or loser can be null
     * @param loser              The loser of the minigame, either winner or loser can be null but one has to exist
     * @param announceToEveryone false: Only participants will be informed, false: The whole server will be informed
     */
    public void announceWinner(Player winner, Player loser, boolean announceToEveryone) throws NullPointerException {
        if (winner == null && loser == null) {
            throw new NullPointerException("Either winner or loser hast to be not null");
        }
        if (winner == null) {
            winner = calculateWinner(loser);
        }
        if (loser == null) {
            loser = calculateLoser(winner);
        }
        if (announceToEveryone) {
            ChatApi.broadcastMessage(ChatApi.prefixColor + ChatApi.playerName(winner) + " hat " + ChatColor.GOLD + getName()  + ChatApi.prefixColor + " gegen " + ChatApi.playerName(loser) + " gewonnen!");
        } else {
            ChatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + ChatColor.GOLD + getName() + ChatApi.prefixColor + " gegen " + ChatApi.playerName(loser) + " gewonnen!");
            ChatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + ChatColor.GOLD + getName()  + ChatApi.prefixColor + " gegen " + ChatApi.playerName(winner) + " verloren!");
        }
        MGPlayerManager.removeMGPlayer(winner);
        MGPlayerManager.removeMGPlayer(loser);
        MGPlayerManager.removeMGPlayer(getChallenger());
        MGPlayerManager.removeMGPlayer(getChallenged());
    }

    /**
     * Calculates the winner of a game if you already know the loser
     *
     * @param loser The loser
     * @return The winner
     */
    public Player calculateWinner(Player loser) {
        Player winner;
        if (loser == getChallenger()) {
            winner = getChallenged();
        } else {
            winner = getChallenger();
        }
        return winner;
    }

    /**
     * Calculates the loser of a game if you already know the winner
     *
     * @param winner The winner
     * @return The loser
     */
    public Player calculateLoser(Player winner) {
        Player loser;
        if (winner == getChallenger()) {
            loser = getChallenged();
        } else {
            loser = getChallenger();
        }
        return loser;
    }


    /**
     * Call this method if you want to cancel your minigame for some reason
     *
     */
    public void cancelGame() {
        Player challenger = getChallenger();
        Player challenged = getChallenged();
        MGPlayerManager.removeMGPlayer(challenger);
        MGPlayerManager.removeMGPlayer(challenged);
        ChatApi.sendMessage(challenger, ChatApi.badColor + getName() + " gegen " + challenged.getName() + " wurde beendet ");
        ChatApi.sendMessage(challenged, ChatApi.badColor + getName() + " gegen " + challenger.getName() + " wurde beendet ");
    }

    /**
     * Resets the Game Timer by setting the resetGameTimer boolean to true
     */
    public void resetGameTimer() {
        resetGameTimer = true;
    }

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

    public String toString(){
        return this.getName();
    }

    //abstrahiere InvitationAPI
    public void onAcceptedInvitation() {
        MGPlayerManager.addMGPlayer(this.getChallenger(), this);
        MGPlayerManager.addMGPlayer(this.getChallenged(), this);
        this.onGameStart();
    }

    public void gameTimer(int timeInSeconds){
        gameTimerSchedulerID = Bukkit.getScheduler().scheduleAsyncRepeatingTask(Bukkit.getPluginManager().getPlugin("TNTCannon"), new Runnable(){
            @Override
            public void run() {
                if(resetGameTimer) {
                    Bukkit.getServer().getScheduler().cancelTask(gameTimerSchedulerID);
                    resetGameTimer = false;
                    gameTimer(timeInSeconds);
                }
            }
        }, 20L*timeInSeconds, 20L);
        cancelGame();
    }
}
