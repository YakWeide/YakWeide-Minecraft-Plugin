package de.YakWeide.chatApi;

import de.YakWeide.Events;
import io.papermc.paper.event.player.AsyncChatEvent;
import java.util.HashMap;
import java.util.UUID;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ChatApi implements Listener {

  private HashMap<UUID, String> lastMessageHashMap = new HashMap<>();

  public ChatApi(Plugin plugin) {
    Bukkit.getServer().getPluginManager().registerEvents(this, plugin);
  }

  public String lastMessage(Player player){
    if(lastMessageHashMap.get(player.getUniqueId()) == null){
      return "Error you haven't send a Message yet";
    }else{
      return lastMessageHashMap.get(player.getUniqueId());
    }
  }


  @EventHandler
  public void onPlayerChat(AsyncChatEvent event){
    Player p = event.getPlayer();
    String Message = String.valueOf(event.originalMessage());
    lastMessageHashMap.put(p.getUniqueId(), Message);
  }


}
