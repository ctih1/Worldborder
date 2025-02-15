package site.frii.worldborder.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import site.frii.worldborder.WorldborderManager;

import java.util.Arrays;
import java.util.Map;

public class Settings  implements CommandExecutor {

    private final Map<String,String> help = Map.of(
        "message","Message to be sent once the world border is expanded. If you want to include the new world border size, you can do so by adding \"{SIZE}\" to your message. Example: \"The new world border size is {SIZE} blocks\"",
        "sound", "Play a sound effect when the world border is expanded (yes/no)"
    );

    private final Map<String, Boolean> selects = Map.of(
            "yes",true,
            "y",true,
            "true",true,
            "no", false,
            "n", false,
            "false",false
    );

    @Override
    public boolean onCommand(@NotNull CommandSender commandSender, @NotNull Command command, @NotNull String label, String[] args) {
        WorldborderManager instance = WorldborderManager.getInstance();

        if(args.length < 1) {
            StringBuilder helpMessage = new StringBuilder();
            help.forEach((key,val) -> {
                helpMessage.append(String.format(
                        "§b§l%s§r: %s\n\n",
                        key,
                        val
                ));

            });
            commandSender.sendMessage(helpMessage.toString());
            return true;
        }
        if(args.length == 1) {
            commandSender.sendMessage(String.format("Help for setting \"§b§l%s§r\": %s",
                    args[0],
                    help.getOrDefault(
                            args[0],
                            "Setting does not exist! Try '/settings' to see available settings"
                    )
            ));
            return true;
        }
        switch(args[0]) {
            case "message":
                String message = String.join(" ",Arrays.stream(args).toList().subList(1,args.length));
                instance.getConfig().set("settings.message",message);
                commandSender.sendMessage(String.format("World border expand message is now §b§l\"%s\"§r", message));
                break;

            case "sound":
                if(selects.get(args[1].toLowerCase()) == null) {
                    commandSender.sendMessage(String.format("Mode §b§l\"%s\"§r is not supported.", args[1]));
                    return false;
                }
                boolean mode = selects.get(args[1].toLowerCase());
                instance.getConfig().set("settings.sound",mode);
                commandSender.sendMessage(String.format("World border makes a sound when expanding: §b§l%b§r", mode));
                break;
        }
        return true;
    }
}
