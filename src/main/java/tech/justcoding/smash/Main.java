package tech.justcoding.smash;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.WorldCreator;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import tech.justcoding.smash.game.ChangeWorldCommand;
import tech.justcoding.smash.game.GameStateManager;
import tech.justcoding.smash.game.GriefPreventor;
import tech.justcoding.smash.ingame.GameStart;
import tech.justcoding.smash.ingame.Knockback;
import tech.justcoding.smash.ingame.SetSpawnCommand;
import tech.justcoding.smash.lobby.PlayerJoinListener;
import tech.justcoding.smash.lobby.ResetGameCommand;
import tech.justcoding.smash.lobby.StartCommand;
import tech.justcoding.smash.utils.Config;
import tech.justcoding.smash.utils.Utils;

import java.util.Random;

public final class Main extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        if (!isServerReady()) {
            Bukkit.getLogger().warning(Main.getErrorPrefix() +
                    "The plugin will be unloaded for your own safety.");
            Bukkit.getPluginManager().disablePlugin(this);
            return;
        }
        loadWorlds();
        registerListeners();
        registerCommands();
        Config.registerConfiguration();
        GameStateManager.gameState = GameStateManager.GameState.LOBBY;
    }

    private void registerListeners() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        pluginManager.registerEvents(new PlayerJoinListener(), this);
        pluginManager.registerEvents(new GameStart(), this);
        pluginManager.registerEvents(new GriefPreventor(), this);
        pluginManager.registerEvents(new Knockback(), this);
    }

    private void registerCommands() {
        getCommand("start").setExecutor(new StartCommand());
        getCommand("setloc").setExecutor(new SetSpawnCommand());
        getCommand("changeworld").setExecutor(new ChangeWorldCommand());
        getCommand("rsgame").setExecutor(new ResetGameCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic

    }

    private boolean isServerReady() {
        if (!Utils.doesWorldFileExist("lobby")) {
            Bukkit.getLogger().severe(getErrorPrefix() + "Lobby world is missing!");
            return false;
        } else if (!Utils.doesWorldFileExist("game")) {
            Bukkit.getLogger().severe(getErrorPrefix() + "Game world is missing!");
            return false;
        }
        return true;
    }

    private void loadWorlds() {
        new WorldCreator("lobby").createWorld();
        new WorldCreator("game").createWorld();
    }

    public static String getPrefix() {
        return ChatColor.DARK_GRAY + "┃ " + ChatColor.BLUE + "Game" + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getPrefix(String customPrefix) {
        return ChatColor.DARK_GRAY + "┃ " + ChatColor.BLUE + customPrefix + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getErrorPrefix() {
        return ChatColor.DARK_GRAY + "┃ " + ChatColor.DARK_RED + "ERROR" + ChatColor.DARK_GRAY + " » " + ChatColor.GRAY;
    }
    public static String getNoPermission() {
        return getErrorPrefix() + "You do not have the required permissions for the command. ";
    }
    public static String getNoPlayer() {
        return getErrorPrefix() + "You must be a player to perform this action. ";
    }
    public static final Random random = new Random();
}
