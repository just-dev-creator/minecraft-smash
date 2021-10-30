package tech.justcoding.smash.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tech.justcoding.smash.game.GameStateManager;
import tech.justcoding.smash.ingame.GameStart;

public class ResetGameCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        GameStart.timer = -1;
        StartTimer.timer = -1;
        GameStateManager.gameState = GameStateManager.GameState.LOBBY;
        return false;
    }
}
