package de.YakWeide.YakPlayerUtility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;

/**
 * Manager class for all YakPlayers. Singleton Pattern.
 */
public class VerwaltungsYak {

    private static final HashMap<Player, YakPlayer> yakPlayerMap = new HashMap<>();
    private static VerwaltungsYak instance;


    /**
     * private constructor so there can only be one instance
     */
    private VerwaltungsYak() {
    }

    /**
     * Get the only instance of VerwaltungsYak, creates one if there isn't one.
     *
     * @return Instance of VerwaltungsYak
     */
    public static VerwaltungsYak getInstance() {
        if (instance == null) {
            instance = new VerwaltungsYak();
        }
        return instance;
    }


    /**
     * Call on plugin start, initializes the VerwaltungsYak
     *
     * @throws Exception If you call this method after plugin was started
     */
    public static void InitializeYakPlayerAtStartup() throws Exception {
        getInstance()._InitializeYakPlayerAtStartup();
    }

    /**
     * removes a property from all online players that have it
     *
     * @param property The property you want to remove
     */
    public static void removePropertyFromAllPlayers(@NonNull YakProperty property) {
        getInstance()._removePropertyFromAllPlayers(property);
    }

    /**
     * Check if a Player has a corresponding YakPlayer
     *
     * @param player The Player
     * @return true if player has a corresponding YakPlayer
     */
    public static boolean isInYakPlayerList(@NonNull Player player) {
        return getInstance()._isInYakPlayerList(player);
    }

    /**
     * Get the corresponding YakPlayer to a Player
     *
     * @param player The player you want the corresponding yakPlayer of
     * @return the YakPlayer, null if the isn't one
     */
    @Nullable
    public static YakPlayer getYakPlayer(@NonNull Player player) {
        return getInstance()._getYakPlayer(player);
    }

    /**
     * Adds a corresponding YakPlayer to player to the map, does nothing if there already is one
     *
     * @param player The player you want to add a corresponding yakPlayer to
     */
    public static void addYakPlayer(@NonNull Player player) {
        getInstance()._addYakPlayer(player);

    }

    /**
     * Removes the corresponding YakPlayer to a Player
     *
     * @param player The player you want to remove the corresponding YakPlayer to
     */
    public static void removeYakPlayer(Player player) {
        getInstance()._removeYakPlayer(player);
    }

    public void _removePropertyFromAllPlayers(@NonNull YakProperty property) {
        for (Player p : yakPlayerMap.keySet()) {
            yakPlayerMap.get(p).removeProperty(property);
        }
    }

    public void _removeYakPlayer(Player player) {
        yakPlayerMap.remove(player);
    }


    public void _addYakPlayer(Player player) {
        if (yakPlayerMap.get(player) == null) {
            YakPlayer yakPlayer = new YakPlayer(player);
            yakPlayerMap.put(player, yakPlayer);
        }

    }


    public void _InitializeYakPlayerAtStartup() throws Exception {
        if (!yakPlayerMap.isEmpty()) {
            throw new Exception("You can only call InitializeYakPlayerAtStartUp on StartUp.");
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            YakPlayer yakPlayer = new YakPlayer(p);
            yakPlayerMap.put(p, yakPlayer);
        }
    }

    public boolean _isInYakPlayerList(Player player) {
        return yakPlayerMap.get(player) != null;
    }

    @Nullable
    public YakPlayer _getYakPlayer(Player player) {
        return yakPlayerMap.get(player);
    }
}
