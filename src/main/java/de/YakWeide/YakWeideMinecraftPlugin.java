package de.YakWeide;

import de.YakWeide.RockPaperScissors.RpsMain;
import de.YakWeide.chatApi.ChatApi;
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
  public static ArrayList<YakPlayer> yakPlayerList= new ArrayList<YakPlayer>();

  @Override
  public void onEnable() {
    plugin = this;
    getServer().getPluginManager().registerEvents(new Events(), this);
    Bukkit.getServer().getPluginManager().registerEvents(ChatApi.getInstance(), this);   //Register Chat Event
    ChatApi chatApi = ChatApi.getInstance();
    Objects.requireNonNull(this.getCommand("rps")).setExecutor(new RpsMain());

    chatApi.BroadcastMessage("Plugin started");
  }

  @Override
  public void onDisable() {
    plugin = null;
  }


  public boolean onCommand(@NonNull CommandSender sender,@NonNull  Command cmd,@NonNull  String label, String[] args) {
    return false;
  }

}
