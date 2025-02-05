package site.frii.worldborder.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import site.frii.worldborder.WorldborderManager;

public class Sync implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        WorldborderManager instance = WorldborderManager.getInstance();

        int size = instance.getConfig().getInt("size");
        double amount = instance.getConfig().getDouble("amount");

        Bukkit.getWorld("world").getWorldBorder().setSize(size*amount);
        commandSender.sendMessage(String.format("Setting worldborder to %s blocks",size*amount));

        return true;
    }
}
