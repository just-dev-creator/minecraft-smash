package tech.justcoding.smash.game;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import tech.justcoding.smash.Main;

public class ChangeWorldCommand implements CommandExecutor {
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
        if (sender.isOp()) {
            sender.sendMessage(Main.getNoPermission());
        } else if (args.length != 1) {
            sender.sendMessage(Main.getErrorPrefix() + "You need to specify the desired world to which " +
                    "you want to teleport.");
        } else if (!(sender instanceof Player)) {
            sender.sendMessage(Main.getNoPlayer());
        } else {
            ((Player) sender).teleport(Bukkit.getWorld(args[0]).getSpawnLocation());
            return true;
        }
        return false;
    }
}
