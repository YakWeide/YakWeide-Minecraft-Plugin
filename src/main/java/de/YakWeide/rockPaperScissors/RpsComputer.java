package de.YakWeide.rockPaperScissors;

import java.util.Random;

public class RpsComputer extends RpsPlayer {

    public RpsComputer() {
        this.id = null;
        this.name = "The computer";
    }

    //sets hand of computer using a random number generator
    public boolean setHand() {
        Random rand = new Random();
        int random = rand.nextInt(3);
        if (random == 0) {
            this.hand = RpsOptions.ROCK;
        } else if (random == 1) {
            this.hand = RpsOptions.PAPER;
        } else {
            this.hand = RpsOptions.SCISSORS;
        }
        return true;
    }
}
