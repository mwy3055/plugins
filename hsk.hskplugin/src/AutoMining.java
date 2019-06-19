import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Queue;
import java.util.LinkedList;

public class AutoMining implements Listener {
    private boolean doAutoMining(Material m) {
        if (m == Material.COAL_ORE ||
                m == Material.ACACIA_LOG ||
                m == Material.BIRCH_LOG ||
                m == Material.DARK_OAK_LOG ||
                m == Material.JUNGLE_LOG ||
                m == Material.OAK_LOG ||
                m == Material.SPRUCE_LOG) {
            return true;
        }
        return false;
    }

    private void doAutoMine(Player p, Location start) {
        int[][] src = {{1, 0, 0}, {0, 1, 0}, {-1, 0, 0}, {0, -1, 0}, {0, 0, 1}, {0, 0, -1}};
        Material origin = start.getBlock().getType();

        Queue<Location> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Location cur = q.remove();

            for (int a = 0; a < 6; a++) {
                int sx = cur.getBlockX() + src[a][0];
                int sy = cur.getBlockY() + src[a][1];
                int sz = cur.getBlockZ() + src[a][2];

                Location next = new Location(cur.getWorld(), sx, sy, sz);
                if(next.getBlock().getType() == Material.LAVA) {
                    p.sendMessage("[HskPlugin] There is a LAVA near the block");
                    continue;
                }
                else if (next.getBlock().getType() == origin) {
                    next.getBlock().breakNaturally();
                    q.add(next);
                }
            }
        }
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        e.setJoinMessage(ChatColor.AQUA + "[HskPlugin] " + e.getPlayer().getName() + " Joined! ");
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent e) {
        if (doAutoMining(e.getBlock().getType()) == false) {
            return;
        }

        Player p = e.getPlayer();
        Location cur = new Location(p.getWorld(), e.getBlock().getX(), e.getBlock().getY(), e.getBlock().getZ());
        doAutoMine(p, cur);
    }
}
