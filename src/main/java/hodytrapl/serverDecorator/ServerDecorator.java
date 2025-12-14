package hodytrapl.serverDecorator;

import hodytrapl.serverDecorator.BelowName.BelowName;
import hodytrapl.serverDecorator.CustomBar.CustomTab;
import hodytrapl.serverDecorator.JoinServer.JoinServer;
import hodytrapl.serverDecorator.Misc.fileManager.ConfigManager;
import hodytrapl.serverDecorator.SideBar.SideBar;
import hodytrapl.serverDecorator.chatFormatter.ChatFormatter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerDecorator extends JavaPlugin {

    private Debugger debugger;
    private ConfigManager configManager;

    @Override
    public void onEnable() {
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }


        this.debugger = new Debugger(this);
        debugger.sendPluginLoadMessage();
        configManager = new ConfigManager(this);


        getServer().getPluginManager().registerEvents(new ChatFormatter(this,configManager), this);
        getServer().getPluginManager().registerEvents(new CustomTab(this,configManager), this);
        getServer().getPluginManager().registerEvents(new JoinServer(), this);
        getServer().getPluginManager().registerEvents(new BelowName(this), this);

        log("плагин Server decorator включен");
    }

    @Override
    public void onDisable() {
        log("плагин Server decorator выключен");
    }

    private void log(String msg){
        getLogger().info(msg);
    }

    public Debugger getDebugger() {
        return debugger;
    }
}