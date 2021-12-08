package org.goldenpositioning.classes;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.goldenpositioning.classes.COMMANDS.helposition_COMMAND;
import org.goldenpositioning.classes.COMMANDS.null_COMMAND;
import org.goldenpositioning.classes.LISTENER.PlayerJoin_LISTENER;
import org.goldenpositioning.classes.LISTENER.PlayerLeave_LISTENER;

import java.io.File;
import java.io.IOException;

public final class MAIN extends JavaPlugin {


    public static MAIN main;

    @Override
    public void onEnable() {
        main = this;
        Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rLoading Plugin...");

        // Loading Commands
        try {
            registerCommands();
            Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rCommands successfully loaded...");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rCould not Load Commands...");
            e.printStackTrace();
        }

        // Loading Listeners
        try {
            registerListeners();
            Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rEvents successfully loaded...");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rCould not Load Events...");
            e.printStackTrace();
        }

        // Loading Config File
        try {
            registerConfig();
            Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rConfig successfully loaded...");
        } catch (Exception e) {
            Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rCould not Load Config...");
            e.printStackTrace();
        }

        // Plugin Successful
        Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rPlugin Successfully loaded...");
    }

    public void registerConfig() {
        File configfile = new File(MAIN.getPlugin().getDataFolder(), "config.yml");
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(configfile);

        if(!configfile.exists()) {

            cfg.set("Use Public Positions", true);
            cfg.set("Use Private Positions", true);

            try {
                cfg.save(configfile);
                Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rConfig successfully saved...");
            } catch (IOException e) {
                Bukkit.getConsoleSender().sendMessage("§6" + getPlugin().getName() + " §8> " + "§rCould not save Config...");
                e.printStackTrace();
            }
        }
    }

    private void registerListeners() {
        PluginManager pm = Bukkit.getPluginManager();
        pm.registerEvents(new PlayerJoin_LISTENER(), this);
        pm.registerEvents(new PlayerLeave_LISTENER(), this);
    }

    private void registerCommands() {
        getCommand("position").setExecutor(new null_COMMAND());
        getCommand("helpposition").setExecutor(new helposition_COMMAND());
    }

    public static MAIN getPlugin() {
        return main;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
