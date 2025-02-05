package site.frii.worldborder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import site.frii.worldborder.Commands.Amount;
import site.frii.worldborder.Commands.Sync;
import site.frii.worldborder.Listeners.AchievementListener;


public final class WorldborderManager extends JavaPlugin {
    static WorldborderManager instance;
    public Logger log = Bukkit.getLogger();

    public static WorldborderManager getInstance() {
        if(instance == null) {
            instance = new WorldborderManager();
        };
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        getServer().getPluginManager().registerEvents(new AchievementListener(), this);
        this.getCommand("amount").setExecutor(new Amount());
        this.getCommand("sync").setExecutor(new Sync());
        log.log(Level.INFO,"Loaded WorldBorderManager");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
