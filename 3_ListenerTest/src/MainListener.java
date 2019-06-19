import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class MainListener extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[HskPlugin] Plugin Enabled.");
        getServer().getPluginManager().registerEvents(new MyListener(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[HshPlugin] Plugin Disabled.");
    }

    public static void main (String[] args) {

    }
}
