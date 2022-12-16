package de.YakWeide.miniGameApi;

import org.bukkit.entity.Player;

import java.util.Optional;

public class MGPlayer {
    private final Player root;
    private MiniGame game;

    public MGPlayer(Player root) {
        this.root = root;
    }

    public Player getRoot() {
        return root;
    }

    public Optional<MiniGame> getGame() {
        return Optional.ofNullable(game);
    }

    public void setGame(MiniGame game) {
        this.game = game;
    }


}
