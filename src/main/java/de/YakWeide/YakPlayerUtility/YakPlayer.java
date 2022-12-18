package de.YakWeide.YakPlayerUtility;

import de.YakWeide.statistics.Statistics;
import de.YakWeide.invitations.Invitation;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.checkerframework.checker.nullness.qual.Nullable;

public class YakPlayer {
    private final Player player;

    private boolean pussymodus;
    private ChatColor displayColor;
    private String customJoinMessage;
    private String customQuitMessage;
    private Invitation invitation;
    private Location home;
    private Statistics statistics;

    public YakPlayer(Player player) {
        this.player = player;
        this.pussymodus = false;
        this.displayColor = null;
        this.customJoinMessage = null;
        this.customQuitMessage = null;
        this.invitation = null;
        this.home = null;
        this.statistics = new Statistics(this);
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

    @Nullable
    public ChatColor getDisplayColor() {
        return displayColor;
    }

    public void setDisplayColor(ChatColor displayColor) {
        this.displayColor = displayColor;
    }

    @Nullable
    public String getCustomJoinMessage() {
        return customJoinMessage;
    }

    public void setCustomJoinMessage(String customJoinMessage) {
        this.customJoinMessage = customJoinMessage;
    }

    @Nullable
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

    @Nullable
    public Location getHome() {
        return home;
    }

    public void setHome(Location home) {
        this.home = home;
    }


    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }
}
