package de.YakWeide;

import de.YakWeide.SSP.SSPMain;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;

public final class YakWeideMinecraftPlugin extends JavaPlugin {

  @Override
  public void onEnable() {
    getServer().getPluginManager().registerEvents(new Events(), this);
    SSPMain SSP = new SSPMain();
    getCommand("SSP").setExecutor(SSP);
    getServer().getPluginManager().registerEvents(SSP, this);

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
    }

    return false;
  }

}
