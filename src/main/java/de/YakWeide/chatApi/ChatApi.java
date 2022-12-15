package de.YakWeide.chatApi;

import java.util.HashMap;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatApi implements Listener {

    //Singleton --> https://www.geeksforgeeks.org/singleton-class-java/
    private static ChatApi chatApi_instance = null;

    public static final ChatColor prefixColor = ChatColor.GRAY;
    private static final String prefix = prefixColor + "[" + ChatColor.GOLD + "YakWeide" + prefixColor + "] ";
    public static final ChatColor goodColor = ChatColor.GREEN;
    public static final ChatColor badColor = ChatColor.RED;
    private static final ChatColor playerColor = ChatColor.GOLD;

    public static ChatApi getInstance() {
        if (chatApi_instance == null)
            chatApi_instance = new ChatApi();
        return chatApi_instance;
    }

    private final HashMap<UUID, String> lastMessageHashMap = new HashMap<>();
    private boolean nextMessageActive = false;

    //Returns the last message of the given Player
    public String lastMessage(Player player) {
        String lastMessage = lastMessageHashMap.get(player.getUniqueId());
        if (lastMessage == null || lastMessage.equals("")) {
            return null;
        } else {
            return lastMessage;
        }
    }

    //Chat Event to log the player messages
    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlayerChat(AsyncPlayerChatEvent event) {
        Player p = event.getPlayer();

        String Message = event.getMessage();

        lastMessageHashMap.put(p.getUniqueId(), Message);
        if (nextMessageActive) {
            event.setCancelled(true);
            p.sendMessage(playerChatMessage(p, Message));
        }
    }


    //Returns the next message of the given player, returns null after 60 seconds without a message
    public String nextMessage(Player player) {
        if(Bukkit.getServer().isPrimaryThread()){
            sendMessage(player, badColor + "ChatApi.nextMessage() was aborted because it was called from the primary thread");
            return "ChatApi.nextMessage() was aborted because it was called from the primary thread";
        }
        nextMessageActive = true;
        String lastMessage = "";
        lastMessageHashMap.put(player.getUniqueId(), null);
        if (lastMessage(player) != null) lastMessage = lastMessage(player);
        sendMessage(player, "Waiting for next Message...");
        long start = System.currentTimeMillis();
        while (lastMessage(player) == null || lastMessage(player).equals(lastMessage)) {
            long loop = System.currentTimeMillis();
            if (loop / 1000 - start / 1000 == 60)
                return null; //Break after 60 seconds
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
            }
        }
        nextMessageActive = false;
        return lastMessage(player);
    }

    public void sendMessage(Player p, String message){
        p.sendMessage(prefix + message);
    }

    public String playerChatMessage(Player p, String message){
        return prefixColor + "[" + ChatColor.GOLD + p.getName() + prefixColor + "] " + message;
    }

    public void sendMessage(UUID id, String message){
        Player p = Bukkit.getPlayer(id);
        if (p != null) sendMessage(p, message);
    }

    public void BroadcastMessage(String message){
        for(Player p : Bukkit.getOnlinePlayers()){
            sendMessage(p, message);
        }
    }

    public String playerName(Player p){
        return playerColor + p.getName() + prefixColor;
    }

    public String playerName(UUID id){
        Player p = Bukkit.getPlayer(id);
        if (p != null) return playerName(p);
        return "";
    }



}