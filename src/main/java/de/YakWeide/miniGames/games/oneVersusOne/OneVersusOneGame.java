package de.YakWeide.miniGames.games.oneVersusOne;

import de.YakWeide.miniGames.miniGameApi.MiniGame;
import org.bukkit.entity.Player;

public class OneVersusOneGame extends MiniGame {
    static final String NAME = "1 gegen 1";
    private final Player challenger;
    private Player challenged;

    public OneVersusOneGame(Player challenger) {
        this.challenger = challenger;
    }

    @Override
    public String getName() {
        return NAME;
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
    public void setChallenged(Player challenged) {
        this.challenged = challenged;
    }

    @Override
    public void onGameStart() {
    }
}
