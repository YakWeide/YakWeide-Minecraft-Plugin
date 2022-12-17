package de.YakWeide;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class Commands implements CommandExecutor {
    private final ChatApi chatApi = ChatApi.getInstance();

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, String[] args) {
        //Only Players are allowed to send commands
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        //Broadcast own coordinates to all other Players
        if (label.equalsIgnoreCase("coords")) {
            Location location = player.getLocation();
            chatApi.BroadcastMessage(ChatApi.getInstance().playerName(player) + ChatApi.playerColor + "s " + ChatApi.prefixColor + "Koordinaten sind: " + (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ());
        }

        //


        return false;
    }
}
