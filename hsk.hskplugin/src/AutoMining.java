import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Player;
import org.bukkit.event.*;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.*;

public class AutoMining implements Listener {
    private static BlockFace[] faces = new BlockFace[]{
            BlockFace.UP,
            BlockFace.DOWN,
            BlockFace.EAST,
            BlockFace.WEST,
            BlockFace.NORTH,
            BlockFace.SOUTH
    };

    private static Material[] MiningArray = new Material[]{
            Material.COAL_ORE,
            Material.ACACIA_LOG,    Material.STRIPPED_ACACIA_LOG,
            Material.BIRCH_LOG,     Material.STRIPPED_BIRCH_LOG,
            Material.DARK_OAK_LOG,  Material.STRIPPED_DARK_OAK_LOG,
            Material.JUNGLE_LOG,    Material.STRIPPED_JUNGLE_LOG,
            Material.OAK_LOG,       Material.STRIPPED_OAK_LOG,
            Material.SPRUCE_LOG,    Material.STRIPPED_SPRUCE_LOG
    };

    private static List<Material> MiningList = Arrays.asList(MiningArray);

    private boolean doAutoMining(Material m) {
        return MiningList.contains(m);
    }

    private boolean facesLava(Block block) {
        for (BlockFace f : faces) {
            if (block.getRelative(f).getType() == Material.LAVA)
                return true;
        }
        return false;
    }

    private void sendLavaMessage(Player p) {
        p.sendMessage(ChatColor.RED + "[HskPlugin] There is a LAVA near the block");
    }

    private void AutoMine(Player p, Block start) {
        boolean LavaFlag = false;
        Material origin = start.getType();

        if (facesLava(start)) {
            sendLavaMessage(p);
            return;
        }

        ItemStack item = p.getInventory().getItemInOffHand();

        int count = 1;
        Queue<Block> q = new LinkedList<>();
        q.add(start);

        while (!q.isEmpty()) {
            Block cur = q.remove();

            for (BlockFace f : faces) {
                Block next = cur.getRelative(f);
                Material ntype = next.getType();

                if (ntype == origin && facesLava(next)) {
                    if (LavaFlag == false) {
                        sendLavaMessage(p);
                        LavaFlag = true;
                    }
                    continue;
                } else if (ntype == origin) {
                    next.breakNaturally(item);
                    count++;
                    q.add(next);
                }
            }
        }
        p.sendMessage("[HskPlugin] " + count + " block(s) automatically broken");
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
