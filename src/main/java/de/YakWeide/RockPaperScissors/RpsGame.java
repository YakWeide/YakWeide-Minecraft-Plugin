package de.YakWeide.RockPaperScissors;
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

    private void returnWinner(RpsPlayer p1, RpsPlayer p2){
        // todo: returns winner of the Rock Paper Scissors game to both players / to the player playing against computer
    }
}
