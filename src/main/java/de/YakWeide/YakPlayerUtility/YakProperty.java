package de.YakWeide.YakPlayerUtility;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;

/**
 * You can add your own YakProperties to a YakPlayer!
 */
public abstract class YakProperty {

    //Für jeden Typ speichern wie viele Properties dieses Typs ein Spieler maximal haben kann
    private static final HashMap<Class<? extends YakProperty>, Integer> propertyLimits = new HashMap<>();
    //TODO: lifetime für properties private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    //TODO: lifetime für properties private ScheduledFuture<?> deleteAttributeFuture;

    public YakProperty() {
    }

    /**
     * get the maximum amount of properties of one particular type a YakPlayer can have
     *
     * @param propertyType The type you want to get the limit for
     * @return The limit
     * @throws IllegalStateException If propertyLimit is not set for this type
     */
    public static int getPropertyLimit(@NonNull Class<? extends YakProperty> propertyType) throws IllegalStateException {
        if (propertyLimits.get(propertyType) == null) {
            throw new IllegalStateException("PropertyLimit has to be set before you get it");
        }
        return propertyLimits.get(propertyType);
    }

    /**
     * Set the maximum amount of properties of one particular type a Yakplayer can have
     *
     * @param propertyType The type you want to set the limit for
     * @param limit        The limit
     * @throws IllegalArgumentException If limit is <= 0
     */
    public static void setPropertyLimit(@NonNull Class<? extends YakProperty> propertyType, int limit) throws IllegalArgumentException {
        if (limit <= 0) {
            throw new IllegalArgumentException("Limit has to be greater than 0 ");
        }
        propertyLimits.put(propertyType, limit);

    }
}
