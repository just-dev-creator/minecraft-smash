package tech.justcoding.smash.ingame;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;
import tech.justcoding.smash.Main;
import tech.justcoding.smash.game.GameStateManager;
import tech.justcoding.smash.utils.Config;

import java.util.ArrayList;
import java.util.List;

public class GameStart implements Listener {
    public static int timer = -1;

    public static void startGame() {
        List<Location> spawnLocations = new ArrayList<>();
        for (int i = 0; i < Bukkit.getOnlinePlayers().size(); i++) {
            spawnLocations.add(Config.getLocation("spawnlocations." + (i + 1)));
        }
        if (spawnLocations.size() != Bukkit.getOnlinePlayers().size()) {
            Bukkit.getLogger().severe(Main.getErrorPrefix() + "Not enough spawn locations!");
            return;
        }
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.teleport(spawnLocations.get(0));
            spawnLocations.remove(0);
        }
        timer = 3;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendTitle(ChatColor.BLUE + String.valueOf(timer),
                    null, 0, 20, 0);
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                if (timer >= 1) {
                    timer--;
                    if (timer == 0) {

                    } else {
                        for (Player player : Bukkit.getOnlinePlayers()) {
                            player.sendTitle(ChatColor.BLUE + String.valueOf(timer),
                                    null, 0, 20, 0);
                        }
                    }
                } else {
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        Knockback.playerPercentages.put(player, 0);
                    }
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 20, 20);
    }
    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        if (GameStateManager.gameState == GameStateManager.GameState.RUNNING && timer > 0) {
            if ((event.getFrom().getX() + event.getFrom().getY() + event.getFrom().getZ()) !=
                    (event.getTo().getX() + event.getTo().getY() + event.getTo().getZ())) {
                event.setCancelled(true);
            }
        }
    }
}
