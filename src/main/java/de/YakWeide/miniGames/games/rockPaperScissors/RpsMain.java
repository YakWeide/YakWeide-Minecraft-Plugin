package de.YakWeide.miniGames.games.rockPaperScissors;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;
import java.util.ArrayList;

public class RpsMain implements CommandExecutor {

    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, String label, String[] args) {
        // options for rps command: /rps computer, /rps player name, if computer: starts a rps game against computer, if {player name} ask challenged player if they want to play if yes: start game against them
        if (label.equalsIgnoreCase("rps")) {
            RpsGame game = new RpsGame();
            if (!(sender instanceof Player)) {
                sender.sendMessage("The command sender has to be a player!");
                return false;
            }
            Player p1 = (Player) sender;

            if (args.length != 0 && args.length != 1) {
                ChatApi.sendMessage(p1, ChatApi.badColor + "Wrong input!");
            } else if (args.length == 0 || (args[0].equalsIgnoreCase("computer") || args[0].equalsIgnoreCase("c") || args[0].equalsIgnoreCase(p1.getName()))) {
                Thread thread = new Thread("RpsThread") {
                    @Override
                    public void run() {
                        RpsHuman human = new RpsHuman(p1.getUniqueId());
                        RpsComputer computer = new RpsComputer();
                        game.play(human, computer);
                    }
                };
                thread.start();
            } else {
                ArrayList<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
                for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
                    Player p2 = playerList.get(i);
                    if (p2.getName().equalsIgnoreCase(args[0])) {
                        Thread thread = new Thread("RpsThread") {
                            @Override
                            public void run() {
                                RpsHuman human1 = new RpsHuman(p1.getUniqueId());
                                RpsHuman human2 = new RpsHuman(p2.getUniqueId());
                                boolean challenge = human1.challenge(human2);
                                if (challenge) {
                                    game.play(human1, human2);
                                }
                            }
                        };
                        thread.start();
                        return false;
                    }
                }
                ChatApi.sendMessage(p1, ChatApi.badColor + "There is no such player online.");
            }
        }
        return false;
    }
}