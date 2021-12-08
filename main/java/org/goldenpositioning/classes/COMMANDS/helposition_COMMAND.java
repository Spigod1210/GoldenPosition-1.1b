package org.goldenpositioning.classes.COMMANDS;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.goldenpositioning.classes.MAIN;

public class helposition_COMMAND implements CommandExecutor {


    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(args.length == 0) {
        sender.sendMessage("§8-- §6" + MAIN.getPlugin().getName() + " §8--"
                + "\n" + "§a/position " + "§8- §7look up your Coordinates"
                + "\n" + "§a/position list " + "§8- §7list of all position names"
                + "\n" + "§a/position public " + "§8- §7list of all public position names"
                + "\n" + "§a/position private " + "§8- §7list of all private position names"
                + "\n" + "§a/position pin <positionname> " + "§8- §7create an public position"
                + "\n" + "§a/position player <player> " + "§8- §7get the position of a player"
                + "\n" + "§a/position send <all,player> " + "§8- §7send your position to someone"
                + "\n" + "§a/position save <temp,semi,perm> <positionname> " + "§8- §7save your position for a certain duration"
                + "\n" + "§a/position public get <positionname> " + "§8- §7get the public position of given name"
                + "\n" + "§a/position private get <temp,semi,perm> <positionname> " + "§8- §7get the private position of given name");
        }

        return false;
    }
}
