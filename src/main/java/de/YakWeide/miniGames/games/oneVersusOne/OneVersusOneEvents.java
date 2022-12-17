package de.YakWeide.miniGames.games.oneVersusOne;

import de.YakWeide.chatApi.ChatApi;
import de.YakWeide.miniGames.miniGameApi.GameFlowManager;
import de.YakWeide.miniGames.miniGameApi.MiniGame;
import de.YakWeide.miniGames.miniGameApi.PlayerInfo;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;
import java.util.List;
import java.util.Optional;

public class OneVersusOneEvents implements Listener {
    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        Optional<MiniGame> game = PlayerInfo.getMiniGameOf(player, OneVersusOneGame.class);
        if (!game.isPresent()) {
            return;
        }

        OneVersusOneGame oneVersusOneGame = (OneVersusOneGame) game.get(); //TODO: Muss man diesen cast machen, zur Laufzeit ist das ja sowieso ein OneVersusOneGame?
        event.setDeathMessage("");

        if(player.getKiller() == null){
            GameFlowManager.announceWinner(null, player, oneVersusOneGame, true);
            return;
        }

        event.setKeepLevel(true);
        event.setKeepInventory(true);
        List<ItemStack> dropList = event.getDrops();
        dropList.clear();
        GameFlowManager.announceWinner(null, player, oneVersusOneGame, true);

    }

}
