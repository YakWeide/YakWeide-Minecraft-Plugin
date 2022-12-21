package de.YakWeide.YakPlayerUtility;

import de.YakWeide.invitations.Invitation;
import de.YakWeide.statistics.Statistics;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Player with more functionality than the standard Bukkit Player
 */
public class YakPlayer {

    private final HashMap<Class<? extends YakProperty>, ArrayList<YakProperty>> propertyMap;
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
        this.propertyMap = new HashMap<>();
    }


    /**
     * Add a property to a YakPlayer, make sure the propertyLimit is set
     *
     * @param property The Property you want to add
     * @throws IllegalStateException If the propertyLimit for type property.getClass() is exceeded or if the propertyLimit isnt set
     */
    public void addProperty(@NonNull YakProperty property) throws IllegalStateException {
        int propertyLimit = YakProperty.getPropertyLimit(property.getClass());

        ArrayList<YakProperty> propertyListOfTypeProperty = propertyMap.get(property.getClass());

        //Check if the YakPlayer already has a property of type property.getClass()
        if (propertyListOfTypeProperty == null) {
            propertyListOfTypeProperty = new ArrayList<>();
            propertyMap.put(property.getClass(), propertyListOfTypeProperty);
        } else {
            //Check if the PropertyLimit is exceeded
            if (propertyListOfTypeProperty.size() >= propertyLimit) {
                throw new IllegalStateException("The Propertylist has reached its maximum capacity");
            }

            propertyListOfTypeProperty.add(property);
        }
    }

    /**
     * Remove a property from a single Player, does nothing if the player does not have this property
     *
     * @param property The property you want to remove
     */
    public void removeProperty(@NonNull YakProperty property) {
        ArrayList<YakProperty> propertyListOfTypeProperty = propertyMap.get(property.getClass());
        if (propertyListOfTypeProperty == null) {
            return;
        }
        propertyListOfTypeProperty.remove(property);
    }

    /**
     * Get all properties of a specific type of a player
     *
     * @param propertyType The propertyType you want the properties of
     * @return An ArrayList with all properties of this type, null if the YakPlayer does not have one
     */
    @Nullable
    public ArrayList<YakProperty> getPropertyListOfType(Class<? extends YakProperty> propertyType) {
        return propertyMap.get(propertyType);
    }

    /**
     * Get the Property of a specific type of a player, can only be called if the propertyLimit of this propertyType is set to 1.
     *
     * @param propertyType The type you want the property of
     * @return The Property of this type, null if the YakPlayer does not have one
     * @throws IllegalArgumentException If you call this method with a propertyType with propertyLimit != 1
     */
    @Nullable
    public YakProperty getPropertyOfType(Class<? extends YakProperty> propertyType) throws IllegalArgumentException {
        if (YakProperty.getPropertyLimit(propertyType) != 1) {
            throw new IllegalArgumentException("You can only call this method with propertiesTypes where the propertyLimit is one");
        }
        return propertyMap.get(propertyType).get(0);
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
