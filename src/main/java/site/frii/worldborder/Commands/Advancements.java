package site.frii.worldborder.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import site.frii.worldborder.WorldborderManager;

public class Advancements implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String s, @NotNull String[] strings) {
        WorldborderManager instance = WorldborderManager.getInstance();

        commandSender.sendMessage(String.format(
                "You have unlocked §b§l%s advancements§r.",
                instance.getConfig().getInt("size")
        ));
        return true;
    }
}
