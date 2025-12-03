package hodytrapl.serverDecorator.chatFormatter;

import hodytrapl.serverDecorator.Debugger;
import hodytrapl.serverDecorator.ServerDecorator;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextColor;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatFormatter implements Listener {

    private final ServerDecorator plugin;
    private final Debugger debugger;
    public ChatFormatter(ServerDecorator plugin) {
        this.plugin = plugin;
        this.debugger = plugin.getDebugger();
    }
    @EventHandler
    public void onChat(AsyncChatEvent event){
        event.renderer((source,SourceDisplayName,message,viewer)->
                Component.text("あ", NamedTextColor.WHITE)
                .append(Component.text("[", NamedTextColor.DARK_PURPLE))
                .append(SourceDisplayName.color(NamedTextColor.LIGHT_PURPLE))
                .append(Component.text("]", NamedTextColor.DARK_PURPLE))
                .append(Component.text(": ", NamedTextColor.WHITE))
                .append(message.color(NamedTextColor.WHITE)));
    }

}