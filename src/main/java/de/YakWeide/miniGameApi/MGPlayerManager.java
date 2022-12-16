package de.YakWeide.miniGameApi;

import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

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
    private final HashMap<Player, MGPlayer> playerMap = new HashMap<Player, MGPlayer>();
    private static MGPlayerManager instance;

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
    public static Optional<MGPlayer> getMGPlayer(@NotNull Player player) {
        return getInstance()._getMGPlayer(player);
    }

    public Optional<MGPlayer> _getMGPlayer(@NotNull Player player) {
        MGPlayer mgPlayer = playerMap.get(player);
        return Optional.ofNullable(mgPlayer);
    }


    /**
     * Neuen MGPlayer anlegen
     *
     * @param player Player für den MGPlayer angelegt werden soll
     */
    public static void addMGPlayer(@NotNull Player player) {
        getInstance()._addMGPlayer(player);
    }

    public void _addMGPlayer(@NotNull Player player) {
        MGPlayer mgPlayer = new MGPlayer(player);
        playerMap.put(player, mgPlayer);
    }

    /**
     * Verwendung, wenn MGPlayer nicht mehr benötigt wird, beispielsweise wenn Spieler sich ausloggt
     *
     * @param player Spieler dessen MGPlayer gelöscht werden soll
     */
    public static void removeMGPlayer(@NotNull Player player) {
        getInstance()._removeMGPlayer(player);
    }

    public void _removeMGPlayer(@NotNull Player player) {
        playerMap.remove(player);
    }


}
