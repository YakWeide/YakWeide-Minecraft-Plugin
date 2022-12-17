package de.YakWeide.chatApi;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.util.HashMap;
import java.util.UUID;

public class ChatApi implements Listener {

    public static final ChatColor prefixColor = ChatColor.GRAY;
    public static final ChatColor goodColor = ChatColor.GREEN;
    public static final ChatColor badColor = ChatColor.RED;
    public static final ChatColor playerColor = ChatColor.GOLD;
    private static final String prefix = prefixColor + "[" + ChatColor.GOLD + "YakWeide" + prefixColor + "] ";
    //Singleton --> https://www.geeksforgeeks.org/singleton-class-java/
    private static ChatApi chatApi_instance = null;
    private final HashMap<UUID, String> lastMessageHashMap = new HashMap<>();
    private boolean nextMessageActive = false;

    public static ChatApi getInstance() {
        if (chatApi_instance == null)
            chatApi_instance = new ChatApi();
        return chatApi_instance;
    }


    public static String lastMessage(Player player) {
        return getInstance()._lastMessage(player);
    }

    public static String nextMessage(Player player) {
        return getInstance()._nextMessage(player);
    }

    public static void sendMessage(UUID id, String message) {
        getInstance()._sendMessage(id, message);
    }


    public static String playerChatMessage(Player player, String message) {
        return getInstance()._playerChatMessage(player, message);
    }

    public static void sendMessage(Player player, String message) {
        getInstance()._sendMessage(player, message);
    }


    public static void broadcastMessage(String message) {
        getInstance()._broadcastMessage(message);
    }

    public static String playerName(UUID id) {
        return getInstance()._playerName(id);
    }

    public static String playerName(Player player) {
        return getInstance()._playerName(player);
    }

    public static String playerName(String playerString) {
        return getInstance()._playerName(playerString);
    }




    //Returns the last message of the given Player
    public String _lastMessage(Player player) {
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
    public String _nextMessage(Player player) {
        if (Bukkit.getServer().isPrimaryThread()) {
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

    public void _sendMessage(Player p, String message) {
        p.sendMessage(prefix + message);
    }

    public String _playerChatMessage(Player p, String message) {
        return prefixColor + "[" + ChatColor.GOLD + p.getName() + prefixColor + "] " + message;
    }

    public void _sendMessage(UUID id, String message) {
        Player p = Bukkit.getPlayer(id);
        if (p != null) sendMessage(p, message);
    }

    public void _broadcastMessage(String message) {
        for (Player p : Bukkit.getOnlinePlayers()) {
            sendMessage(p, message);
        }
    }

    public String _playerName(Player p) {
        return playerColor + p.getName() + prefixColor;
    }

    public String _playerName(String playerString) {
        return playerColor + playerString;
    }

    public String _playerName(UUID id) {
        Player p = Bukkit.getPlayer(id);
        if (p != null) return playerName(p);
        return "";
    }


}