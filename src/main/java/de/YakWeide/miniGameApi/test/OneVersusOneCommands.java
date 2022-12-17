package de.YakWeide.miniGameApi.test;

import de.YakWeide.chatApi.ChatApi;
import de.YakWeide.miniGameApi.GameFlowManager;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.checkerframework.checker.nullness.qual.NonNull;

public class OneVersusOneCommands implements CommandExecutor {
    private static final ChatApi chatApi = ChatApi.getInstance();

    @Override
    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, @NonNull String[] args) {
        //Only accept Commands coming from human
        if (!(sender instanceof Player)) {
            return false;
        }
        Player player = (Player) sender;

        if (label.equalsIgnoreCase("challengetest")) {
            OneVersusOneGame game = new OneVersusOneGame(player);
            return GameFlowManager.startGame(game, args);
        }
        return false;

    }
}
