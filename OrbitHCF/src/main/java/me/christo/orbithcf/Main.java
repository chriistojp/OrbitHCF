package me.christo.orbithcf;

import me.christo.orbithcf.Commands.BardGiveCommand;
import me.christo.orbithcf.Commands.SwitcherGiveCommand;
import me.christo.orbithcf.Listeners.BardListener;
import me.christo.orbithcf.Listeners.SwitcherListener;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandExecutor;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.util.HashMap;
import java.util.UUID;

public final class Main extends JavaPlugin {


    private static Main instance;

    @Override
    public void onEnable() {

        File file = new File(getDataFolder(), "config.yml");
        if(!file.exists())
            saveDefaultConfig();

        this.getServer().getPluginManager().registerEvents(new BardListener(), this);
        this.getServer().getPluginManager().registerEvents(new SwitcherListener(), this);
        new BardGiveCommand(this);
        new SwitcherGiveCommand(this);

        instance = this;

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public static Main getInstance() {
        return instance;
    }
    public HashMap<Player, UUID> getSnowHash() {
        return snowHash;
    }

    HashMap<Player, UUID> snowHash = new HashMap<Player, UUID>();

}
