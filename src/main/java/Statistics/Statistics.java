package Statistics;

import de.YakWeide.YakPlayer;
import de.YakWeide.YakWeideMinecraftPlugin;

public class Statistics {

    YakPlayer yakPlayer;
    private int rockPaperScissorsWins;
    private int rockPaperScissorsLosses;
    private int OneVersusOneWins;
    private int OneVersusOneLosses;

    public Statistics(YakPlayer yakPlayer) {
        this.yakPlayer = yakPlayer;
        this.rockPaperScissorsWins = 0;
        this.rockPaperScissorsLosses = 0;
        OneVersusOneWins = 0;
        OneVersusOneLosses = 0;
    }

    public YakPlayer getYakPlayer() {
        return yakPlayer;
    }

    public void setYakPlayer(YakPlayer yakPlayer) {
        this.yakPlayer = yakPlayer;
    }

    public int getRockPaperScissorsWins() {
        return rockPaperScissorsWins;
    }

    public void setRockPaperScissorsWins(int rockPaperScissorsWins) {
        this.rockPaperScissorsWins = rockPaperScissorsWins;
    }

    public int getRockPaperScissorsLosses() {
        return rockPaperScissorsLosses;
    }

    public void setRockPaperScissorsLosses(int rockPaperScissorsLosses) {
        this.rockPaperScissorsLosses = rockPaperScissorsLosses;
    }

    public int getOneVersusOneWins() {
        return OneVersusOneWins;
    }

    public void setOneVersusOneWins(int oneVersusOneWins) {
        OneVersusOneWins = oneVersusOneWins;
    }

    public int getOneVersusOneLosses() {
        return OneVersusOneLosses;
    }

    public void setOneVersusOneLosses(int oneVersusOneLosses) {
        OneVersusOneLosses = oneVersusOneLosses;
    }
}
