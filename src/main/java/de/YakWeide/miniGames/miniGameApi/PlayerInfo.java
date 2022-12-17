package de.YakWeide.miniGames.miniGameApi;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

/**
 * You can call methods of this class to get information about a Player
 * e.g.: Are they in a minigame?
 *
 * @author Jan Reitz, Tim Lisemer
 */
public class PlayerInfo {
    /**
     * nameOfGame not Null: Get the current MiniGame of a player if he is in a MiniGame of a specific type
     * nameOfGame null : Get the current Minigame of a player
     *
     * @param player     The Player you want the currentMiniGame of
     * @param typeOfGame Specifies the type of minigame the player should be in, can be null if you generally want the MiniGame the player is in
     * @return Optional with the minigame if he is in one /if he is in one with the right type, else empty Optional
     */
    public static <T extends MiniGame> Optional<MiniGame> getMiniGameOf(@NonNull Player player,  Class<T> typeOfGame) {
        Optional<MiniGame> miniGame = Optional.empty();

        //get the mini-game of player if there is one
        if (MGPlayerManager.getMGPlayer(player).isPresent() && MGPlayerManager.getMGPlayer(player).get().getCurrentGame().isPresent()) {
            miniGame = MGPlayerManager.getMGPlayer(player).get().getCurrentGame();
        }

        // If the type of game does not matter return miniGame now
        if (typeOfGame == null) {
            return miniGame;
        }
        // If the type does matter check if the type is correct
        if (miniGame.isPresent() && (miniGame.get().getClass().equals(typeOfGame))) {
            return miniGame;
        }
        return Optional.empty();

    }

}
