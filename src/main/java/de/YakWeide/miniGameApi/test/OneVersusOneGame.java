package de.YakWeide.miniGameApi.test;

import de.YakWeide.miniGameApi.MiniGame;
import org.bukkit.entity.Player;

public class OneVersusOneGame extends MiniGame {
    static final String NAME = "1 gegen 1";
    private Player challenger;
    private Player challenged;

    public OneVersusOneGame(Player challenger) {
        this.challenger = challenger;
    }

    @Override
    public String getName() {
        return NAME;
    }

    public String toString(){
        return NAME;
    }

    @Override
    public void setChallenged(Player challenged) {
        this.challenged = challenged;
    }

    @Override
    public Player getChallenger() {
        return this.challenger;
    }

    @Override
    public Player getChallenged() {
        return this.challenged;
    }

    @Override
    public void onGameStart() {
        return;
    }
}
