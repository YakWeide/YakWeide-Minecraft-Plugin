package de.YakWeide.chatApi;

import de.YakWeide.YakWeideMinecraftPlugin;
import io.papermc.paper.event.player.AsyncChatEvent;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.HashMap;
import java.util.UUID;
import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

public class ChatApi implements Listener {

  //Singleton --> https://www.geeksforgeeks.org/singleton-class-java/
  private static ChatApi chatApi_instance = null;
  public static ChatApi getInstance()
  {
    if (chatApi_instance == null)
      chatApi_instance = new ChatApi();
    return chatApi_instance;
  }

  private final HashMap<UUID, String> lastMessageHashMap = new HashMap<>();

  //Returns the last message of the given Player
  public String lastMessage(Player player){
    String lastMessage = lastMessageHashMap.get(player.getUniqueId());
    if(lastMessage == null || lastMessage.equals("")){
      return null;
    }else{
      return lastMessage;
    }
  }

  //Chat Event to log the player messages
  @EventHandler
  public void onPlayerChat(AsyncChatEvent event){
    Player p = event.getPlayer();
    String Message = PlainTextComponentSerializer.plainText().serialize(event.message());
    lastMessageHashMap.put(p.getUniqueId(), Message);
  }


  public String nextMessageContent(Player player){
    String lastMessage = "";
    if(lastMessage(player) != null) lastMessage = lastMessage(player);
    player.sendMessage("Waiting for next Message...");


    Timestamp start = Timestamp.from(Instant.now());
    while (lastMessage(player) == null || lastMessage(player).equals(lastMessage)) {
      player.sendMessage("a");
      Timestamp loop = Timestamp.from(Instant.now());

      if (loop.getNanos() - start.getNanos() == 60000000)
        return null; //Break after 60 seconds

      try {
        Thread.sleep(1000);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }

    }
    return lastMessage(player);
  }

  //Returns the next message of the given player, returns null after 60 seconds without a message
  public String nextMessage(Player player){
    String lastMessage = "";
    lastMessageHashMap.put(player.getUniqueId(), lastMessage);
    if(lastMessage(player) != null) lastMessage = lastMessage(player);
    player.sendMessage("Waiting for next Message...");
    long start = System.currentTimeMillis();
    int i = 0;
    while (lastMessage(player) == null || lastMessage(player).equals(lastMessage)) {
      long loop = System.currentTimeMillis();
      i++;

      if (loop / 1000 - start / 1000 == 60)
        return null; //Break after 60 seconds

      try {
        Thread.sleep(100);
      } catch (InterruptedException ex) {
        Thread.currentThread().interrupt();
      }

    }
    return lastMessage;
  }


}
