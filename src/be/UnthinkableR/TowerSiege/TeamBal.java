package be.UnthinkableR.TowerSiege;

import java.util.Collection;

import org.bukkit.entity.Player;

public class TeamBal {

	public static void Balance(Collection<Player> players){
    	new Team("Barbarians");
    	new Team("Defenders");
    	
    	int i = 1;
    	for(Player p : players){
    		if(i > Team.getAllTeams().size())
    			i = 0;
    		Team.getTeam(Team.getAllTeams().get(i).getName()).add(p);
    	}
	}
	
}
