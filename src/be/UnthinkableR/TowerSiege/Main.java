package be.UnthinkableR.TowerSiege;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitScheduler;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Score;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.ScoreboardManager;

public class Main extends JavaPlugin implements Listener{
static ArrayList<String> Count = new ArrayList<String>();
static ArrayList<String> Time = new ArrayList<String>();
static ArrayList<String> Min = new ArrayList<String>();
static ArrayList<Player> Bar = new ArrayList<Player>();
static ArrayList<Player> Def = new ArrayList<Player>();
static ArrayList<String> Done = new ArrayList<String>();
static Collection<Player> Game1 = new ArrayList<Player>();

	
	public void onEnable(){
		for(Player players : Bukkit.getOnlinePlayers()){
		Game1.add(players);
		}
		StartGame(Game1, 2);
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	public void onDisable(){
		
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args)
	  {  
	    Player player = (Player)sender;
	    // Start Code
	    // End Code
		return false;
	  }
	
	private static final List<String> frames = Arrays.asList(ChatColor.GOLD + String.valueOf(ChatColor.BOLD) + "Development",//
            ChatColor.GREEN + String.valueOf(ChatColor.BOLD) + "Development", 
            ChatColor.RED + String.valueOf(ChatColor.BOLD) + "Development");

    private static final Map<String, Integer> taskIds = new HashMap<>();

  

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Scoreboard newScoreboard = getServer().getScoreboardManager().getNewScoreboard();
        e.getPlayer().setScoreboard(newScoreboard);
        final Objective mainObjective = newScoreboard.registerNewObjective("main", "dummy");
        mainObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
        mainObjective.setDisplayName(frames.get(0));
        mainObjective.getScore(ChatColor.GOLD + "Coins: ").setScore(420);
        taskIds.put(e.getPlayer().getName(), new BukkitRunnable() {
            int frame = 0;

            public void run() {
                frame++;
                if (frame == frames.size()) {
                    frame = 0;
                }
                mainObjective.setDisplayName(frames.get(frame));
            }
        }.runTaskTimer(this, 0L, 10L).getTaskId());
    }

    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Integer id = taskIds.remove(e.getPlayer().getName());
        if (id != null) {
            getServer().getScheduler().cancelTask(id);
        }
    }

	
	public void StartGame(Collection<Player> players, Integer size){
		for(Player p : players){
			this.StartTheGame(p, size);
		}
	}
	
	
	
	public String Format(String text){
		return text.replaceAll("&", "§");
	}
	
}
