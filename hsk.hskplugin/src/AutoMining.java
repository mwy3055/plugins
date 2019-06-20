import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;

import java.util.Queue;
import java.util.LinkedList;

public class AutoMining implements Listener {
    static BlockFace[] faces = new BlockFace[]{
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.NORTH,
            BlockFace.SOUTH
    };

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
    private boolean facesLava(Block block) {
        for (BlockFace f : faces) {
            if (block.getRelative(f).getType() == Material.LAVA)
                return true;
        }
        return false;
    }
    private void sendLavaMessage(Player p) {
        p.sendMessage("[HskPlugin] There is a LAVA near the block");
    }

    private void AutoMine(Player p, Block start) {
        boolean LavaFlag = false;
        Material origin = start.getType();

        if(facesLava(start)) {
            sendLavaMessage(p);
            return;
        }

        Queue<Block> q = new LinkedList<>();
        q.add(start);
        while (!q.isEmpty()) {
            Block cur = q.remove();

            for (BlockFace f : faces) {
                Block next = cur.getRelative(f);
                Material ntype = next.getType();

                if (ntype == origin && facesLava(next)) {
                    if(LavaFlag == false) {
                        sendLavaMessage(p);
                        LavaFlag = true;
                    }
                    continue;
                } else if (ntype == origin) {
                    next.breakNaturally();
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
        if (!doAutoMining(e.getBlock().getType())) {
            return;
        }

        AutoMine(e.getPlayer(), e.getBlock());
    }
}
