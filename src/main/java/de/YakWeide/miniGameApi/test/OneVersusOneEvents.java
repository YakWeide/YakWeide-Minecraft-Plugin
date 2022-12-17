package de.YakWeide.miniGameApi.test;

import de.YakWeide.miniGameApi.GameFlowManager;
import de.YakWeide.miniGameApi.MiniGame;
import de.YakWeide.miniGameApi.PlayerInfo;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

import java.util.Optional;

public class OneVersusOneEvents implements Listener {
    public void onPlayerDeath(PlayerDeathEvent event){
        Player player = event.getEntity();
        Optional<MiniGame> game = PlayerInfo.getMiniGameOf(player, OneVersusOneGame.NAME);
        if(!game.isPresent()){
            return;
        }
        OneVersusOneGame oneVersusOneGame = (OneVersusOneGame) game.get(); //TODO: Muss man diesen cast machen, zur Laufzeit ist das ja sowieso ein OneVersusOneGame?
        GameFlowManager.announceWinner(player, oneVersusOneGame);

    }

}
