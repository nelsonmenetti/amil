package generic.fps.ranking;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;

import generic.fps.ranking.common.Enemies;
import generic.fps.ranking.common.Match;
import generic.fps.ranking.common.Player;
import generic.fps.ranking.common.Weapons;
import generic.fps.ranking.util.ChangePlayerWeapon;
import generic.fps.ranking.util.DefinePlayerKills;

import org.apache.log4j.Logger;

/**
 * Hello world!
 *
 */
public class SimulateMatch 
{
	private static final Logger logger = Logger.getLogger(SimulateMatch.class) ; 

	
	private static Integer randomPlayerPosition(final Integer currentPlayerPosition , final Integer maxPlayers){
		Random random = new Random(new Date().getTime());
		Integer attackPosition = Math.abs(random.nextInt(maxPlayers-1));
		if(attackPosition == currentPlayerPosition){
			return randomPlayerPosition( currentPlayerPosition ,  maxPlayers);
		}
		
		return attackPosition;

	}
	
    public static void main( String[] args )
    {
    	logger.info("This is a simple simulation of a 10 player match...");
        
		// Create a match and wait for players to join it
		Match match = new Match(1);
		match.waitPlayers();

		// Create Players

		Player player1 = new Player("JEFF", Weapons.AXE);
		Player player2 = new Player("BILL", Weapons.AXE);
		Player player3 = new Player("JEAN", Weapons.AXE);
		Player player4 = new Player("DENIS", Weapons.AXE);
		Player player5 = new Player("MORGAN", Weapons.AXE);
		Player player6 = new Player("JEREMY", Weapons.AXE);
		Player player7 = new Player("ANA", Weapons.AXE);
		Player player8 = new Player("JP", Weapons.AXE);
		Player player9 = new Player("ALAN", Weapons.AXE);
		Player player10 = new Player("DEAN", Weapons.AXE);
		
		//Create WORLD
		Player playerWorld = new Player(Enemies.DROWN);
		
		//ADD Players
		match.addPlayer(player1);
		match.addPlayer(player2);
		match.addPlayer(player3);
		match.addPlayer(player4);
		match.addPlayer(player5);
		match.addPlayer(player6);
		match.addPlayer(player7);
		match.addPlayer(player8);
		match.addPlayer(player9);
		match.addPlayer(player10);
		match.addPlayer(playerWorld);

		
		//Generate a Random Numbers that will be the number of rounds of each player ( MAX 40)
		Random randomTurns = new Random(new Date().getTime());

		//Start Match
		match.startMatch();
		ArrayList <Player> playerList = new ArrayList<Player>();
		playerList.addAll(match.getPlayers());
		Integer position =0;
		for(Player player:match.getPlayers()){
			Integer numberOfTurns = Math.abs(randomTurns.nextInt(40));
			
			
			while(numberOfTurns >0){
				
	        	DefinePlayerKills.randomKillOrDie(player,playerList.get(randomPlayerPosition(position,playerList.size()))  );
	        	numberOfTurns--;
	        }
			for(Player playertochange:match.getPlayers()){
				  ChangePlayerWeapon.randomChangeWeapon(playertochange);
			}
			position++;
        }		
	
		//Ends Current Match
		match.endMatch();

		//Ranking
		logger.info("MATCH RANKING");
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
}
