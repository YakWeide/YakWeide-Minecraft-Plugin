package de.YakWeide.miniGames.miniGameApi;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

/**
 * Commands the Api uses:
 * /cancelGame: Player cancels his current game
 * /getGame: Player gets the type of game he is in
 *
 * @author: Jan Reitz, Tim Lisemer
 */
public class MiniGameApiCommands implements CommandExecutor {

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, @NonNull String[] args) {
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
                ChatApi.sendMessage(player, ChatApi.badColor + "Du hast kein offenes Minispiel!");
            }
            return true;
        }

        if (label.equalsIgnoreCase("getGame")) {
            Optional<MGPlayer> mgPlayer = MGPlayerManager.getMGPlayer(player);
            if (mgPlayer.isPresent() && mgPlayer.get().getCurrentGame().isPresent()) {
                MiniGame game = mgPlayer.get().getCurrentGame().get();
                ChatApi.sendMessage(player, "Dein aktuelles Minispiel heißt: " + game.getName());
            } else {
                ChatApi.sendMessage(player, ChatApi.badColor + "Du hast kein offenes Minispiel!");
            }
            return true;
        }
        return false;
    }
}
