package de.YakWeide.miniGameApi;

import org.bukkit.entity.Player;

import java.util.Optional;

public class MGPlayer {
    private final Player root;
    private MiniGame currentGame;

    public MGPlayer(Player root, MiniGame game) {
        this.root = root;
        currentGame = game;
    }

    public Player getRoot() {
        return root;
    }

    public Optional<MiniGame> getCurrentGame() {
        return Optional.ofNullable(currentGame);
    }

    public void setCurrentGame(MiniGame currentGame) {
        this.currentGame = currentGame;
    }


}
