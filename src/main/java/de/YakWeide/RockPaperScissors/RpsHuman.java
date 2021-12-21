package de.YakWeide.RockPaperScissors;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;

import java.util.UUID;

public class RpsHuman extends RpsPlayer {
    // minecraft player id

    private final ChatApi chatApi = ChatApi.getInstance();



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

    //challenge another player to rps, true if accepted, false if declined
    public boolean challenge(RpsPlayer p2){
        Bukkit.getPlayer(p2.getId()).sendMessage(Bukkit.getPlayer(this.getId()).getName() + " challenges you to a game of Rock, Paper, Scissors (y/n)");
        Bukkit.getPlayer(this.getId()).sendMessage("Waiting for " + p2.getName() + "s answer!");
        String input;
        ChatApi chatApi = ChatApi.getInstance();
        input = chatApi.nextMessage(Bukkit.getPlayer(p2.getId()));
        if(input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")){
            Bukkit.getPlayer(this.getId()).sendMessage(Bukkit.getPlayer(p2.getId()).getName() + " has accepted!");
            return true;
        }
        else{
            Bukkit.getPlayer(this.getId()).sendMessage(Bukkit.getPlayer(p2.getId()).getName() + " has declined!");
            return false;
        }
    }
}
