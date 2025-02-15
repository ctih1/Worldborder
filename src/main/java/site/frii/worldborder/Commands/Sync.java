package site.frii.worldborder.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import site.frii.worldborder.WorldborderManager;

public class Sync implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, String[] args) {
        WorldborderManager instance = WorldborderManager.getInstance();

        int size = instance.getConfig().getInt("size");
        double amount = instance.getConfig().getDouble("amount");

        Bukkit.getWorld("world").getWorldBorder().setSize(size*amount);
        Bukkit.broadcastMessage(String.format("Setting world border to §b§l%s§r blocks.",size*amount));

        return true;
    }
}
