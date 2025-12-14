package hodytrapl.serverDecorator.JoinServer;

import net.kyori.adventure.text.Component;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class JoinServer implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        event.joinMessage(null);
        Component joinMsg = Component.empty()
                .append(Component.text("§f[§a+§f] "))
                .append(Component.text(event.getPlayer().getName()));

        event.joinMessage(joinMsg);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        event.quitMessage(null);
        Component leaveMsg = Component.empty()
                .append(Component.text("§f[§c-§f] "))
                .append(Component.text(event.getPlayer().getName()));

        event.quitMessage(leaveMsg);
    }
}
