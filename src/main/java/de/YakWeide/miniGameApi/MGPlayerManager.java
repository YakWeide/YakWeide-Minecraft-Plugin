package de.YakWeide.miniGameApi;

import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;


import java.util.HashMap;
import java.util.Optional;

/**
 * Verwaltungseinheit von Paaren (Player, MGPlayer)
 * Nötig da durch Angabe von Player auf Eigenschaften von MGPlayer zugegriffen werden muss
 * Singleton Pattern
 *
 * @author Jan Reitz
 */
public class MGPlayerManager {
    private static MGPlayerManager instance;
    private final HashMap<Player, MGPlayer> playerMap = new HashMap<>();

    private MGPlayerManager() {

    }

    public static MGPlayerManager getInstance() {
        if (instance == null) {
            instance = new MGPlayerManager();
        }
        return instance;
    }

    /**
     * Zu Player MGPlayer anfordern
     *
     * @param player Player für den MGPlayer angefordert werden soll
     * @return MGPlayer, wenn vorhanden. Sonst leeres Optional.
     */
    public static Optional<MGPlayer> getMGPlayer(@NonNull Player player) {
        return getInstance()._getMGPlayer(player);
    }

    /**
     * Neuen MGPlayer anlegen
     *
     * @param player Player für den MGPlayer angelegt werden soll
     */
    public static void addMGPlayer(@NonNull Player player, MiniGame currentGame) {
        getInstance()._addMGPlayer(player, currentGame);
    }

    /**
     * Verwendung, wenn MGPlayer nicht mehr benötigt wird, beispielsweise wenn Spieler sich ausloggt
     *
     * @param player Spieler dessen MGPlayer gelöscht werden soll
     */
    public static void removeMGPlayer(@NonNull Player player) {
        getInstance()._removeMGPlayer(player);
    }

    public Optional<MGPlayer> _getMGPlayer(@NonNull Player player) {
        MGPlayer mgPlayer = playerMap.get(player);
        return Optional.ofNullable(mgPlayer);
    }

    public void _addMGPlayer(@NonNull Player player, MiniGame currentGame) {
        MGPlayer mgPlayer = new MGPlayer(player, currentGame);
        playerMap.put(player, mgPlayer);
    }

    public void _removeMGPlayer(@NonNull Player player) {
        playerMap.remove(player);
    }


}
