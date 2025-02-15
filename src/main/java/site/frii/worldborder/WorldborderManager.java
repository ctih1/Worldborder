package site.frii.worldborder;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import site.frii.worldborder.Commands.Advancements;
import site.frii.worldborder.Commands.Multiplier;
import site.frii.worldborder.Commands.Settings;
import site.frii.worldborder.Commands.Sync;
import site.frii.worldborder.Listeners.AchievementListener;


public final class WorldborderManager extends JavaPlugin {
    static WorldborderManager instance;
    public Logger log = Bukkit.getLogger();

    public static WorldborderManager getInstance() {
        if(instance == null) {
            instance = new WorldborderManager();
        }
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        generateConfig();

        getServer().getPluginManager().registerEvents(new AchievementListener(), this);
        this.getCommand("multiplier").setExecutor(new Multiplier());
        this.getCommand("sync").setExecutor(new Sync());
        this.getCommand("advancements").setExecutor(new Advancements());
        this.getCommand("settings").setExecutor(new Settings());

        log.log(Level.INFO,"Loaded WorldBorderManager");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void generateConfig() {
        if(this.getConfig().getBoolean("settings.created")) {
            return;
        }
        log.info("Creating default config...");

        this.getConfig().set("settings.message","World border's size is now §b§l{SIZE} blocks§r");
        this.getConfig().set("settings.sound",true);
        this.getConfig().set("created",true);
    }
}
