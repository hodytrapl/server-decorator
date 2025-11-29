package hodytrapl.serverDecorator;

import org.bukkit.plugin.java.JavaPlugin;

public final class ServerDecorator extends JavaPlugin {

    @Override
    public void onEnable() {
        log("плагин Server decorator включен");

    }

    @Override
    public void onDisable() {
        log("плагин Server decorator выключен");
    }

    public void log(String msg){
        getLogger().info(msg);
    }
}
