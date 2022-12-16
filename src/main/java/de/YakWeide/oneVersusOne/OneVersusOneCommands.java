package de.YakWeide.oneVersusOne;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;

public class OneVersusOneCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command command, @NonNull String label, String[] args) {
        if(!(sender instanceof Player)){
            return false;
        }
        Player player = (Player) sender;

        if(label.equalsIgnoreCase("challenge")){
            ArrayList<Player> playerList = new ArrayList<>(Bukkit.getOnlinePlayers());
            for(int i = 0; i < Bukkit.getOnlinePlayers().size(); i++){
                Player p2 = playerList.get(i);
                if(p2.getName().equalsIgnoreCase(args[0])){
                    OneVersusOneGames oVo = new OneVersusOneGames(player, p2);
                    return oVo.begin();
                }
            }

        }
        return false;
    }
}
