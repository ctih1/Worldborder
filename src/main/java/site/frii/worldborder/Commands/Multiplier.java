package site.frii.worldborder.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import site.frii.worldborder.WorldborderManager;

public class Multiplier implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, String[] args) {
        WorldborderManager instance = WorldborderManager.getInstance();

        double amount = instance.getConfig().getDouble("amount");
        if(args.length != 1) {
            commandSender.sendMessage(String.format(
                    "Current world border size per advancement: §b§l%s blocks§r §o(10 advancements = %s blocks)§r",
                    amount,
                    10*amount
            ));
            return true;
        }
        instance.getConfig().set("amount", Double.parseDouble(args[0]));
        instance.saveConfig();

        commandSender.sendMessage(String.format("Set expand amount per advancement to §b§l%s blocks§r. Use §l/sync§r to synchronize world border size.",Double.parseDouble(args[0])));

        return true;
    }
}
