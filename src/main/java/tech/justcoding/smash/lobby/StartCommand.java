package tech.justcoding.smash.lobby;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import tech.justcoding.smash.Main;

public class StartCommand implements CommandExecutor {
    /**
     * Executes the given command, returning its success.
     * <br>
     * If false is returned, then the "usage" plugin.yml entry for this command
     * (if defined) will be sent to the player.
     *
     * @param sender  Source of the command
     * @param command Command which was executed
     * @param label   Alias of the command which was used
     * @param args    Passed command arguments
     * @return true if a valid command, otherwise false
     */
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!sender.isOp() && !sender.hasPermission("smash.commands.start")) return false;
        if (StartTimer.timer > 10) {
            StartTimer.timer = 10;
            sender.sendMessage(Main.getPrefix() + "Du hast die Startzeit erfolgreich verkürzt!");
        } else if (StartTimer.timer == -1) {
            StartTimer.startTimer(10);
            sender.sendMessage(Main.getPrefix() + "Du hast das Spiel erfolgreich gestartet!");
        } else {
            sender.sendMessage(Main.getErrorPrefix() + "Das Spiel wurde bereits gestartet!");
        }
        return true;
    }
}
