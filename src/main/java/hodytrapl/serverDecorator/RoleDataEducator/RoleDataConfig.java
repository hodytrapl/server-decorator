package hodytrapl.serverDecorator.RoleDataEducator;

import hodytrapl.serverDecorator.ServerDecorator;
import net.kyori.adventure.text.format.NamedTextColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.logging.Level;

public class RoleDataConfig {
    private final ServerDecorator plugin;
    private File dataFile;
    private FileConfiguration dataConfig;

    public RoleDataConfig(ServerDecorator plugin){
        this.plugin = plugin;
        setupDataFile();
    }

    public void setupDataFile(){
        dataFile = new File(plugin.getDataFolder(), "roledata.yml");
        if (!dataFile.exists()) {
            try {
                dataFile.getParentFile().mkdirs();
                dataFile.createNewFile();
                createDefaultRoles();
            } catch (IOException e) {
                plugin.getLogger().log(Level.SEVERE, "Could not create roledata.yml", e);
            }
        }
        dataConfig = YamlConfiguration.loadConfiguration(dataFile);
    }

    private void createDefaultRoles() {
        // Дефолтные роли
        saveRoleDataConfig("default", "white", "yellow", "");
        saveRoleDataConfig("admin", "red", "red", "あ");
        saveRoleDataConfig("vip", "green", "gold", "★");
        saveRoleDataConfig("special", "light_purple", "red", "ぁ");
    }

    public void saveRoleDataConfig(String role, String color, String subcolor, String badge) {
        String path = "roles." + role;

        dataConfig.set(path + ".color", color);
        dataConfig.set(path + ".subcolor", subcolor);
        dataConfig.set(path + ".badge", badge);

        saveDataFile();
    }

    public RoleFormatData getRoleData(String role) {
        String path = "roles." + role;

        // Если роль не найдена, используем дефолтную
        if (!dataConfig.contains(path)) {
            plugin.getLogger().warning("Role '" + role + "' not found, using default");
            return getRoleData("default");
        }

        String colorStr = dataConfig.getString(path + ".color", "white");
        String subcolorStr = dataConfig.getString(path + ".subcolor", "yellow");
        String badge = dataConfig.getString(path + ".badge", "");

        NamedTextColor color = stringToColor(colorStr);
        NamedTextColor subcolor = stringToColor(subcolorStr);

        return new RoleFormatData(role, color, subcolor, badge, this);
    }

    private NamedTextColor stringToColor(String colorName) {
        if (colorName == null) return NamedTextColor.WHITE;

        // Прямое преобразование из строки в NamedTextColor
        switch (colorName.toLowerCase()) {
            case "black": return NamedTextColor.BLACK;
            case "dark_blue": return NamedTextColor.DARK_BLUE;
            case "dark_green": return NamedTextColor.DARK_GREEN;
            case "dark_aqua": return NamedTextColor.DARK_AQUA;
            case "dark_red": return NamedTextColor.DARK_RED;
            case "dark_purple": return NamedTextColor.DARK_PURPLE;
            case "gold": return NamedTextColor.GOLD;
            case "gray": return NamedTextColor.GRAY;
            case "dark_gray": return NamedTextColor.DARK_GRAY;
            case "blue": return NamedTextColor.BLUE;
            case "green": return NamedTextColor.GREEN;
            case "aqua": return NamedTextColor.AQUA;
            case "red": return NamedTextColor.RED;
            case "light_purple": return NamedTextColor.LIGHT_PURPLE;
            case "yellow": return NamedTextColor.YELLOW;
            case "white": return NamedTextColor.WHITE;
            default: return NamedTextColor.WHITE;
        }
    }
}