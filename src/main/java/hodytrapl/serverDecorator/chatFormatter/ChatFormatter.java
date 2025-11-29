package hodytrapl.serverDecorator.chatFormatter;

import hodytrapl.serverDecorator.Debugger;
import hodytrapl.serverDecorator.PlayerDataEducator.PlayerFormatData;
import hodytrapl.serverDecorator.ServerDecorator;
import hodytrapl.serverDecorator.PlayerDataEducator.PlayerDataManager;
import io.papermc.paper.chat.ChatRenderer;
import io.papermc.paper.event.player.AsyncChatEvent;
import net.kyori.adventure.audience.Audience;
import net.kyori.adventure.text.Component;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class ChatFormatter {

    private final ServerDecorator plugin;
    private final Debugger debugger;
    private final PlayerDataManager dataManager;
    private final Map<UUID, PlayerFormatData> playerDataCache;

    public ChatFormatter(ServerDecorator plugin) {
        this.plugin = plugin;
        this.debugger = plugin.getDebugger();
        this.dataManager = new PlayerDataManager(plugin);
        this.playerDataCache = new HashMap<>();
    }

}