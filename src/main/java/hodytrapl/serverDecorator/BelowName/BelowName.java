package hodytrapl.serverDecorator.BelowName;

import hodytrapl.serverDecorator.ServerDecorator;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class BelowName implements Listener {

    private final ServerDecorator plugin;
    private Scoreboard belowName;
    private Scoreboard emptyScoreboard;
    public BelowName(ServerDecorator plugin){
        this.plugin = plugin;
        ScoreboardManager manager = Bukkit.getScoreboardManager();
        belowName = manager.getNewScoreboard();
        emptyScoreboard = manager.getNewScoreboard();

        if (belowName.getObjective("health") != null)
            belowName.getObjective("health").unregister();
        if (belowName.getObjective("hp") != null)
            belowName.getObjective("hp").unregister();

        Objective obj = belowName.registerNewObjective(
                "hp",
                "dummy",
                "§c❤"
        );

        obj.setDisplaySlot(DisplaySlot.BELOW_NAME);


        new BukkitRunnable() {
            @Override
            public void run() {
                for (Player player : Bukkit.getOnlinePlayers()) {
                    updateHealth(player);
                }
            }
        }.runTaskTimer(plugin, 0L, 10L);
    }

    private void updateHealth(Player player) {

        int hp = (int) Math.ceil(player.getHealth());

        Objective obj = belowName.getObjective("hp");
        if (obj != null) {
            obj.getScore(player.getName()).setScore(hp);
        }

        player.setScoreboard(belowName);
    }

}
