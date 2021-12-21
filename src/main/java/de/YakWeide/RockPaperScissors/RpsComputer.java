package de.YakWeide.RockPaperScissors;

import java.util.Random;

public class RpsComputer extends RpsPlayer {

    //sets hand of computer using a random number generator
    public void setHand(){
        Random rand = new Random();
        int random = rand.nextInt(3);
        if(random == 0){
            this.hand = RpsOptions.ROCK;
        }
        else if(random == 1){
            this.hand = RpsOptions.PAPER;
        }
        else if(random == 2){
            this.hand = RpsOptions.SCISSORS;
        }

    }
}
