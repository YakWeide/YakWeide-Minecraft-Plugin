package de.YakWeide.YakPlayerUtility;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.HashMap;

public class VerwaltungsYak {

    public static HashMap<Player, YakPlayer> yakPlayerMap = new HashMap<>();
    private static VerwaltungsYak instance;

    private VerwaltungsYak(){
    }
    public static VerwaltungsYak getInstance(){
        if(instance == null){
            VerwaltungsYak yak = new VerwaltungsYak();
            instance = yak;
        }
        return instance;
    }

    public static void InitializeYakPlayerAtStartup() throws Exception {
        getInstance()._InitializeYakPlayerAtStartup();
    }

    public static boolean isInYakPlayerList(Player player){
        return getInstance()._isInYakPlayerList(player);
    }

    @Nullable
    public static YakPlayer getYakPlayer(Player player) {
        return getInstance()._getYakPlayer(player);
    }

    public static void addYakPlayer(Player player){
        getInstance()._addYakPlayer(player);

    }

    public static void removeYakPlayer(Player player){
        getInstance()._removeYakPlayer(player);
    }

    public void _removeYakPlayer(Player player) {
        yakPlayerMap.remove(player);
    }


    public void _addYakPlayer(Player player){
        if(yakPlayerMap.get(player) == null){
            YakPlayer yakPlayer = new YakPlayer(player);
            yakPlayerMap.put(player, yakPlayer);
        }

    }


    public void _InitializeYakPlayerAtStartup() throws Exception {
        if(yakPlayerMap.isEmpty()){
            throw new Exception("You can only call InitializeYakPlayerAtStartUp on StartUp.");
        }
        for (Player p : Bukkit.getOnlinePlayers()) {
            YakPlayer yakPlayer = new YakPlayer(p);
            yakPlayerMap.put(p, yakPlayer);
        }
    }

    public boolean _isInYakPlayerList(Player player) {
        if(yakPlayerMap.get(player) != null){
            return true;
        }
        return false;
    }

    @Nullable
    public YakPlayer _getYakPlayer(Player player) {
        return yakPlayerMap.get(player);
    }
}
