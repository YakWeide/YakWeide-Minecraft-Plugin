package de.YakWeide.miniGameApi;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class MiniGameApiCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, @NotNull String label, @NotNull String[] args) {
        //Only accept Commands coming from human
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;
        if (label.equalsIgnoreCase("cancelGame")) {
            Optional<MGPlayer> mgPlayer = MGPlayerManager.getMGPlayer(player);
            if (mgPlayer.isPresent() && mgPlayer.get().getCurrentGame().isPresent()) {
                GameFlowManager.cancelGame(mgPlayer.get().getCurrentGame().get());
            } else {
                ChatApi chatApi = ChatApi.getInstance();
                chatApi.sendMessage(player, ChatApi.badColor + "Du hast kein offenes Minispiel!");
            }
            return true;
        }
        return false;
    }
}
