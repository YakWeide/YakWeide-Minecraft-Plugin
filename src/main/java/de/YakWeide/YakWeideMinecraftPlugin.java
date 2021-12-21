package de.YakWeide;

import de.YakWeide.RockPaperScissors.RpsMain;
import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import org.jetbrains.annotations.NotNull;

public final class YakWeideMinecraftPlugin extends JavaPlugin {

  public static Plugin plugin;

  @Override
  public void onEnable() {
    plugin = this;
    getServer().getPluginManager().registerEvents(new Events(), this);
    Bukkit.getServer().getPluginManager().registerEvents(ChatApi.getInstance(), this);   //Register Chat Event
    this.getCommand("rps").setExecutor(new RpsMain());
  }

  @Override
  public void onDisable() {
    plugin = null;
  }


  public boolean onCommand(@NotNull CommandSender sender, @NotNull Command cmd, String label, String[] args) {
    if (label.equalsIgnoreCase("hello")) {
      if (sender instanceof Player) {
        Player player = (Player) sender;
        player.sendMessage("Hello world!");
        return true;
      }
    }
    else if(label.equalsIgnoreCase("coords")){
      if( sender instanceof Player) {
        Player player = (Player) sender;
        Location location = player.getLocation();
        for(int i = 0; i< Bukkit.getOnlinePlayers().size(); i++){
          ArrayList<Player> list = new ArrayList(Bukkit.getOnlinePlayers());
          Player p = (Player) list.get(i);
          p.sendMessage( player.getName() + "s " +"Koordinaten sind: " + (int) location.getX() + " " + (int) location.getY() + " " + (int) location.getZ());
        }
      }
    }else if(label.equalsIgnoreCase("lastMessage")){

      if(sender instanceof Player) {
        Player player = (Player) sender;
        String lastMessage = "";
        if(ChatApi.getInstance().lastMessage(player) == null){
          player.sendMessage("You havent sent a Message yet");
        }else {
          lastMessage = ChatApi.getInstance().lastMessage(player);
        }
        player.sendMessage("Your Last Message was: " + lastMessage);
      }

    }

    return false;
  }

}
