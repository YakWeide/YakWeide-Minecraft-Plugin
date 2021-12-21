package de.YakWeide.RockPaperScissors;

import org.bukkit.Bukkit;

//play a game of Rock Paper Scissors in Minecraft
public class RpsGame {
    private RpsPlayer winner;

    public RpsPlayer getWinner() {
        return winner;
    }

    public void setWinner(RpsPlayer winner) {
        this.winner = winner;
    }

    public void play(RpsPlayer p1, RpsPlayer p2){

        p1.setHand();
        p2.setHand();
        determineWinner(p1, p2);


    }

    //determine winner of a RpsGame set winner attribute of game, null if draw
    private void determineWinner(RpsPlayer p1, RpsPlayer p2){
        if(p1.getHand().equals(p2.getHand())){
            this.setWinner(null);
        }
        else if(p1.getHand().equals(RpsOptions.ROCK) && p2.getHand().equals(RpsOptions.SCISSORS)){
            this.setWinner(p1);
        }
        else if(p1.getHand().equals(RpsOptions.PAPER) && p2.getHand().equals(RpsOptions.ROCK)){
            this.setWinner(p1);
        }
        else if(p1.getHand().equals(RpsOptions.SCISSORS) && p2.getHand().equals(RpsOptions.PAPER)){
            this.setWinner(p2);
        }
        else{
            this.setWinner(p1);
        }
    }
// prints the winner to both players / to the player playing against computer, note: p1 is always a human
    private void returnWinner(RpsPlayer p1, RpsPlayer p2){
        Bukkit.getPlayer(p1.getId()).sendMessage(this.getWinner().getName() + "has won!");
        if(!p2.getId().equals(null)){
            Bukkit.getPlayer(p2.getId()).sendMessage(this.getWinner().getName() + "has won!");
        }
    }
}
