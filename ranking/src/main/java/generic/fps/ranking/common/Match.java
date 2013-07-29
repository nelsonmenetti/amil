package generic.fps.ranking.common;

import generic.fps.ranking.util.PlayerKillRankingComparator;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class Match {

	private static final Logger logger = Logger.getLogger(Match.class) ; 
	private long id;
	private Set<Player> players= new HashSet<Player>();
	private Boolean waitingPlayers;
	private Boolean ongoing;
	private Boolean finished;
		
	public Match(long id) {
		super();
		this.id = id;
	}

	public Match(long id, Set<Player> players) {
		super();
		this.id = id;
		this.players = players;
		this.waitPlayers();
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	
	
	public Boolean getWaitingPlayers() {
		return waitingPlayers;
	}
	public void setWaitingPlayers(Boolean waitingPlayers) {
		this.waitingPlayers = waitingPlayers;
	}
	public Boolean getOngoing() {
		return ongoing;
	}
	public void setOngoing(Boolean ongoing) {
		this.ongoing = ongoing;
	}
	public Boolean getFinished() {
		return finished;
	}
	public void setFinished(Boolean finished) {
		this.finished = finished;
	}
	
	
	public void addPlayer(Player player){

		if(this.waitingPlayers ) {
			player.enterMatch(id);
			players.add(player);
			logger.info("Player "+ player.getName()+" added to match");
		}else{
			logger.warn("Player "+ player.getName()+" refused match is ongoing or finished");
		}
	}
	
	public void removePlayer(Player player){
		if(this.waitingPlayers ) {
			player.resetCounters();
			players.remove(player);
			logger.info("Player "+ player.getName()+" removed from match");
		}else{
			logger.warn("Player "+ player.getName()+" cant be removed because match is ongoing or finished");
		}
	}
	
	public void waitPlayers(){
		this.waitingPlayers=true;
		this.ongoing=false;
		this.finished=false;
		logger.info("Match Created and Waiting for Players");
	}
	public void startMatch(){
		this.waitingPlayers=false;
		this.ongoing=true;
		this.finished=false;
		logger.info("New match "+id+" has started");
	}
	
    public void endMatch(){
    	this.waitingPlayers=false;
		this.ongoing=false;
		this.finished=true;
		
		for(Player player : players){
			Award award = new Award("Escaping Death", "Dont die during a match", this.id);
			if(player.getCurrentMatchDeathCount() == 0){				
				player.getAwards().add(award);
			}
		}
		

		logger.info("Match "+id+" has ended");
	}
	
	public List<Player> generateRanking (){
		
		ArrayList <Player> rankedPlayerList = new ArrayList<Player>();
        rankedPlayerList.addAll(players);
		Collections.sort(rankedPlayerList, new PlayerKillRankingComparator());
		
		//remove player WORLD
		
		Player world = new Player(Enemies.ELF);
		rankedPlayerList.remove(world);
		
		
		return rankedPlayerList;
		
	}
	
	public void printMaxKillStreaksByPlayer(){
		List <Player> rankedPlayerList = generateRanking();

		logger.info ("\n MAX KillStreaks Per Player");
		Integer position = 1;
        for(Player player :rankedPlayerList){
            if(player !=null){
            	logger.info(position+". "+player.getName() +" killStreak :"+player.getCurrentMatchHighestKillStreak());
            	position++;
            }
        }
		logger.info ("\n ");

	}
	
	public void printPlayerAwards(){
		List <Player> rankedPlayerList = generateRanking();

		logger.info ("\n Awards Per Player");
		Integer position = 1;
        for(Player player :rankedPlayerList){
            if(player !=null){
            	logger.info(position+". "+player.getName() );
            	for(Award award : player.getAwards()){
                	if(award.getMatchId() == this.id)
            		logger.info("\t"+ award.getName() +": "+award.getDescription());
            	}
            	position++;
            }
        }
		logger.info ("\n ");

	}
	
	public void printRanking(){
		List <Player> rankedPlayerList = generateRanking();
    	logger.info("\n RANKING OF MATCH "+this.id+"\n");
		
    	Integer position = 1;
        for(Player player :rankedPlayerList){
            if(player !=null){
            	logger.info(position+". "+player.getName() +" kills :"+player.getCurrentMatchKillCount()+" deaths :"+player.getCurrentMatchDeathCount());
            	position++;
            }
        }        
        logger.info("\n DRUGS ARE FOR LOSERS \n");
	}
	
}
