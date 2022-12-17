package de.YakWeide;

import de.YakWeide.miniGames.games.rockPaperScissors.RpsMain;
import de.YakWeide.chatApi.ChatApi;
import de.YakWeide.invitations.InvitationCommands;
import de.YakWeide.invitations.InvitationEvents;
import de.YakWeide.miniGames.miniGameApi.MiniGameApiCommands;
import de.YakWeide.miniGames.games.oneVersusOne.OneVersusOneCommands;
import de.YakWeide.miniGames.games.oneVersusOne.OneVersusOneEvents;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Objects;

public final class YakWeideMinecraftPlugin extends JavaPlugin {

    public static Plugin plugin;
    public static ArrayList<YakPlayer> yakPlayerList = new ArrayList<>();

    @Override
    public void onEnable() {
        plugin = this;
        getServer().getPluginManager().registerEvents(new Events(), this);
        getServer().getPluginManager().registerEvents(new InvitationEvents(), this);
        getServer().getPluginManager().registerEvents(new OneVersusOneEvents(), this);
        Bukkit.getServer().getPluginManager().registerEvents(ChatApi.getInstance(), this);   //Register Chat Event
        Objects.requireNonNull(this.getCommand("rps")).setExecutor(new RpsMain());
        Objects.requireNonNull(this.getCommand("coords")).setExecutor(new Commands());
        Objects.requireNonNull(this.getCommand("accept")).setExecutor(new InvitationCommands());
        Objects.requireNonNull(this.getCommand("decline")).setExecutor(new InvitationCommands());
        Objects.requireNonNull(this.getCommand("invitationtest")).setExecutor(new InvitationCommands());
        Objects.requireNonNull(this.getCommand("cancelGame")).setExecutor(new MiniGameApiCommands());
        Objects.requireNonNull(this.getCommand("getgame")).setExecutor(new MiniGameApiCommands());
        Objects.requireNonNull(this.getCommand("challengetest")).setExecutor(new OneVersusOneCommands());

        ChatApi.broadcastMessage("Plugin started");
    }

    @Override
    public void onDisable() {
        plugin = null;
    }


    public boolean onCommand(@NonNull CommandSender sender, @NonNull Command cmd, @NonNull String label, String[] args) {
        return false;
    }

}
