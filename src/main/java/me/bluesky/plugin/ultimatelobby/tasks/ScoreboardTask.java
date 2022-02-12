package me.bluesky.plugin.ultimatelobby.tasks;

import me.bluesky.plugin.ultimatelobby.utils.ColorUtils;
import me.bluesky.plugin.ultimatelobby.utils.ConfigUtils;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scoreboard.*;

import java.util.ArrayList;
import java.util.List;

public class ScoreboardTask extends BukkitRunnable {

    String title = ConfigUtils.getScoreboard().getString("Scoreboard.Board.Title");
    List<String> infos = ConfigUtils.getScoreboard().getStringList("Scoreboard.Board.Info");

    ScoreboardManager scoreboardManager = Bukkit.getScoreboardManager();
    Scoreboard scoreboard = scoreboardManager.getNewScoreboard();
    Objective objective = scoreboard.registerNewObjective("Lobby", "Dummy");

    private List<String> lastStr = new ArrayList<>();

    @Override
    public void run() {
        if (ConfigUtils.getScoreboard().getBoolean("Scoreboard.Switch")) {
            if (Bukkit.getOnlinePlayers().size() != 0) {
                for (Player players : Bukkit.getOnlinePlayers()) {
                    title = PlaceholderAPI.setPlaceholders(players, title);
                    objective.setDisplayName(ColorUtils.tMC(title));
                    objective.setDisplaySlot(DisplaySlot.SIDEBAR);
                    Score score;
                    for (String str : lastStr) {
                        scoreboard.resetScores(str);
                    }
                    for (int i = 0; i < infos.size(); i++) {
                        String info = infos.get(i);
                        info = PlaceholderAPI.setPlaceholders(players, info);
                        lastStr.add(info);
                        score = objective.getScore(ColorUtils.tMC(info));
                        score.setScore(infos.size() - i);
                    }

                    players.setScoreboard(scoreboard);
                }
            }
        }
    }
}
