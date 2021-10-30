package tech.justcoding.smash.ingame;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.justcoding.smash.Main;
import tech.justcoding.smash.utils.Config;

public class SetSpawnCommand implements CommandExecutor {
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
        if (!sender.isOp()) {
            sender.sendMessage(Main.getNoPermission());
        } else if (args.length != 1) {
            sender.sendMessage(Main.getErrorPrefix() + "Please provide the number of the spawn point you want to set.");
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getNoPlayer());
        } else {
            Config.setLocation("spawnlocations." + args[0], ((Player) sender).getLocation());
            sender.sendMessage(Main.getPrefix() + "Location set successfully");
            return true;
        }
        return false;
    }
}
