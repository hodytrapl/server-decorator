package hodytrapl.serverDecorator.CustomBar;

import hodytrapl.serverDecorator.Misc.Misc;
import hodytrapl.serverDecorator.ServerDecorator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;


public class CustomTab implements Listener {
    private final ServerDecorator plugin;


    public CustomTab(ServerDecorator plugin){
        this.plugin = plugin;

        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    UpdaterTab(player);
                }
            }
        }.runTaskTimer(plugin, 0L, 1200L);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UpdaterTab(player);
            }
        });
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event) {
        Bukkit.getScheduler().runTask(plugin, () -> {
            for (Player player : Bukkit.getOnlinePlayers()) {
                UpdaterTab(player);
            }
        });
    }



    public void UpdaterTab(Player player) {
        final String SERVERNAME = "Create SMP";
        final String IPSERVER = "play.serverip.net";
        int onlineCount = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        int[] tps = getTPS();

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


        Component socialLink = Component.empty()
                .append(Component.text("SocialWeb: ", NamedTextColor.GRAY))
                .append(Component.text("Link", NamedTextColor.GRAY))
                .append(Component.text(" │ ", NamedTextColor.DARK_GRAY))
                .append(Component.text("WebSite: ", NamedTextColor.GRAY))
                .append(Component.text("Link", NamedTextColor.GRAY));


        Component serverStats = Component.empty()
                .append(Component.text("Игроков онлайн: ", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(Misc.getStatusColor(onlineCount, (int)(maxPlayers*0.25), (int)(maxPlayers*0.75)) + onlineCount))
                .append(Component.text(Misc.getStatusColor(onlineCount, (int)(maxPlayers*0.25), (int)(maxPlayers*0.75)) + "/"))
                .append(Component.text(Misc.getStatusColor(onlineCount, (int)(maxPlayers*0.25), (int)(maxPlayers*0.75)) + maxPlayers))
                .append(Component.newline())
                .append(Component.text("TPS: ", NamedTextColor.LIGHT_PURPLE))
                .append(Component.text(Misc.getStatusColor(tps[0], 5, 15) + tps[0]));

        Component footer = Component.empty()
                .append(socialLink)
                .append(Component.newline())
                .append(serverStats);

        player.sendPlayerListHeaderAndFooter(header, footer);
    }


    public int[] getTPS() {
        double[] tpsDoub = Bukkit.getTPS();
        int[] tpsInt = new int[tpsDoub.length];

        for(int step=0; step<tpsDoub.length;step++){
            int tps= (int) tpsDoub[step];
            tpsInt[step]= tps;
        }
        return tpsInt;
    }
}
