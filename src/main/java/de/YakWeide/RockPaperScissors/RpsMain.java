package de.YakWeide.RockPaperScissors;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

public class RpsMain implements CommandExecutor {
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
        // options for rps command: /rps computer, /rps playername, if computer: starts a rps game against computer, if {playername} ask challenged player if it wants to play if yes: start game against player
        if(label.equalsIgnoreCase("rps")){
            RpsGame game = new RpsGame();
            if(!(sender instanceof Player)){ sender.sendMessage("The command sender has to be a player!");}
            Player p1 = (Player)sender;
            if(args[0].equalsIgnoreCase("computer") || args[0].equalsIgnoreCase("c")){
                RpsHuman human = new RpsHuman(p1.getUniqueId());
                RpsComputer computer = new RpsComputer();
                game.play(human, computer);
            }
        }
        return false;
    }
}
