package de.YakWeide.miniGames.games.rockPaperScissors;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import java.util.UUID;

public class RpsHuman extends RpsPlayer {
    // minecraft player id
    public RpsHuman(UUID id) {
        this.id = id;
        Player player = Bukkit.getPlayer(id);
        assert player != null;
        this.name = player.getName();
    }

    //set hand of a Rps Human using input from minecraft text chat
    public boolean setHand() {
        ChatApi.sendMessage(this.getId(), "Rock, Paper or Scissors?");
        String input = ChatApi.nextMessage(Bukkit.getPlayer(this.getId()));
        if (input == null) {
            ChatApi.sendMessage(this.getId(), ChatApi.badColor + "Rock Paper Scissors was canceled because no message was sent within 60 seconds");
            return false;
        }
        if (input.equalsIgnoreCase("rock")) {
            this.hand = RpsOptions.ROCK;
        } else if (input.equalsIgnoreCase("paper")) {
            this.hand = RpsOptions.PAPER;
        } else if (input.equalsIgnoreCase("scissors")) {
            this.hand = RpsOptions.SCISSORS;
        } else {
            ChatApi.sendMessage(this.getId(), ChatApi.badColor + "Rock Paper Scissors canceled because an incorrect message was sent: " + input);
            return false;
        }
        return true;
    }

    //challenge another player to rps, true if accepted, false if declined
    public boolean challenge(RpsPlayer p2) {
        ChatApi.sendMessage(p2.getId(), ChatApi.playerName(this.getId()) + ChatApi.prefixColor + " challenges you to a game of Rock, Paper, Scissors (" + ChatApi.goodColor + "y/" + ChatApi.badColor + "n)");
        ChatApi.sendMessage(this.getId(), "Waiting for " + ChatApi.playerName(p2.getId()) + "s " + ChatApi.prefixColor + "answer!");
        String input;
        ChatApi chatApi = ChatApi.getInstance();
        input = ChatApi.nextMessage(Bukkit.getPlayer(p2.getId()));
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            ChatApi.sendMessage(this.getId(), ChatApi.playerName(p2.getId()) + ChatApi.goodColor + " has accepted!");
            return true;
        } else {
            ChatApi.sendMessage(this.getId(), ChatApi.playerName(p2.getId()) + ChatColor.RED + " has declined!");
            return false;
        }
    }
}
