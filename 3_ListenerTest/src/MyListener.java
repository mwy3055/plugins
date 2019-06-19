import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;


public class MyListener implements Listener{
    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage("[HskPlugin] "+ e.getPlayer().getName() + " Joined! ");
    }
}
