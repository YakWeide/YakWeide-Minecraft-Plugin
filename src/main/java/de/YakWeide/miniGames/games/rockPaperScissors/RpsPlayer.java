package de.YakWeide.miniGames.games.rockPaperScissors;

import java.util.UUID;

public abstract class RpsPlayer {
    // id of minecraft player, null if RpsPlayer is computer
    public UUID id;
    //name of minecraft player, "computer" if RpsPlayer is computer
    public String name;
    protected RpsOptions hand;

    public String getName() {
        return name;
    }

    public UUID getId() {
        return id;
    }
    // getter

    public RpsOptions getHand() {
        return this.hand;
    }

    // setter, overwrite in RpsHuman and RpsComputer
    public abstract boolean setHand();

}
