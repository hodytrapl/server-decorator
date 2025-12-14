package hodytrapl.serverDecorator;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.event.ClickEvent;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

public class Debugger {

    private final JavaPlugin plugin;
    private boolean enabled;
    private String prefix;

    public Debugger(JavaPlugin plugin) {
        this.plugin = plugin;
        this.enabled = true; // По умолчанию включен
        this.prefix = "§b[DEBUG] §r";
    }

    public void sendPluginLoadMessage() {
        if (!enabled) return;

        Component tellrawMessage = Component.text()
                .append(Component.text("🎯 ", NamedTextColor.GOLD))
                .append(Component.text("Плагин ", NamedTextColor.GREEN))
                .append(Component.text("ServerDecorator", NamedTextColor.AQUA, TextDecoration.BOLD))
                .append(Component.text(" успешно загружен!", NamedTextColor.GREEN))
                .append(Component.newline())
                .append(Component.text("➤ Нажми сюда", NamedTextColor.YELLOW)
                        .clickEvent(ClickEvent.runCommand("/help"))
                        .hoverEvent(Component.text("Получить помощь", NamedTextColor.GRAY)))
                .build();

        // Отправляем всем онлайн игрокам
        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(tellrawMessage);
        }
    }

    public void sendDebugMessage(String msg,NamedTextColor color){
        if (!enabled) return;

        Component tellrawMessage = Component.text()
                .append(Component.text(msg, color))
                .build();

        for (Player player : Bukkit.getOnlinePlayers()) {
            player.sendMessage(tellrawMessage);
        }
    }


}
