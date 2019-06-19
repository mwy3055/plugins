package Main;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
    @Override
    public void onEnable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.BLUE + "Plugin Enabled!");
        Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "== First Plugin by hsk ==");
    }

    @Override
    public void onDisable() {
        Bukkit.getConsoleSender().sendMessage(ChatColor.AQUA + "Plugin Disabled.");
    }

    public static void main(String[] args) {

    }
}
