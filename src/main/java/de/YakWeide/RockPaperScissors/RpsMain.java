package de.YakWeide.RockPaperScissors;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

public class RpsMain implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        // options for rps command: /rps computer, /rps playername, if computer: starts a rps game against computer, if {playername} ask challenged player if it wants to play if yes: start game against player
        if(label.equalsIgnoreCase("rps")){
            RpsGame game = new RpsGame();
            if(!(sender instanceof Player)){ sender.sendMessage("The command sender has to be a player!");}
            Player p1 = (Player)sender;
            if(args.length > 0 && (args[0].equalsIgnoreCase("computer") || args[0].equalsIgnoreCase("c"))){
                RpsHuman human = new RpsHuman(p1.getUniqueId());
                RpsComputer computer = new RpsComputer();
                game.play(human, computer);
            }
            else if(args.length > 0 && !(args[0].equals(null))){
                ArrayList<Player> playerList = new ArrayList<Player>(Bukkit.getOnlinePlayers());
                for(int i = 0; i < Bukkit.getOnlinePlayers().size(); i++){
                    Player p2 = playerList.get(i);
                    if(p2.getName().equalsIgnoreCase(args[0])){
                        RpsHuman human1 = new RpsHuman(p1.getUniqueId());
                        RpsHuman human2 = new RpsHuman(p2.getUniqueId());
                        boolean challenge = human1.challenge(human2);
                        if(challenge){
                            game.play(human1, human2);
                        }
                        return false;
                    }
                }
                p1.sendMessage("There is no such player online.");
            }
            else{
                p1.sendMessage("Falsche Eingabe!");
            }
        }
        return false;
    }
}
