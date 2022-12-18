package de.YakWeide.miniGames.games.rockPaperScissors;

import de.YakWeide.chatApi.ChatApi;

//play a game of Rock Paper Scissors in Minecraft
public class RpsGame {
    private RpsPlayer winner;

    public RpsPlayer getWinner() {
        return winner;
    }

    public void setWinner(RpsPlayer winner) {
        this.winner = winner;
    }

    public void play(RpsPlayer p1, RpsPlayer p2) {
        if (p1.setHand()) {
            if (p2.setHand()) {
                determineWinner(p1, p2);
                returnEnemyHand(p1, p2);
                returnWinner(p1, p2);
            }
        }
    }

    //determine winner of a RpsGame set winner attribute of game, null if draw
    private void determineWinner(RpsPlayer p1, RpsPlayer p2) {
        if (p1.getHand().equals(p2.getHand())) {
            this.setWinner(null);
        } else if (p1.getHand().equals(RpsOptions.ROCK) && p2.getHand().equals(RpsOptions.SCISSORS)) {

            this.setWinner(p1);
        } else if (p1.getHand().equals(RpsOptions.PAPER) && p2.getHand().equals(RpsOptions.ROCK)) {
            this.setWinner(p1);
        } else if (p1.getHand().equals(RpsOptions.SCISSORS) && p2.getHand().equals(RpsOptions.PAPER)) {
            this.setWinner(p1);
        } else {
            this.setWinner(p2);
        }
    }

    // prints the winner to both players / to the player playing against computer, note: p1 is always a human
    private void returnWinner(RpsPlayer p1, RpsPlayer p2) {
        if (this.getWinner() == null) {
            ChatApi.sendMessage(p1.getId(), "Draw!");
            if (!(p2.getId() == null)) {
                ChatApi.sendMessage(p2.getId(), "Draw!");
            }
        }
        ChatApi.sendMessage(p1.getId(), ChatApi.playerName(this.getWinner().getId()) + ChatApi.prefixColor + " has won!");
        if (p2 instanceof RpsHuman) {
            ChatApi.sendMessage(p2.getId(), ChatApi.playerName(this.getWinner().getId()) + ChatApi.prefixColor + " has won!");
        }
    }

    // returns the hand of the opponent to the human
    private void returnEnemyHand(RpsPlayer p1, RpsPlayer p2) {
        ChatApi.sendMessage(p1.getId(), ChatApi.playerName(p2.getName()) + ChatApi.prefixColor + " takes: " + p2.getHand());
        if (p2 instanceof RpsHuman) {
            ChatApi.sendMessage(p2.getId(), ChatApi.playerName(p2.getName()) + ChatApi.prefixColor + " takes: " + p2.getHand());
        }
    }
}
