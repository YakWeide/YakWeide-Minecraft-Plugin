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

    public boolean play(RpsPlayer p1, RpsPlayer p2){

        if(p1.setHand()){
            if(p2.setHand()){
                determineWinner(p1, p2);
                returnEnemyHand(p1,p2);
                returnWinner(p1,p2);
                return true;
            }
        }
        return false;
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
            this.setWinner(p1);
        }
        else{
            this.setWinner(p2);
        }
    }
// prints the winner to both players / to the player playing against computer, note: p1 is always a human
    private void returnWinner(RpsPlayer p1, RpsPlayer p2){
        if(this.getWinner() == null){
            Bukkit.getPlayer(p1.getId()).sendMessage("Draw!");
            if(!(p2.getId() == null)){
                Bukkit.getPlayer(p2.getId()).sendMessage("Draw!");
            }
        }
        Bukkit.getPlayer(p1.getId()).sendMessage(this.getWinner().getName() + "has won!");
        if(p2 instanceof RpsHuman){
            Bukkit.getPlayer(p2.getId()).sendMessage(this.getWinner().getName() + "has won!");
        }
    }
    // returns the hand of the opponent to the human
    private void returnEnemyHand(RpsPlayer p1, RpsPlayer p2){
        Bukkit.getPlayer(p1.getId()).sendMessage(p2.getName() + " takes: " + p2.getHand());
        if(p2 instanceof RpsHuman){
            Bukkit.getPlayer(p2.getId()).sendMessage(p1.getName() + " takes:" + p1.getHand());
        }
    }
}
