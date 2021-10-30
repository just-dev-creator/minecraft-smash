package tech.justcoding.smash.lobby;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import tech.justcoding.smash.Main;
import tech.justcoding.smash.game.GameStateManager;
import tech.justcoding.smash.ingame.GameStart;
import tech.justcoding.smash.utils.Config;

public class StartTimer {
    public static int timer = -1;
    public static final int neededPlayers = Config.getInt("timer.neededPlayers", 2);

    public static void startTimer(int time) {
        if (timer != -1) return;
        timer = time;
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.spigot().sendMessage(ChatMessageType.ACTION_BAR ,new TextComponent(
                    ChatColor.GRAY + "Verbleibende Zeit: " + ChatColor.BLUE + timer
            ));
            player.setExp(1);
            player.setLevel(timer);
        }
        new BukkitRunnable(){
            @Override
            public void run() {
                if (timer >= 1) {
                    if (Bukkit.getOnlinePlayers().size() == 0) {
                        timer = -1;
                        cancel();
                    }
                    StartTimer.timer--;
                    for (Player player : Bukkit.getOnlinePlayers()) {
                        player.spigot().sendMessage(ChatMessageType.ACTION_BAR ,new TextComponent(
                                ChatColor.GRAY + "Verbleibende Zeit: " + ChatColor.BLUE + timer
                        ));
                        player.setExp(1);
                        player.setLevel(timer);
                    }
                } else {
                    new BukkitRunnable(){
                        @Override
                        public void run() {
                            GameStateManager.gameState = GameStateManager.GameState.RUNNING;
                            for (Player player : Bukkit.getOnlinePlayers()) {
                                player.setExp(0);
                            }
                            GameStart.startGame();
                        }
                    }.runTask(Main.getPlugin(Main.class));
                    cancel();
                }
            }
        }.runTaskTimerAsynchronously(Main.getPlugin(Main.class), 20, 20);
    }
}
