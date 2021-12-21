package de.YakWeide.RockPaperScissors;

public abstract class RpsPlayer {
    protected RpsOptions hand;
    // getter

    public RpsOptions getHand(){
        return this.hand;
    }

    // setter, overwrite in RpsHuman and RpsComputer
    public abstract void setHand();

}
