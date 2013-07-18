package generic.fps.ranking.util;

import generic.fps.ranking.common.Player;

import java.util.Comparator;


public class PlayerKillRankingComparator implements Comparator<Player> {

	public int compare(Player o1, Player o2) {
		if(o1 == null && o2 == null){
			return 0;
		}
		else if(o1 == null){
			return -1;
		}else if(o2 == null){
			return 1;
		}else {
            if(o1.getCurrentMatchKillCount() < o2.getCurrentMatchKillCount()){
				return 1;
			}  
			if(o1.getCurrentMatchKillCount() > o2.getCurrentMatchKillCount()){
				return -1;
			}  
		}				
		return 0;
	}

}
