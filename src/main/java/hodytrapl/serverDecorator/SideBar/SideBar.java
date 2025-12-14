package hodytrapl.serverDecorator.SideBar;

import com.sun.security.jgss.InquireType;
import hodytrapl.serverDecorator.Misc.Misc;
import hodytrapl.serverDecorator.ServerDecorator;
import net.kyori.adventure.text.Component;
import net.kyori.adventure.text.format.NamedTextColor;
import net.kyori.adventure.text.format.TextDecoration;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Statistic;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SideBar implements Listener {
    /* TODO: хочу чтобы бы показывалались строки
        имя сервера
        дата и время сейчас
        разделитель
        ранк
        разделитель
        статистика(убийства,смертей.пинг,время игры)
    */
    final String SERVERNAME = "Create SMP";//будем потом брать с конфигов плагина

    private final ServerDecorator plugin;
    private final Map<UUID, Scoreboard> playerScoreboards;// храним sidebar для каждого игрока
    private final Map<UUID, Objective> playerObjectives;
    private final Map<UUID, Set<String>> playerEntries;

    private String lastDateTime = "";
    private long lastUpdateTime = 0;

    private static final long UPDATE_INTERVAL = 20L; // 1 секунда (20 тиков)
    private static final int MAX_LINES = 15;
    private static final String[] COLOR_CODES = new String[] {
            "§0", "§1", "§2", "§3", "§4", "§5", "§6", "§7",
            "§8", "§9", "§a", "§b", "§c", "§d", "§e", "§f"
    };


    public SideBar(ServerDecorator plugin){
        this.plugin = plugin;
        this.playerEntries = new HashMap<>();
        this.playerObjectives = new HashMap<>();
        this.playerScoreboards = new HashMap<>();


        DefaultSideBar();

        Bukkit.getPluginManager().registerEvents(this, plugin);

        //обновление
        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    UpdateAllSideBars();
                }
            }
        }.runTaskTimer(plugin, 5L, UPDATE_INTERVAL);
    }

    private void createScoreboard(Player player){
        Scoreboard scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();

        String objectiveName = "sidebar_" + player.getUniqueId().toString().substring(0, 8);
        Objective objective = scoreboard.registerNewObjective(
                objectiveName,
                "dummy",
                Component.text(SERVERNAME)
                        .color(NamedTextColor.GOLD)
                        .decoration(TextDecoration.BOLD, true)
        );

        objective.setDisplaySlot(DisplaySlot.SIDEBAR);

        playerScoreboards.put(player.getUniqueId(), scoreboard);
        playerObjectives.put(player.getUniqueId(), objective);
        playerEntries.put(player.getUniqueId(), new HashSet<>());
        player.setScoreboard(scoreboard);
    }

    public void UpdateAllSideBars(){

        for (Player player : Bukkit.getOnlinePlayers()) {
            UpdateSidebar(player);
        }
    }
    public void UpdateSidebar(Player player) {

    }
    private String FormatterPlayTime(long time){
        long second =time/20;
        long days = second / 86400;
        long hours = (second % 86400)/3600;
        long minutes = (second % 3600) / 60;
        String result = String.format("%02d:%02d:%02d",days,hours,minutes);
        return  result;
    }

    //создаем в случае ошибки
    private void DefaultSideBar(){

    }
}
