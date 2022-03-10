package de.YakWeide;

import de.YakWeide.RockPaperScissors.RpsMain;
import de.YakWeide.chatApi.ChatApi;
import java.util.Objects;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

public final class YakWeideMinecraftPlugin extends JavaPlugin {

  public static Plugin plugin;
  private ChatApi chatApi;

  @Override
  public void onEnable() {
    plugin = this;
    getServer().getPluginManager().registerEvents(new Events(), this);
    Bukkit.getServer().getPluginManager().registerEvents(ChatApi.getInstance(), this);   //Register Chat Event
    chatApi = ChatApi.getInstance();
    Objects.requireNonNull(this.getCommand("rps")).setExecutor(new RpsMain());

    chatApi.BroadcastMessage("Plugin started");
  }

  @Override
  public void onDisable() {
    plugin = null;
  }


  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
    if (label.equalsIgnoreCase("hello")) {
      if (sender instanceof Player) {
        Player player = (Player) sender;
        chatApi.sendMessage(player, "Hello world!");
        return true;
      }
    }
    else if(label.equalsIgnoreCase("coords")){
      if( sender instanceof Player) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        chatApi.BroadcastMessage(ChatApi.getInstance().playerName(player) + "s " + ChatApi.prefixColor + "Koordinaten sind: " + (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ());
      }
    }else if(label.equalsIgnoreCase("lastMessage")){

      if(sender instanceof Player) {
        Player player = (Player) sender;
        String lastMessage;
        if(ChatApi.getInstance().lastMessage(player) == null){
          chatApi.sendMessage(player, ChatApi.badColor + "You haven't sent a Message yet");
        }else {
          lastMessage = ChatApi.getInstance().lastMessage(player);
          chatApi.sendMessage(player, "Your Last Message was: " + lastMessage);
        }
      }

    }

    return false;
  }

}
