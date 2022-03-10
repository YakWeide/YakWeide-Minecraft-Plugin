package de.YakWeide.RockPaperScissors;

import java.util.UUID;

public abstract class RpsPlayer {
    // id of minecraft player, null if RpsPlayer is computer
    UUID id;
    //name of minecraft player, "computer" if RpsPlayer is computer
    String name;

    public String getName() {
        return name;
    }


    public UUID getId() {
        return id;
    }


    protected RpsOptions hand;
    // getter

    public RpsOptions getHand(){
        return this.hand;
    }

    // setter, overwrite in RpsHuman and RpsComputer
    public abstract boolean setHand();

}
