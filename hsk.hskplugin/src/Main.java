import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        Bukkit.getLogger().info("[HskPlugin] Plugin Enabled.");
        getServer().getPluginManager().registerEvents(new AutoMining(), this);
    }

    @Override
    public void onDisable() {
        Bukkit.getLogger().info("[HskPlugin] Plugin Disabled.");
    }

    public static void main(String[] args) {

    }
}
