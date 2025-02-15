package site.frii.worldborder.Listeners;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;
import site.frii.worldborder.WorldborderManager;

public class AchievementListener implements Listener {
    WorldborderManager instance;

    private WorldborderManager getMainInstance() {
        if(instance == null) {
            instance = WorldborderManager.getInstance();
        }
        return instance;
    }

    @EventHandler
    public void Achievement(PlayerAdvancementDoneEvent event) {
        instance = getMainInstance();

        // discards new recipes so the world border doesn't grow exponentially
        if(event.getAdvancement().getKey().toString().contains("minecraft:recipes")) {
            return;
        }

        String name = event.getAdvancement().getKey().toString();

        // If the achievement has already been achieved by someone, it is ignored.
        if(instance.getConfig().getBoolean(name)) {
            return;
        }

        int newSize = instance.getConfig().getInt("size") + 1;
        double increaseAmount = instance.getConfig().getDouble("amount");

        if(increaseAmount == 0.0) {
            Bukkit.broadcastMessage("Please specify how many blocks an advancement expands the world border with §l/amount§r. Defaulting to 1...");
            increaseAmount = 1.0;
        }

        instance.getConfig().set(name,true);
        instance.getConfig().set("size", newSize);
        instance.saveConfig();

        try {
            World world = Bukkit.getWorld("world");
            world.getWorldBorder().setSize(newSize*increaseAmount);
            for (Player player : Bukkit.getOnlinePlayers())
            {
                if(instance.getConfig().getBoolean("settings.sound")) {
                    player.playSound(player.getLocation(), Sound.BLOCK_NOTE_BLOCK_BANJO, 1.0f, 1.0f);
                }

                String message;
                message = instance.getConfig().getString("settings.message").replace("{SIZE}",""+newSize*increaseAmount);
                player.sendMessage(message);
            }
        } catch (NullPointerException npe) {
            Bukkit.broadcastMessage("Failed to set world border. Reason: NullPointerException. new size: " + newSize);
        } catch(Exception exception) {
            Bukkit.broadcastMessage(String.format("Failed to set world border. Reason: Unhandled error (%s). new size: %s",exception.toString(),newSize));
        }

    }
}