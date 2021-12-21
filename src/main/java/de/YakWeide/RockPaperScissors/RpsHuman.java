package de.YakWeide.RockPaperScissors;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;

import java.util.UUID;

public class RpsHuman extends RpsPlayer {
    // minecraft player id

    private ChatApi chatApi = ChatApi.getInstance();



    public RpsHuman(UUID id) {
        this.id = id;
        this.name = Bukkit.getPlayer(id).getName();
    }

    //set hand of a Rps Human using input from minecraft text chat
    public boolean setHand(){
        Bukkit.getPlayer(this.getId()).sendMessage("Rock, Paper or Scissors?");
        String input = chatApi.nextMessage(Bukkit.getPlayer(this.getId()));
        if(input == null){
            Bukkit.getPlayer(this.getId()).sendMessage("Rock Paper Scissors was canceled because no message was sent within 60 seconds");
            return false;
        }
        if(input.equalsIgnoreCase("rock") ){
            this.hand = RpsOptions.ROCK;
        }
        else if(input.equalsIgnoreCase("paper")){
            this.hand = RpsOptions.PAPER;
        }
        else if(input.equalsIgnoreCase("scissors")){
            this.hand = RpsOptions.SCISSORS;
        }else{
            Bukkit.getPlayer(this.getId()).sendMessage("Rock Paper Scissors canceled because an incorrect message was sent");
            return false;
        }
        return true;
    }
}
