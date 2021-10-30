package tech.justcoding.smash.game;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class GriefPreventor implements Listener {
    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if (event.getPlayer().isOp() && event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if (event.getPlayer().isOp() && event.getPlayer().isSneaking()) return;
        event.setCancelled(true);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK)) {
            if (event.getPlayer().isOp() && event.getPlayer().isSneaking()) return;
            event.setCancelled(true);
        }
    }
}
