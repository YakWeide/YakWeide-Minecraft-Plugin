package de.YakWeide.SSP;

import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class SSPMain implements CommandExecutor, Listener {
    private static HashMap<UUID, Integer> sspStatus = new HashMap<UUID, Integer>();

    public static void setSspStatus(UUID id, Integer status) {
        sspStatus.put(id, status);
    }

    public static void getSspStatus(UUID id) {
        sspStatus.get(id);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (label.equalsIgnoreCase("SSP")) {
            if (sender instanceof Player) {
                Player p1 = (Player) sender;
                Player p2;
                setSspStatus(p1.getUniqueId(), 1);
                ArrayList<Player> onlinePlayers = new ArrayList(Bukkit.getOnlinePlayers());

                if (args[0].equalsIgnoreCase("Computer") || args[0].equalsIgnoreCase("c")) {
                    SspComputer c1 = new SspComputer();
                    //SspGame.play(p1, c1);
                }

                for (Player player : onlinePlayers) {
                    if (args[0].equalsIgnoreCase(player.getName())) {
                        p2 = player;
                        break;
                    }
                }
            }
        }
        return false;
    }


    @EventHandler
    public void onPlayerChat(AsyncChatEvent e) {
        Player p1 = e.getPlayer();
        if (sspStatus.get(p1.getUniqueId()) == 1) {
            Component input = e.message();
            sspStatus.put(p1.getUniqueId(), 1);
            e.setCancelled(true);


        }
        else if(sspStatus.get(p1.getUniqueId()) == 2){
            Component input = e.message();
            //sspStatus.put

        }
    }
}
