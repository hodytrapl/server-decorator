package hodytrapl.serverDecorator;

import hodytrapl.serverDecorator.CommandList.RoleCommand;
import hodytrapl.serverDecorator.chatFormatter.ChatFormatter;
import org.bukkit.plugin.java.JavaPlugin;

public final class ServerDecorator extends JavaPlugin {

    private Debugger debugger;

    @Override
    public void onEnable() {
        // Создаем папку плагина если её нет
        if (!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }

        this.debugger = new Debugger(this);
        debugger.sendPluginLoadMessage();

        ChatFormatter chatFormatter = new ChatFormatter(this);
        //getServer().getPluginManager().registerEvents(chatFormatter, this);

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