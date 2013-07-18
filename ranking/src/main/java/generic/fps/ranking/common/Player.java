package generic.fps.ranking.common;

import generic.fps.ranking.util.SortMapByComparator;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

public class Player {

	private static final Logger logger = Logger.getLogger(Player.class) ; 

	private String name;
	private String currentWeapon;
	private HashMap<String, Long> killsByWeapon = new HashMap<String, Long>();	
	private Long currentMatchKillCount =0l;
	private Long currentMatchDeathCount=0l ;
	private Long currentMatchKillStreak=0l ;
	private Long currentMatchHighestKillStreak=0l ;

	private Date killStreakStartTime = null;
	private List<Award> awards=new ArrayList<Award>() ;
	private Long currentMatchId ;
	
	
	public Player(String name, Weapons currentWeapon ) {
		super();
		this.name = name;
		this.currentWeapon = currentWeapon.getName();
	}
	
	public Player(Enemies enemy) {
		super();
		this.name = "<WORLD>";
		this.currentWeapon = enemy.getName();
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCurrentWeapon() {
		return currentWeapon;
	}
	public void setCurrentWeapon(String currentWeapon) {
		this.currentWeapon = currentWeapon;
	}
	public Long getCurrentMatchKillCount() {
		return currentMatchKillCount;
	}
	public void setCurrentMatchKillCount(Long currentMatchKillCount) {
		this.currentMatchKillCount = currentMatchKillCount;
	}
	public Long getCurrentMatchDeathCount() {
		return currentMatchDeathCount;
	}
	public void setCurrentMatchDeathCount(Long currentMatchDeathCount) {
		this.currentMatchDeathCount = currentMatchDeathCount;
	}
	
	
	
	public Long getCurrentMatchId() {
		return currentMatchId;
	}

	public void setCurrentMatchId(Long currentMatchId) {
		this.currentMatchId = currentMatchId;
	}
	
	public HashMap<String, Long> getKillsByWeapon() {
		return killsByWeapon;
	}

	public void setKillsByWeapon(HashMap<String, Long> killsByWeapon) {
		this.killsByWeapon = killsByWeapon;
	}

	public Long getCurrentMatchKillStreak() {
		return currentMatchKillStreak;
	}

	public void setCurrentMatchKillStreak(Long currentMatchKillStreak) {
		this.currentMatchKillStreak = currentMatchKillStreak;
	}

	public Date getKillStreakStartTime() {
		return killStreakStartTime;
	}

	public void setKillStreakStartTime(Date killStreakStartTime) {
		this.killStreakStartTime = killStreakStartTime;
	}

	public List<Award> getAwards() {
		return awards;
	}

	public void setAwards(List<Award> awards) {
		this.awards = awards;
	}
	
	
	
	public Long getCurrentMatchHighestKillStreak() {
		return currentMatchHighestKillStreak;
	}

	public void setCurrentMatchHighestKillStreak(Long currentMatchHighestKillStreak) {
		this.currentMatchHighestKillStreak = currentMatchHighestKillStreak;
	}

	public void addDeath(){
		this.currentMatchDeathCount++;
		this.currentMatchKillStreak=0l;
	}
	
	public Map<String, Long>  getPreferredWeapons(){
		Map<String, Long>  sortedMap =  SortMapByComparator.sortByComparator(killsByWeapon, false);
		return sortedMap;
	} 
	public void printPreferredWeapons(){
		Map<String, Long>  sortedMap  = getPreferredWeapons();
		logger.info("Preferred Weapons by "+this.getName());
		Set<String> keys = sortedMap.keySet();
		Integer position =1;
		for(String key :keys){
			Long kills = sortedMap.get(key);
			logger.info(position+". "+key +": "+kills);
			position++;
		}
	}
	
    public void addKill(){
    	Date currentTime = new Date();
		Long currentKillsWithWeapon = killsByWeapon.get(currentWeapon);
    	
    	this.currentMatchKillCount++;
		this.currentMatchKillStreak++;
		
		
		if(currentMatchKillStreak == 1){
			killStreakStartTime = new Date();
		}
		
		if(currentMatchKillStreak%5 == 0l ){
			if((currentTime.getTime()-killStreakStartTime.getTime()) < 300000 ){
			  Award award= new Award("An Quick Hands Job", "5 kills in less than 5 minutes",this.currentMatchId);
			  this.awards.add(award);
			  killStreakStartTime = new Date();
			}
			else{
			  killStreakStartTime = new Date();
			}
		}
		
		if(currentKillsWithWeapon == null){
			killsByWeapon.put(currentWeapon, 1l);
		}else{
			killsByWeapon.put(currentWeapon, ++currentKillsWithWeapon);
		}
		
		if(currentMatchHighestKillStreak <currentMatchKillStreak){
			currentMatchHighestKillStreak=currentMatchKillStreak;
		}
 		
	}
    
    public void enterMatch(Long matchId){
    	this.resetCounters();
    	this.setCurrentMatchId(matchId);
    }
    
    public void resetCounters(){
    	this.currentMatchDeathCount = 0l;
    	this.currentMatchKillCount = 0l;    
    	this.killsByWeapon = new HashMap<String, Long>();
    	this.currentMatchKillStreak=0l ;
    	this.killStreakStartTime = new Date();
    	this.currentMatchHighestKillStreak=0l;
    	this.setCurrentMatchId(null);
    }

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Player other = (Player) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}

    
}
