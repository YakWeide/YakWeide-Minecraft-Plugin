package de.YakWeide;

import de.YakWeide.Statistics.Statistics;
import de.YakWeide.invitations.Invitation;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.ArrayList;

public class YakPlayer {
    private final Player player;

    private boolean pussymodus;
    private ChatColor displayColor;
    private String customJoinMessage;
    private String customQuitMessage;
    private Invitation invitation;
    private Location home;
    private Inventory inventory;
    private Statistics statistics;

    public YakPlayer(Player player) {
        this.player = player;
        this.pussymodus = false;
        this.displayColor = null;
        this.customJoinMessage = null;
        this.customQuitMessage = null;
        this.invitation = null;
        this.home = null;
        this.inventory = null;
        this.statistics = new Statistics(this);
    }

    public static ArrayList<YakPlayer> InitializeYakPlayerAtStartup() {
        ArrayList<YakPlayer> yakPlayerList = new ArrayList<YakPlayer>();
        for (Player p : Bukkit.getOnlinePlayers()) {
            yakPlayerList.add(new YakPlayer(p));
        }
        return yakPlayerList;
    }

    public static boolean isInYakPlayerList(Player player) {
        for (YakPlayer yakPlayer : YakWeideMinecraftPlugin.yakPlayerList) {
            if (yakPlayer.getRoot().equals(player)) return true;
        }
        return false;
    }

    public static YakPlayer getYakPlayer(Player player) {
        for (YakPlayer yakPlayer : YakWeideMinecraftPlugin.yakPlayerList) {
            if (yakPlayer.getRoot().equals(player)) return yakPlayer;
        }
        return null;
    }

    public Player getRoot() {
        return player;
    }

    public boolean isInPussymodus() {
        return pussymodus;
    }

    public void setPussymodus(boolean pussymodus) {
        this.pussymodus = pussymodus;
    }

    public ChatColor getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(ChatColor displayColor) {
        this.displayColor = displayColor;
    }

    public String getCustomJoinMessage() {
        return customJoinMessage;
    }

    public void setCustomJoinMessage(String customJoinMessage) {
        this.customJoinMessage = customJoinMessage;
    }

    public String getCustomQuitMessage() {
        return customQuitMessage;
    }

    public void setCustomQuitMessage(String customQuitMessage) {
        this.customQuitMessage = customQuitMessage;
    }

    public Invitation getInvitation() {
        return invitation;
    }

    public void setInvitation(Invitation invitation) {
        this.invitation = invitation;
    }

    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
