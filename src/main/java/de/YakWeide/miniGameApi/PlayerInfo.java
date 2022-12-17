package de.YakWeide.miniGameApi;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

/**
 * You can call methods of this class to get information about a Player
 * e.g.: Are they in a minigame?
 */
public class PlayerInfo {
    /**
     * nameOfGame not Null: Get the current MiniGame of a player if he is in a MiniGame of a specific type
     * nameOfGame null : Get the current Minigame of a player
     * @param player The Player you want the currentMiniGame of
     * @param nameOfGame   Specifies the type of minigame the player should be in, can be null if you generally want the MiniGame the player is in
     * @return Optional with the minigame if he is in one /if he is in one with the right type, else empty Optional
     */
    public static Optional<MiniGame> getMiniGameOf(@NonNull Player player, String nameOfGame) {
        Optional<MiniGame> miniGame = Optional.empty();

        //get the minigame of player if there is one
        if(MGPlayerManager.getMGPlayer(player).isPresent() && MGPlayerManager.getMGPlayer(player).get().getCurrentGame().isPresent()){
            miniGame = MGPlayerManager.getMGPlayer(player).get().getCurrentGame();
        }

        // If the type of game does not matter return miniGame now
        if(nameOfGame == null){
            return miniGame;
        }
        // If the type does matter check if the type is correct
        if(miniGame.isPresent() && (miniGame.get().getName().equals(nameOfGame))){
            return miniGame;
        }
        return Optional.empty();

    }

}
