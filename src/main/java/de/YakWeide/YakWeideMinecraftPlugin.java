package de.YakWeide;

import de.YakWeide.chatApi.ChatApi;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class YakWeideMinecraftPlugin extends JavaPlugin {

  private ChatApi chatApi = null;

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(new Events(), this);
    chatApi = new ChatApi(this);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }


  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
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
        if(chatApi.lastMessage(player) == null){
          player.sendMessage("You haven sent a Message yet");
        }else {
          lastMessage = chatApi.lastMessage(player);
        }
        player.sendMessage("Your Last Message was: " + lastMessage);
      }

    }

    return false;
  }

}
