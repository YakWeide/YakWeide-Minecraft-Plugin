package de.YakWeide.RockPaperScissors;

import de.YakWeide.chatApi.ChatApi;

import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

public class RpsHuman extends RpsPlayer {
    // minecraft player id

    private final ChatApi chatApi = ChatApi.getInstance();

    public RpsHuman(UUID id) {
        this.id = id;
        Player player = Bukkit.getPlayer(id);
        assert player != null;
        this.name = player.getName();
    }

    //set hand of a Rps Human using input from minecraft text chat
    public boolean setHand() {
        chatApi.sendMessage(this.getId(), "Rock, Paper or Scissors?");
        String input = chatApi.nextMessage(Bukkit.getPlayer(this.getId()));
        if (input == null) {
            chatApi.sendMessage(this.getId(), ChatApi.badColor + "Rock Paper Scissors was canceled because no message was sent within 60 seconds");
            return false;
        }
        if (input.equalsIgnoreCase("rock")) {
            this.hand = RpsOptions.ROCK;
        } else if (input.equalsIgnoreCase("paper")) {
            this.hand = RpsOptions.PAPER;
        } else if (input.equalsIgnoreCase("scissors")) {
            this.hand = RpsOptions.SCISSORS;
        } else {
            chatApi.sendMessage(this.getId(), ChatApi.badColor + "Rock Paper Scissors canceled because an incorrect message was sent: " + input);
            return false;
        }
        return true;
    }

    //challenge another player to rps, true if accepted, false if declined
    public boolean challenge(RpsPlayer p2) {
        chatApi.sendMessage(p2.getId(), chatApi.playerName(this.getId()) + ChatApi.prefixColor + " challenges you to a game of Rock, Paper, Scissors (" + ChatApi.goodColor + "y/" + ChatApi.badColor + "n)");
        chatApi.sendMessage(this.getId(), "Waiting for " + chatApi.playerName(p2.getId()) + "s " + ChatApi.prefixColor + "answer!");
        String input;
        ChatApi chatApi = ChatApi.getInstance();
        input = chatApi.nextMessage(Bukkit.getPlayer(p2.getId()));
        if (input.equalsIgnoreCase("y") || input.equalsIgnoreCase("yes")) {
            chatApi.sendMessage(this.getId(), chatApi.playerName(p2.getId()) + ChatApi.goodColor + " has accepted!");
            return true;
        } else {
            chatApi.sendMessage(this.getId(), chatApi.playerName(p2.getId()) + ChatColor.RED + " has declined!");
            return false;
        }
    }
}
