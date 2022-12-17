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
     * Find out if a Player is in a minigame right now
     *
     * @param player player whoms status you want to know
     * @return true if player is in minigame, false otherwise
     */
    public static boolean isInMiniGame(@NonNull Player player) {
        Optional<MGPlayer> mgPlayer = MGPlayerManager.getMGPlayer(player);
        if (mgPlayer.isPresent()) {
            return mgPlayer.get().getCurrentGame().isPresent();
        }
        return false;

    }

    /**
     * Find out if a player is in a particular minigame
     *
     * @param player player whose status you want to know
     * @param game   The game you want to know if player is in it
     * @return true if player is in MiniGame game, else otherwise
     */
    public static boolean isInMiniGame(@NonNull Player player, @NonNull MiniGame game) {
        if (isInMiniGame(player)) {
            Optional<MGPlayer> mgPlayer = MGPlayerManager.getMGPlayer(player);
            return mgPlayer.get().getCurrentGame().get() == game;
        }
        return false;
    }


    /**
     * Find out if a player is in a particular type of minigame (identified by the constant name of the minigame)
     * Will fail if there is a second MiniGame with the same Name or getName is not implemented as intended.
     *
     * @param player player whose status you want to know
     * @param name   The game type you want to know if player is in a game of that sort
     * @return true if player is in MiniGameType identified by name.
     */
    public static boolean isInMiniGame(@NonNull Player player, @NonNull String name) {
        Optional<MGPlayer> mgPlayer = MGPlayerManager.getMGPlayer(player);
        return (mgPlayer.isPresent() && mgPlayer.get().getCurrentGame().isPresent() && mgPlayer.get().getCurrentGame().get().getName().equalsIgnoreCase(name));
    }

    /**
     * Get the current MiniGame of a player if there is one
     *
     * @param player The Player you want the currentMiniGame of
     * @return Optional with the minigame if there is one, else empty Optional
     */
    public static Optional<MiniGame> getMiniGameOf(@NonNull Player player) {
        if (MGPlayerManager.getMGPlayer(player).isPresent()) {
            return MGPlayerManager.getMGPlayer(player).get().getCurrentGame();
        }
        return Optional.empty();
    }

    /**
     * Get the current MiniGame of a player if he is in a MiniGame of a specific type
     *
     * @param player The Player you want the currentMiniGame of
     * @param name   Specifies the type of minigame the player should be in
     * @return Optional with the minigame if he is in one with the right type, else empty Optional
     */
    public static Optional<MiniGame> getMiniGameOf(@NonNull Player player, @NonNull String name) {
        if (isInMiniGame(player, name)) {
            return getMiniGameOf(player);
        }
        return Optional.empty();
    }

}
