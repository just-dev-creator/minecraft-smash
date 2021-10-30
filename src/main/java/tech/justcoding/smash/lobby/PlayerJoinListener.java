package tech.justcoding.smash.lobby;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import tech.justcoding.smash.game.GameStateManager;

public class PlayerJoinListener implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        if (GameStateManager.gameState == GameStateManager.GameState.LOBBY) {
            event.getPlayer().teleport(Bukkit.getWorld("lobby").getSpawnLocation());
            if (Bukkit.getOnlinePlayers().size() >= StartTimer.neededPlayers) {
                StartTimer.startTimer(60);
            }
        }
    }
}
