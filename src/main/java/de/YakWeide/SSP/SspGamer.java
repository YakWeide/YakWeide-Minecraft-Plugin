package de.YakWeide.SSP;

public abstract class SspGamer {
    private SspOptions hand;
    private boolean gewonnen;

    public boolean isGewonnen() {
        return gewonnen;
    }

    public void setGewonnen(boolean gewonnen) {
        this.gewonnen = gewonnen;
    }

    public SspOptions getHand() {
        return hand;
    }

    public abstract void setHand();

    public void setHand(SspOptions hand){
        this.hand = hand;
    }
}
