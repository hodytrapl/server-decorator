package hodytrapl.serverDecorator;

import hodytrapl.serverDecorator.BelowName.BelowName;
import hodytrapl.serverDecorator.CustomBar.CustomTab;
import hodytrapl.serverDecorator.JoinServer.JoinServer;
import hodytrapl.serverDecorator.SideBar.SideBar;
import hodytrapl.serverDecorator.chatFormatter.ChatFormatter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerDecorator extends JavaPlugin {

    private Debugger debugger;
    private SideBar sideBar;

    @Override
    public void onEnable() {

        this.debugger = new Debugger(this);
        debugger.sendPluginLoadMessage();

        this.sideBar = new SideBar(this);

        getServer().getPluginManager().registerEvents(new ChatFormatter(this), this);
        getServer().getPluginManager().registerEvents(new CustomTab(this), this);
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