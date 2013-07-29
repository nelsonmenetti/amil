package generic.fps.ranking.reversematch;

import generic.fps.ranking.common.Match;
import generic.fps.ranking.common.Player;
import generic.fps.ranking.common.TypeOfAction;
import generic.fps.ranking.util.DefinePlayerKills;
import generic.fps.ranking.util.FileUtil;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import org.apache.log4j.Logger;

public class SimulateMatchBasedOnLog {

	private static final Logger logger = Logger.getLogger(SimulateMatchBasedOnLog.class) ; 
	public static List<Match> matches = new ArrayList<Match>();
	public static Match currentMatch = null;
	public static List<Player> currentMatchListOfPlayers = null;
	
    public static void main( String[] args ){
    	
    	if(args == null || args.length != 1){
    		logger.info("USAGE   :  SimulateMatchBasedOnLog <LogFileLocation> ");
    		logger.info("Example :  SimulateMatchBasedOnLog \"C:\\theLogFile\\\" ");
    		logger.info("Please ensure that the Log file has this aspect//format before procceding:");
    		logger.info("\t <TimeStamp & Other Info> - New match 11348965 has started");
    		logger.info("\t <TimeStamp & Other Info> - Roman killed Nick using M16");
    		logger.info("\t <TimeStamp & Other Info> - <WORLD> killed Nick by DROWN");
    		logger.info("\t <TimeStamp & Other Info> - Match 11348965 has ended");
    		return;
    	}
    	
    	logger.info("Processing Match(s) information from file "+args[0] );
    	
    	List<String> lines= FileUtil.readAllFileLines(args[0]);
    	for(String line : lines){
    		TypeOfAction type = SimulateMatchBasedOnLog.interpretLineActionType (line);
    		SimulateMatchBasedOnLog.proccessAction (type, line);
    	}   
    	for(Match match : matches){
    	        //Ranking
    			logger.info("MATCH RANKING FOR MATCH " +match.getId());
    			match.printRanking();
    			
    			//Killstreaks
    			logger.info("KILLSTREAKS (ORDERED BY MATCH RANKING) ");

    			match.printMaxKillStreaksByPlayer();
    			
    			//Awards
    			logger.info("AWARDS (ORDERED BY MATCH RANKING) ");

    			match.printPlayerAwards();
    			
    			//Weapons Used By TOP Player
    			logger.info("TOP PLAYER MOST USED WEAPON (ORDERED BY USE) ");
    			match.generateRanking().get(0).printPreferredWeapons();
    			
    	}
    	logger.info("Finished Processing Match(s) information from file"+args[0] );    	
    }
    
    public static TypeOfAction interpretLineActionType (String line){
    	TypeOfAction type = TypeOfAction.UNRECOGNIZED_ACTION;
    	for (TypeOfAction action : TypeOfAction.values()) {
        	if(line.matches(action.getRegex())){
        		type = action;
        		break;
        	}
        }
    	return type;
    }
    
    public static void proccessAction (TypeOfAction type ,String line ){
    	if(type.equals(TypeOfAction.MATCH_START)){
    		if(currentMatch != null){
    			throw new RuntimeException("UNEXPECTED Start of Match inside another match !");
    		}
    		try{
	    		Long matchId = new Long(line.split("New match ")[1].split(" ")[0].trim());
	    		currentMatch = new Match(matchId); 
		   		logger.info("Found start of match "+matchId+" sucessfully");	   	   
    		}catch (Exception e) {
    			logger.error("This log file is corrupted, found an start match that had UNEXPECTED pattern , could not process the file.");
    			throw new RuntimeException("This log file is corrupted, found an start match that had UNEXPECTED pattern , could not process the file.");
			}
     
    	}
    	else if(type.equals(TypeOfAction.MATCH_END)){
    		try{
    		    Long matchId = new Long(line.split("Match ")[1].split(" ")[0].trim());
    		    if(currentMatch.getId() ==matchId.longValue()){
    		    	currentMatch.setWaitingPlayers(true);
        	   		currentMatch.setPlayers( new HashSet<Player>(currentMatchListOfPlayers) );
        	   		currentMatch.endMatch();
        	   		matches.add(currentMatch);
        	   		currentMatchListOfPlayers = null;
        	   		currentMatch=null;
        	   		logger.info("Processed match "+matchId+" sucessfully");
        		}else{
        			logger.warn("This log file is corrupted, found an end to a match inside another match ! Results might be diferent from expected");
        		}    		
    		}catch (Exception e) {
    			logger.error("This log file is corrupted, found an end match that had strange pattern , could not process the file.");
    			throw new RuntimeException("This log file is corrupted, found an end match that had a UNEXPECTED pattern , could not process the file.");
			}
    		
 		}
    	else if(type.equals(TypeOfAction.PLAYER_KILL)){
    		try{

	    		String player1Name = line.split("killed")[0].split(" \\- ")[1].trim();
	    		String player2Name = line.split("killed")[1].split("using")[0].trim();
	    		String player1Weapon = line.split("killed")[1].split("using")[1].trim();
	    		
	    		Player player1 = new  Player(player1Name);
	    		player1.setCurrentWeapon(player1Weapon);
	    		player1.setCurrentMatchId(currentMatch.getId());
	    		Player player2 = new Player(player2Name);
	    		player2.setCurrentMatchId(currentMatch.getId());
	    		
	    		if(currentMatchListOfPlayers == null){
	    			currentMatchListOfPlayers = new ArrayList<Player>();
	    		}
	    		
	    		if(!currentMatchListOfPlayers.contains(player1)){
	    			currentMatchListOfPlayers.add(player1);
	    		}else{
	    			int index = currentMatchListOfPlayers.indexOf(player1);
	    			player1 = currentMatchListOfPlayers.get(index);
		    		player1.setCurrentWeapon(player1Weapon);
	    		}
	    		
	    		if(!currentMatchListOfPlayers.contains(player2)){
	    			currentMatchListOfPlayers.add(player2);
	    		}else{
	    			int index = currentMatchListOfPlayers.indexOf(player2);
	    			player2 = currentMatchListOfPlayers.get(index);
	    		}
	    		
	    		DefinePlayerKills.killOrDie(player1, player2, 100);
	    		
	    	}catch (Exception e) {
    			logger.error("This log file is corrupted, found an Player Kill that had a UNEXPECTED pattern , could not process the file.");
    			throw new RuntimeException("This log file is corrupted, found an Player Kill that had a UNEXPECTED pattern , could not process the file.");
			}			
		}
    	else if(type.equals(TypeOfAction.WORLD_KILL)){
    		try{

	    		String player1Name = "<WORLD>";
	    		String player2Name = line.split("<WORLD> killed")[1].split("by")[0].trim();
	    		String player1Enemie = line.split("<WORLD> killed")[1].split("by")[1].trim();
	    		
	    		Player player1 = new  Player(player1Name);
	    		player1.setCurrentWeapon(player1Enemie);
	    		player1.setCurrentMatchId(currentMatch.getId());

	    		Player player2 = new Player(player2Name);
	    		player2.setCurrentMatchId(currentMatch.getId());

	    		if(currentMatchListOfPlayers == null){
	    			currentMatchListOfPlayers = new ArrayList<Player>();
	    		}
	    		
	    		if(!currentMatchListOfPlayers.contains(player1)){
	    			currentMatchListOfPlayers.add(player1);
	    		}else{
	    			int index = currentMatchListOfPlayers.indexOf(player1);
	    			player1 = currentMatchListOfPlayers.get(index);
		    		player1.setCurrentWeapon(player1Enemie);
	    		}
	    		
	    		if(!currentMatchListOfPlayers.contains(player2)){
	    			currentMatchListOfPlayers.add(player2);
	    		}else{
	    			int index = currentMatchListOfPlayers.indexOf(player2);
	    			player2 = currentMatchListOfPlayers.get(index);
	    		}
	    		
	    		DefinePlayerKills.killOrDie(player1, player2, 100);
	    		
	    	}catch (Exception e) {
    			logger.error("This log file is corrupted, found an Player Kill that had a UNEXPECTED pattern , could not process the file.");
    			throw new RuntimeException("This log file is corrupted, found an Player Kill that had a UNEXPECTED pattern , could not process the file.");
			}
		}
    }

}
