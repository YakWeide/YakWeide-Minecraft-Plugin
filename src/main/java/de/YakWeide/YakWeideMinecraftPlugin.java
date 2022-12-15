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


  public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
    return false;
  }

}
