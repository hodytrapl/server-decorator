package hodytrapl.serverDecorator.chatFormatter;

import hodytrapl.serverDecorator.Debugger;
import hodytrapl.serverDecorator.Misc.Misc;
import hodytrapl.serverDecorator.Misc.fileManager.ConfigManager;
import hodytrapl.serverDecorator.ServerDecorator;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;


public class ChatFormatter implements Listener {

    private final ServerDecorator plugin;
    private final Debugger debugger;
    private final ConfigManager confM;

    public ChatFormatter(ServerDecorator plugin, ConfigManager confM) {
        this.plugin = plugin;
        this.debugger = plugin.getDebugger();
        this.confM = confM;
    }



    @EventHandler
    public void onChat(AsyncChatEvent event) {
        event.renderer((source, sourceDisplayName, message, viewer) -> {
            // Получаем цвета из конфига
            String subColor = confM.getSubColor(); // Цвет скобок
            String mainColor = confM.getMainColor(); // Основной цвет для ника

            // Создаем компонент с цветным ником
            Component coloredName = sourceDisplayName
                    .color(TextColor.fromHexString(Misc.convertMinecraftColorToHex(mainColor)));

            // Создаем цветное сообщение
            Component coloredMessage = message
                    .color(TextColor.fromHexString(Misc.convertMinecraftColorToHex(confM.getChatMessageColor())));

            // Собираем итоговое сообщение
            return Component.text("あ", TextColor.fromHexString("#FFFFFF")) // Белый символ
                    .append(Component.text(subColor + "["))
                    .append(coloredName)
                    .append(Component.text(subColor + "]"))
                    .append(Component.text(confM.getChatMessageColor() + ": "))
                    .append(coloredMessage);
        });
    }

}