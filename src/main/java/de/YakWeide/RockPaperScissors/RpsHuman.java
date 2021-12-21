package de.YakWeide.RockPaperScissors;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RpsHuman {
    // minecraft player id
    UUID id;
    private ChatApi chatApi = null;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public RpsHuman(UUID id) {
        this.id = id;
    }


    public void setHand(Player player){
        player.sendMessage("Rock, Paper or Scissors?");
        String input = chatApi.nextMessage(player);
        if(input.equalsIgnoreCase("rock") ){
            // TODO: implement setHand method ( get input from minecraft player and set hand attribute of RpsHuman)
        }

    }
}
