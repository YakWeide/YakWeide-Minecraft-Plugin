package de.YakWeide.RockPaperScissors;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;

import java.util.UUID;

public class RpsHuman extends RpsPlayer {
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

    //set hand of a Rps Human using input from minecraft text chat
    public void setHand(){
        Bukkit.getPlayer(this.getId()).sendMessage("Rock, Paper or Scissors?");
        String input = chatApi.nextMessage(Bukkit.getPlayer(this.getId()));
        if(input.equalsIgnoreCase("rock") ){
            this.hand = RpsOptions.ROCK;
        }
        else if(input.equalsIgnoreCase("paper")){
            this.hand = RpsOptions.PAPER;
        }
        else if(input.equalsIgnoreCase("scissors")){
            this.hand = RpsOptions.SCISSORS;
        }

    }
}
