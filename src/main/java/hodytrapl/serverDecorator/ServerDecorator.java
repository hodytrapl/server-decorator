package hodytrapl.serverDecorator;

import hodytrapl.serverDecorator.CustomBar.CustomBar;
import hodytrapl.serverDecorator.chatFormatter.ChatFormatter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerDecorator extends JavaPlugin {

    private Debugger debugger;

    @Override
    public void onEnable() {

        this.debugger = new Debugger(this);
        debugger.sendPluginLoadMessage();

        getServer().getPluginManager().registerEvents(new ChatFormatter(this), this);
        getServer().getPluginManager().registerEvents(new CustomBar(), this);

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