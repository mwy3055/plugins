import org.bukkit.Bukkit;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

public class CommandTest implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) {
        if (sender instanceof Player) {
            Player p = (Player) sender;
            p.sendMessage("[hsk] Hi " + p.getName());
            return true;
        }
        else {
            Bukkit.getConsoleSender().sendMessage("[hsk] This Command is for players.");
            return false;
        }
    }
}
