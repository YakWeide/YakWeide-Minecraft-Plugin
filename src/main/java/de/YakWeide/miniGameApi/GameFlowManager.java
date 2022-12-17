package de.YakWeide.miniGameApi;

import de.YakWeide.chatApi.ChatApi;
import de.YakWeide.invitations.InvitationManager;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

/**
 * Use this class to control the program flow of a minigame
 * e.g: start a minigame
 */
public class GameFlowManager {
    private static final ChatApi chatApi = ChatApi.getInstance();

    /**
     * Call this method if you want to start a miniGame Object,make sure name and challenger are not null.
     * How it is thought to be used:
     * Your game is started by a command of the challenger
     * The command should look like this /[YourCommand] args, once you receive this command, call this method
     * it will check if args is the name of a player on your server, if it is, the game will start, else the challenger will be notified.
     *
     *
     * @param game the minigame you want to start
     * @param args the String[] you get from your command
     * @throws NullPointerException
     */
    public static boolean startGame(@NonNull MiniGame game, @NonNull String[] args) throws NullPointerException {
        if (!(game.getName() != null && game.getChallenger() != null)) {
            throw new NullPointerException("name, challenger or challenged of your game Object == null");
        }

        //Check if challenger already is in a minigame
        if (PlayerInfo.isInMiniGame(game.getChallenger())) {
            chatApi.sendMessage(game.getChallenger(), ChatApi.badColor + "Du bist bereits in einem Minispiel!");
            return false;
        }

        //Versuche challenged zu finden und setze challenged attribut von game auf ihn
        if(args.length != 1){
            chatApi.sendMessage(game.getChallenger(), ChatApi.badColor + "Du musst genau einen Namen als Argument angeben!");
            return false;
        }
        Player challenged = null;
        for(Player p : Bukkit.getOnlinePlayers()){
            if(p.getName().equalsIgnoreCase(args[0])){
                challenged = p;
                break;
            }
        }
        game.setChallenged(challenged);

        //Check if challenged already is in a minigame
        if (PlayerInfo.isInMiniGame(game.getChallenged())) {
            chatApi.sendMessage(game.getChallenger(), ChatApi.badColor + game.getChallenged().getName() + " ist bereits in einem Minispiel!");
            return false;
        }
        // Prüft auch ob challenged == null und fängt das ab
        InvitationManager.invite(game.getChallenger(), game.getChallenged(), game);
        return true;
    }

    /**
     * If the game is finished, and you want to announce the winner call this method
     * Will automatically cancel the minigame
     *
     * @param winner     The winner of the minigame
     * @param loser      The loser of the minigame
     * @param game       The finished game
     * @param announceTo "private": Only the participants will be informed, "public": The whole Server will be informed
     */
    public static void announceWinner(@NonNull Player winner, @NonNull Player loser, @NonNull MiniGame game, @NonNull String announceTo) {
        if (announceTo.equalsIgnoreCase("private")) {
            chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
            chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        } else if (announceTo.equalsIgnoreCase("public")) {
            chatApi.BroadcastMessage(ChatApi.prefixColor + winner.getName() + " hat " + game.getName() + " gegen " + loser.getName() + " gewonnen!");

        } else {
            chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
            chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        }
        MGPlayerManager.removeMGPlayer(winner);
        MGPlayerManager.removeMGPlayer(loser);
        MGPlayerManager.removeMGPlayer(game.getChallenger());
        MGPlayerManager.removeMGPlayer(game.getChallenged());
    }

    public static void announceWinner(@NonNull Player winner, @NonNull Player loser, @NonNull MiniGame game) {
        chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
        chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        MGPlayerManager.removeMGPlayer(winner);
        MGPlayerManager.removeMGPlayer(loser);
        MGPlayerManager.removeMGPlayer(game.getChallenger());
        MGPlayerManager.removeMGPlayer(game.getChallenged());
    }

    public static void announceWinner(@NonNull Player winner, @NonNull MiniGame game) {
        Player loser;
        if (winner == game.getChallenger()) {
            loser = game.getChallenged();
        } else {
            loser = game.getChallenger();
        }
        chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
        chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        MGPlayerManager.removeMGPlayer(winner);
        MGPlayerManager.removeMGPlayer(loser);
        MGPlayerManager.removeMGPlayer(game.getChallenger());
        MGPlayerManager.removeMGPlayer(game.getChallenged());
    }

    public static void announceWinner(@NonNull Player winner, @NonNull MiniGame game, @NonNull String announceTo) {
        Player loser;
        if (winner == game.getChallenger()) {
            loser = game.getChallenged();
        } else {
            loser = game.getChallenger();
        }


        if (announceTo.equalsIgnoreCase("private")) {
            chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
            chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        } else if (announceTo.equalsIgnoreCase("public")) {
            chatApi.BroadcastMessage(ChatApi.prefixColor + winner.getName() + " hat " + game.getName() + " gegen " + loser.getName() + " gewonnen!");

        } else {
            chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
            chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        }
        MGPlayerManager.removeMGPlayer(winner);
        MGPlayerManager.removeMGPlayer(loser);
        MGPlayerManager.removeMGPlayer(game.getChallenger());
        MGPlayerManager.removeMGPlayer(game.getChallenged());
    }

    public static void announceWinner(@NonNull MiniGame game, @NonNull Player loser){
        Player winner;
        if(loser == game.getChallenger()){
            winner = game.getChallenged();
        }
        else{
            winner = game.getChallenger();
        }
        chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
        chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        MGPlayerManager.removeMGPlayer(winner);
        MGPlayerManager.removeMGPlayer(loser);
        MGPlayerManager.removeMGPlayer(game.getChallenger());
        MGPlayerManager.removeMGPlayer(game.getChallenged());
    }

    public static void announceWinner(@NonNull MiniGame game, @NonNull Player loser, String announceTo){
        Player winner;
        if(loser == game.getChallenger()){
            winner = game.getChallenged();
        }
        else{
            winner = game.getChallenger();
        }
        if (announceTo.equalsIgnoreCase("private")) {
            chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
            chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        } else if (announceTo.equalsIgnoreCase("public")) {
            chatApi.BroadcastMessage(ChatApi.prefixColor + winner.getName() + " hat " + game.getName() + " gegen " + loser.getName() + " gewonnen!");

        } else {
            chatApi.sendMessage(winner, ChatApi.goodColor + "Du hast " + game.getName() + " gegen " + loser.getName() + " gewonnen!");
            chatApi.sendMessage(loser, ChatApi.badColor + "Du hast " + game.getName() + " gegen " + winner.getName() + " verloren!");
        }
    }


    /**
     * Call this method if you want to cancel your minigame for some reason
     *
     * @param game The minigame you want to cancel
     */
    public static void cancelGame(@NonNull MiniGame game) {
        Player challenger = game.getChallenger();
        Player challenged = game.getChallenged();
        MGPlayerManager.removeMGPlayer(challenger);
        MGPlayerManager.removeMGPlayer(challenged);
        chatApi.sendMessage(challenger, ChatApi.badColor + game.getName() + " gegen " + challenged.getName() + " wurde beendet ");
        chatApi.sendMessage(challenged, ChatApi.badColor + game.getName() + " gegen " + challenger.getName() + " wurde beendet ");
    }

}
