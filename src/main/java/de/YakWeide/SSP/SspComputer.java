package de.YakWeide.SSP;

import java.util.Random;

public class SspComputer extends SspGamer {
    public void setHand(){
        Random random = new Random();
        int rand = random.nextInt(3);
        if(rand == 0){
            this.setHand(SspOptions.SCHERE);
        }
        else if(rand == 1){
            this.setHand(SspOptions.STEIN);
        }
        else if(rand == 2){
            this.setHand(SspOptions.PAPIER);
        }
    }
}
