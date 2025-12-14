package hodytrapl.serverDecorator.CustomBar;

import hodytrapl.serverDecorator.Misc.Misc;
import hodytrapl.serverDecorator.Misc.fileManager.ConfigManager;
import hodytrapl.serverDecorator.ServerDecorator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class CustomTab implements Listener {
    private final ServerDecorator plugin;
    private final ConfigManager confM;
    Map<String, Set<String>> tabEntries;

    public CustomTab(ServerDecorator plugin, ConfigManager confM){
        this.plugin = plugin;
        this.confM = confM;
        this.tabEntries = new HashMap<>();

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
        final String SERVERNAME = confM.getServerName();
        final String SERVERTITLECOLOR = confM.getServerTitleColor();
        final String SERVERTITLEFONT = confM.getServerTitleFont();
        final String IPSERVER = confM.getIpAddress();
        int onlineCount = Bukkit.getOnlinePlayers().size();
        int maxPlayers = Bukkit.getMaxPlayers();
        int[] tps = getTPS();

        Component headerdd= Component.empty();
        Component footerdd= Component.empty();

        Component OBFUSCATEDtext =
                Component.text( "f")
                    .decorate(TextDecoration.OBFUSCATED);

        Component DecorServerName =
                Component.text(" ")
                    .append(Component.text(SERVERTITLECOLOR+SERVERTITLEFONT+SERVERNAME))
                    .append(Component.text(" "));

        Component DecorIPserver =
                Component.text(confM.getUnimportantColor()+"│ ")
                    .append(Component.text( confM.getUnimportantColor()+IPSERVER))
                    .append(Component.text(confM.getUnimportantColor()+" │"));

        Component header =
                Component.empty()
                        .append(OBFUSCATEDtext)
                        .append(DecorServerName)
                        .append(OBFUSCATEDtext)
                        .append(Component.newline())
                        .append(DecorIPserver);


        Component socialLink = Component.empty()
                .append(Component.text(confM.getUnimportantColor()+"SocialWeb: "))
                .append(Component.text(confM.getUnimportantColor()+"Link"))
                .append(Component.text(confM.getUnimportantColor()+" │ "))
                .append(Component.text(confM.getUnimportantColor()+"WebSite: "))
                .append(Component.text(confM.getUnimportantColor()+"Link"));


        Component serverStats = Component.empty()
                .append(Component.text(confM.getMainColor()+"Игроков онлайн: "))
                .append(Component.text(Misc.getStatusColor(confM,onlineCount, (int)(maxPlayers*0.25), (int)(maxPlayers*0.75)) + onlineCount))
                .append(Component.text(Misc.getStatusColor(confM,onlineCount, (int)(maxPlayers*0.25), (int)(maxPlayers*0.75)) + "/"))
                .append(Component.text(Misc.getStatusColor(confM,onlineCount, (int)(maxPlayers*0.25), (int)(maxPlayers*0.75)) + maxPlayers))
                .append(Component.newline())
                .append(Component.text(confM.getMainColor()+"TPS: "))
                .append(Component.text(Misc.getStatusColor(confM,tps[0], 5, 15) + tps[0]));

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
