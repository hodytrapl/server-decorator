package hodytrapl.serverDecorator.CustomBar;

import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class CustomBar implements Listener {
    public int[] getTPS() {
        double[] tpsDoub = Bukkit.getTPS();
        int[] tpsInt = new int[tpsDoub.length];

        for(int step=0; step<tpsDoub.length;step++){
            int tps= (int) tpsDoub[step];
            tpsInt[step]= tps;
        }
        return tpsInt;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        final String SERVERNAME = "Create SMP";
        final String IPSERVER = "play.serverip.net";
        int onlineCount = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        int[] tps = getTPS();
        int ping = player.getPing();

        Component playerTabName = Component.text()
                .append(Component.text(player.getName(), NamedTextColor.WHITE))
                .append(Component.text(" [" + ping + "ms]", NamedTextColor.GREEN))
                .build();
        player.playerListName(playerTabName);


        Component OBFUSCATEDtext =
                Component.text( "f")
                    .decorate(TextDecoration.OBFUSCATED);

        Component DecorServerName =
                Component.text(" ")
                    .append(Component.text( SERVERNAME, NamedTextColor.GOLD))
                    .append(Component.text(" "));

        Component DecorIPserver =
                Component.text("│ ", NamedTextColor.GRAY)
                    .append(Component.text( IPSERVER, NamedTextColor.GRAY))
                    .append(Component.text(" │", NamedTextColor.GRAY));

        Component header =
                Component.empty()
                        .append(OBFUSCATEDtext)
                        .append(DecorServerName)
                        .append(OBFUSCATEDtext)
                        .append(Component.newline())
                        .append(DecorIPserver);

        Component componentRank = Component.empty()
                .append(Component.text("Rank: ", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text("None"));

        Component socialLink = Component.empty()
                .append(Component.text("SocialWeb: ", NamedTextColor.GRAY))
                .append(Component.text("Link", NamedTextColor.GRAY))
                .append(Component.text(" │ ", NamedTextColor.DARK_GRAY))
                .append(Component.text("WebSite: ", NamedTextColor.GRAY))
                .append(Component.text("Link", NamedTextColor.GRAY));


        Component serverStats = Component.empty()
                .append(Component.text("Игроков онлайн: ",NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(onlineCount, NamedTextColor.GREEN))
                .append(Component.text("/", NamedTextColor.GREEN))
                .append(Component.text(maxPlayers, NamedTextColor.GREEN))
                .append(Component.newline())
                .append(Component.text("TPS: ", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(Integer.toString(tps[0]), NamedTextColor.GREEN));

        Component footer = Component.empty()
                .append(componentRank)
                .append(Component.newline())
                .append(socialLink)
                .append(Component.newline())
                .append(serverStats);

        player.sendPlayerListHeaderAndFooter(header, footer);
    }
}
