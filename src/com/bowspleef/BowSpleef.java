package com.bowspleef;

import com.bowspleef.command.CommandProcessor;
import com.bowspleef.command.Commands;
import com.bowspleef.command.HelpCommand;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class BowSpleef extends JavaPlugin {

    public static final String PATH = "plugins/BowSpleef";
    public static File arenaFile = new File(PATH + "/arena.yml");
    public static FileConfiguration arenaFileConfiguration = YamlConfiguration.loadConfiguration(arenaFile);

    @Override
    public void onEnable() {

        saveConfigurationFiles();
        loadConfigurationFiles();

        getCommand("bs").setExecutor(new CommandProcessor());
        Commands.getCommandList().add(new HelpCommand());

        getLogger().info("BowSpleef is enabled.");
    }

    @Override
    public void onDisable() {

        saveConfigurationFiles();

        getLogger().info("BowSpleef is disabled.");
    }

    public static void setupConfigurationFiles() {
        if (!arenaFile.exists()) {
            try {
                arenaFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static void saveConfigurationFiles() {
        try {
            arenaFileConfiguration.save(arenaFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loadConfigurationFiles() {
        try {

            if (!arenaFile.exists()) {
                arenaFile.createNewFile();
            }

            arenaFileConfiguration.load(arenaFile);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
