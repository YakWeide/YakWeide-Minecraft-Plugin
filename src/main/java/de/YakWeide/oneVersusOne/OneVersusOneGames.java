package de.YakWeide.oneVersusOne;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class OneVersusOneGames {
    private Player challenger;      //challenger
    private Player challenged;      //challenged

    private static final ArrayList<Player> activeFighters = new ArrayList<>();
    private final ChatApi chatApi = ChatApi.getInstance();


    public OneVersusOneGames(Player challenger, Player challenged) {
        this.challenger = challenger;
        this.challenged = challenged;
    }

    public boolean begin(){

        return false;
    }



}
