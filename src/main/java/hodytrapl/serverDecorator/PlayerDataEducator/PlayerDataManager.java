package hodytrapl.serverDecorator.PlayerDataEducator;

import hodytrapl.serverDecorator.RoleDataEducator.RoleDataConfig;
import hodytrapl.serverDecorator.RoleDataEducator.RoleFormatData;
import hodytrapl.serverDecorator.ServerDecorator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import net.kyori.adventure.text.format.NamedTextColor;

import java.io.File;
import java.io.IOException;
import java.util.UUID;
import java.util.logging.Level;

public class PlayerDataManager {
    private final ServerDecorator plugin;
    private File dataFile;
    private FileConfiguration dataConfig;
    private final RoleDataConfig roleDataConfig;

    public PlayerDataManager(ServerDecorator plugin) {
        this.plugin = plugin;
        this.roleDataConfig = new RoleDataConfig(plugin);
        setupDataFile();
    }

    public void setupDataFile(){
        dataFile = new File(plugin.getDataFolder(), "roledata.yml");
        if (!dataFile.exists()) {
            try {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
                //setDefaultPlayers();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not create roledata.yml", e);
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }
}