package tech.justcoding.smash;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;
import tech.justcoding.smash.game.GameStateManager;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!isServerReady()) {
            Bukkit.getLogger().warning(Main.getErrorPrefix() +
                    "The plugin will be unloaded for your own safety.");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        GameStateManager.gameState = GameStateManager.GameState.LOBBY;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    private boolean isServerReady() {
        if (Bukkit.getWorld("lobby") == null) {
            Bukkit.getLogger().severe(getErrorPrefix() + "Lobby world is missing!");
            return false;
        } else if (Bukkit.getWorld("game") == null) {
            Bukkit.getLogger().severe(getErrorPrefix() + "Game world is missing!");
            return false;
        }
        return true;
    }

    public static String getPrefix() {
        return ChatColor.DARK_GRAY.toString() + "┃ " + ChatColor.BLUE + "Game" + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getPrefix(String customPrefix) {
        return ChatColor.DARK_GRAY.toString() + "┃ " + ChatColor.BLUE + customPrefix + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getErrorPrefix() {
        return ChatColor.DARK_GRAY.toString() + "┃ " + ChatColor.DARK_RED + "ERROR" + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getNoPermission() {
        return getErrorPrefix() + "Du hast nicht die benötigten Berechtigungen für den Befehl. ";
    }
    public static String getNoPlayer() {
        return getErrorPrefix() + "Du musst ein Spieler sein, um diese Aktion auszuführen. ";
    }
}
