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
static Collection<Player> Game1 = new ArrayList<Player>();


	
	public void onEnable(){
		for(Player players : Bukkit.getOnlinePlayers()){
		Game1.add(players);
		}
		StartGame(Game1);
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
	
	private static final List<String> frames = Arrays.asList(String.valueOf(Format("&3&lNestMC")),
			String.valueOf(Format("&3&lNestMC")), String.valueOf(Format("&f&lN&3&lestMC")),
			String.valueOf(Format("&f&lNe&3&lstMC")), String.valueOf(Format("&f&lNes&3&ltMC")),
			String.valueOf(Format("&f&lNest&3&lMC")), String.valueOf(Format("&f&lNestM&3&lC")),
			String.valueOf(Format("&f&lNestMC")), String.valueOf(Format("&f&lNestMC")));

    private static final Map<String, Integer> taskIds = new HashMap<>();

  

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
       
    }

  
    
    public void StartGame(final Collection<Player> players){
    	Integer GameSize = players.size();
    		for(Player p : players){     		
    			Scoreboard newScoreboard = getServer().getScoreboardManager().getNewScoreboard();
    	        p.setScoreboard(newScoreboard);
    	        final Objective mainObjective = newScoreboard.registerNewObjective("game", "dummy");
    	        TeamBal.Balance(players);
    	        Integer c = (GameSize / 2);
    	        mainObjective.setDisplaySlot(DisplaySlot.SIDEBAR);
    	        mainObjective.setDisplayName(frames.get(0));
    	        mainObjective.getScore(Format("&e&lTime:")).setScore(7);
    	        mainObjective.getScore(Format("0:00")).setScore(6);    	       
    	        mainObjective.getScore(Format("&b&lDefenders")).setScore(5);
    	        mainObjective.getScore(Format("&3" + c)).setScore(4);
    	        mainObjective.getScore(Format("&c&lBarbarians")).setScore(3);
    	        mainObjective.getScore(Format("&4" + c)).setScore(2);
    	        mainObjective.getScore(ChatColor.RED.toString()).setScore(6);
    	        mainObjective.getScore(Format("--------------")).setScore(1);
    	        taskIds.put(p.getName(), new BukkitRunnable() {
    	            int frame = 0;
    	            public void run() {
    	                frame++;
    	                if (frame == frames.size()) {
    	                    frame = 0;
    	                }
    	                mainObjective.setDisplayName(frames.get(frame));
    	            }
    	        }.runTaskTimer(this, 100, 05).getTaskId());
    	}    	
    }
	
    @EventHandler
    public void onQuit(PlayerQuitEvent e) {
        Integer id = taskIds.remove(e.getPlayer().getName());
        if (id != null) {
            getServer().getScheduler().cancelTask(id);
        }
    }
	
	public static String Format(String text){
		return text.replaceAll("&", "§");
	}

	
}
