package be.UnthinkableR.TowerSiege;

import java.util.ArrayList;
import java.util.Collection;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scoreboard.Team;

public class TeamBal {

	public static void Balance(Collection<Player> players, Team bar, Team def){
    	ArrayList<String> Done = new ArrayList<String>();
    	ArrayList<Player> Name = new ArrayList<Player>();
    	Name.addAll(players);
			for(int i = 0; i < players.size() + 1; i++){
				if(Done.size() == 1){
					bar.addPlayer(Name.get(i-1));
				}
				if(Done.size() == 0){
					def.addPlayer(Name.get(i-1));
				}
				if(i == players.size() / 2){
					Done.add("1");
				}
			}
	}
	
}
