package hodytrapl.serverDecorator.Misc.fileManager;

import hodytrapl.serverDecorator.ServerDecorator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class ConfigManager {
    private final ServerDecorator plugin;
    private FileConfiguration config;
    private File configFile;

    public ConfigManager(ServerDecorator plugin){
        this.plugin = plugin;
        setupConfig();
    }

    private void setupConfig(){
        this.configFile = new File(plugin.getDataFolder(), "config.yml");

        if(!configFile.exists()){
            plugin.getDataFolder().mkdirs();
            createDefaultConfig();
        }

        config = YamlConfiguration.loadConfiguration(configFile);
    }

    private void createDefaultConfig(){
        try {
            config = new YamlConfiguration();

            // Server section
            config.set("config.server.server_name", "ServerName");
            config.set("config.server.server_title_color", "§6");
            config.set("config.server.server_title_font", "§l§o");
            config.set("config.server.ip_address", "IpAddress");

            // Theme section
            config.set("config.theme.main_color", "§d");
            config.set("config.theme.sub_color", "§5");

            // Status sub-section
            config.set("config.theme.status.terrible_color", "§c");
            config.set("config.theme.status.warning_color", "§e");
            config.set("config.theme.status.good_color", "§a");
            config.set("config.theme.status.unimportant_color", "§7");

            // Chat sub-section
            config.set("config.theme.chat.message_color", "§f");

            config.save(configFile);
            plugin.getLogger().info("Создан конфигурационный файл по умолчанию");
        } catch (IOException e) {
            plugin.getLogger().severe("Не удалось создать конфигурационный файл: " + e.getMessage());
        }
    }

    public void loadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public void saveConfig() {
        try {
            config.save(configFile);
        } catch (IOException e) {
            plugin.getLogger().severe("Не удалось сохранить конфиг: " + e.getMessage());
        }
    }

    public void reloadConfig() {
        config = YamlConfiguration.loadConfiguration(configFile);
    }

    public FileConfiguration getConfig() {
        return config;
    }

    // ========== Server section ==========

    public String getServerName() {
        return config.getString("config.server.server_name", "ServerName");
    }

    public String getServerTitleColor() {
        return config.getString("config.server.server_title_color", "§6");
    }

    public String getServerTitleFont() {
        return config.getString("config.server.server_title_font", "§l§o");
    }

    public String getIpAddress() {
        return config.getString("config.server.ip_address", "IpAddress");
    }

    public void setServerName(String name) {
        config.set("config.server.server_name", name);
        saveConfig();
    }

    public void setServerTitleColor(String color) {
        config.set("config.server.server_title_color", color);
        saveConfig();
    }

    public void setServerTitleFont(String font) {
        config.set("config.server.server_title_font", font);
        saveConfig();
    }

    public void setIpAddress(String ip) {
        config.set("config.server.ip_address", ip);
        saveConfig();
    }

    // ========== Theme section ==========

    public String getMainColor() {
        return config.getString("config.theme.main_color", "§6");
    }

    public String getSubColor() {
        return config.getString("config.theme.sub_color", "§7");
    }

    public void setMainColor(String color) {
        config.set("config.theme.main_color", color);
        saveConfig();
    }

    public void setSubColor(String color) {
        config.set("config.theme.sub_color", color);
        saveConfig();
    }

    // ========== Status sub-section ==========

    public String getTerribleColor() {
        return config.getString("config.theme.status.terrible_color", "§c");
    }

    public String getWarningColor() {
        return config.getString("config.theme.status.warning_color", "§e");
    }

    public String getGoodColor() {
        return config.getString("config.theme.status.good_color", "§a");
    }

    public String getUnimportantColor() {
        return config.getString("config.theme.status.unimportant_color", "§8");
    }

    public void setTerribleColor(String color) {
        config.set("config.theme.status.terrible_color", color);
        saveConfig();
    }

    public void setWarningColor(String color) {
        config.set("config.theme.status.warning_color", color);
        saveConfig();
    }

    public void setGoodColor(String color) {
        config.set("config.theme.status.good_color", color);
        saveConfig();
    }

    public void setUnimportantColor(String color) {
        config.set("config.theme.status.unimportant_color", color);
        saveConfig();
    }

    // ========== Chat sub-section ==========

    public String getChatMessageColor() {
        return config.getString("config.theme.chat.message_color", "§f");
    }

    public void setChatMessageColor(String color) {
        config.set("config.theme.chat.message_color", color);
        saveConfig();
    }
}