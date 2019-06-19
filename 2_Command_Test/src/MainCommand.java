import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.Bukkit;

public class MainCommand extends JavaPlugin {
    @Override
    public void onEnable() {
        getCommand("hello").setExecutor(new CommandTest());
        Bukkit.getLogger().info("[hsk] Plugin Enabled");
    }

    public void onDisable() {
        Bukkit.getLogger().info("[hsk] Plugin Disabled");
    }

    public static void main(String[] args) {

    }
}
