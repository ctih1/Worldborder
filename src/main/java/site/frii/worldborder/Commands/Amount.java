package site.frii.worldborder.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import site.frii.worldborder.WorldborderManager;

public class Amount implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        WorldborderManager instance = WorldborderManager.getInstance();
        instance.getConfig().set("amount", Double.parseDouble(args[0]));
        instance.saveConfig();

        commandSender.sendMessage(String.format("Set amount to %s. Use /sync to synchronize world border size.",Double.parseDouble(args[0])));

        return true;
    }
}
