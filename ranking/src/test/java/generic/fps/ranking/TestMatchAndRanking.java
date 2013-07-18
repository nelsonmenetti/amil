package generic.fps.ranking;

import java.util.List;

import org.apache.log4j.Logger;

import generic.fps.ranking.common.Enemies;
import generic.fps.ranking.common.Match;
import generic.fps.ranking.common.Player;
import generic.fps.ranking.common.Weapons;
import generic.fps.ranking.util.ChangePlayerWeapon;
import generic.fps.ranking.util.DefinePlayerKills;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
 
public class TestMatchAndRanking extends TestCase{

	private static Logger logger = Logger.getLogger(TestMatchAndRanking.class);
	/**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( TestMatchAndRanking.class );
    }
    
	public void testCreateMatchWith5Players(){
		logger.info("TEST CREATE MATCH START \n\n\n");
		
		//Create a match and wait for players to join it
		Match match = new Match(1);
		match.waitPlayers();
		
		assertTrue(match.getWaitingPlayers());
		assertFalse(match.getOngoing());
		assertFalse(match.getFinished());
		
		//Create Players
		
		Player player1 = new Player("JEFF", Weapons.AXE  ) ;
		Player player2 = new Player("BILL", Weapons.AXE ) ;
		Player player3 = new Player("JEAN", Weapons.AXE) ;
		Player player4 = new Player("DENIS", Weapons.AXE) ;
		Player player5 = new Player("MORGAN", Weapons.AXE) ;
		Player playerWorld = new Player( Enemies.DROWN) ;
		
		assertEquals("<WORLD>", playerWorld.getName());
		
		//add Players For Match
		match.addPlayer(player1);
		match.addPlayer(player2);
		match.addPlayer(player3);
		match.addPlayer(player4);
		match.addPlayer(player5);
		match.addPlayer(playerWorld);
		
		assertEquals(6 ,match.getPlayers().size());
		
		//Star Match
		match.startMatch();
		
		assertTrue(match.getOngoing());
		assertFalse(match.getWaitingPlayers());
		assertFalse(match.getFinished());
		
		
		logger.info("\n\n\nTEST CREATE MATCH END \n\n\n");
	}
	
    public void testEndMatch(){
		logger.info("TEST END MATCH START \n\n\n");

    	//Create a match and wait for players to join it
    			Match match = new Match(1);
    			match.waitPlayers();
    			
    			assertTrue(match.getWaitingPlayers());
    			assertFalse(match.getOngoing());
    			assertFalse(match.getFinished());
    			
    			//Create Players
    			
    			Player player1 = new Player("JEFF", Weapons.AXE  ) ;
    			Player player2 = new Player("BILL", Weapons.AXE ) ;
    			Player player3 = new Player("JEAN", Weapons.AXE) ;
    			Player player4 = new Player("DENIS", Weapons.AXE) ;
    			Player player5 = new Player("MORGAN", Weapons.AXE) ;
    			Player playerWorld = new Player( Enemies.DROWN) ;
    			
    			assertEquals("<WORLD>", playerWorld.getName());
    			
    			//add Players For Match
    			match.addPlayer(player1);
    			match.addPlayer(player2);
    			match.addPlayer(player3);
    			match.addPlayer(player4);
    			match.addPlayer(player5);
    			match.addPlayer(playerWorld);
    			
    			assertEquals(6 ,match.getPlayers().size());
    			
    			//Star Match
    			match.startMatch();
    			
    			assertTrue(match.getOngoing());
    			assertFalse(match.getWaitingPlayers());
    			assertFalse(match.getFinished());
    			  			
    			
    			//End Match
    			match.endMatch();
    			assertTrue(match.getFinished());
    			assertFalse(match.getWaitingPlayers());
    			assertFalse(match.getOngoing());

    	logger.info("\n\n\nTEST END MATCH END \n\n\n");
	
	}
    
    public void testAddingAndRemovingPlayers(){

		logger.info("TEST ADD AND REMOVE PLAYERS END \n\n\n");

    	//Create a match and wait for players to join it
    			Match match = new Match(1);
    			match.waitPlayers();
    			
    			assertTrue(match.getWaitingPlayers());
    			assertFalse(match.getOngoing());
    			assertFalse(match.getFinished());
    			
    			//Create Players
    			
    			Player player1 = new Player("JEFF", Weapons.AXE  ) ;
    			Player player2 = new Player("BILL", Weapons.AXE ) ;
    			Player player3 = new Player("JEAN", Weapons.AXE) ;
    			Player player4 = new Player("DENIS", Weapons.AXE) ;
    			Player player5 = new Player("MORGAN", Weapons.AXE) ;
    			Player playerWorld = new Player( Enemies.DROWN) ;
    			
    			assertEquals("<WORLD>", playerWorld.getName());
    			
    			//add Players For Match
    			match.addPlayer(player1);
    			match.addPlayer(player2);
    			match.addPlayer(player3);
    			match.addPlayer(player4);
    			match.addPlayer(player5);
    			match.addPlayer(playerWorld);
    			
    			assertEquals(6 ,match.getPlayers().size());
    			
    			//remove Players For Match
    			match.removePlayer(player2);
    			match.removePlayer(player3);
    			match.removePlayer(player4);
    			
    			assertEquals(3 ,match.getPlayers().size());
    			
    	logger.info("\n\n\nTEST ADD AND REMOVE PLAYERS END \n\n\n");
	
	
	}
    
    public void testAddingPlayerAfterMatchStars(){

		logger.info("TEST ADD PLAYERS AFTER MATCH STARTS END \n\n\n");

    	//Create a match and wait for players to join it
    			Match match = new Match(1);
    			match.waitPlayers();
    			
    			assertTrue(match.getWaitingPlayers());
    			assertFalse(match.getOngoing());
    			assertFalse(match.getFinished());
    			
    			//Create Players
    			
    			Player player1 = new Player("JEFF", Weapons.AXE  ) ;
    			Player player2 = new Player("BILL", Weapons.AXE ) ;
    			Player player3 = new Player("JEAN", Weapons.AXE) ;
    			Player player4 = new Player("DENIS", Weapons.AXE) ;
    			Player player5 = new Player("MORGAN", Weapons.AXE) ;
    			Player playerWorld = new Player( Enemies.DROWN) ;
    			
    			assertEquals("<WORLD>", playerWorld.getName());
    			
    			//add Players For Match
    			match.addPlayer(player1);
    			match.addPlayer(player2);
    			match.addPlayer(player3);
    			match.addPlayer(player4);
    			match.addPlayer(playerWorld);
    			
    			assertEquals(5 ,match.getPlayers().size());
    			match.startMatch();
    			
    			match.addPlayer(player5);

    			
    			assertEquals(5 ,match.getPlayers().size());
    			
    	logger.info("\n\n\nTEST ADD PLAYERS AFTER MATCH STARTS END \n\n\n");
	
	
	}
    
    public void testDeletePlayerAfterMatchStars(){

		logger.info("TEST REMOVE PLAYERS AFTER MATCH STARTS END \n\n\n");

    	//Create a match and wait for players to join it
    			Match match = new Match(1);
    			match.waitPlayers();
    			
    			assertTrue(match.getWaitingPlayers());
    			assertFalse(match.getOngoing());
    			assertFalse(match.getFinished());
    			
    			//Create Players
    			
    			Player player1 = new Player("JEFF", Weapons.AXE  ) ;
    			Player player2 = new Player("BILL", Weapons.AXE ) ;
    			Player player3 = new Player("JEAN", Weapons.AXE) ;
    			Player player4 = new Player("DENIS", Weapons.AXE) ;
    			Player playerWorld = new Player( Enemies.DROWN) ;
    			
    			assertEquals("<WORLD>", playerWorld.getName());
    			
    			//add Players For Match
    			match.addPlayer(player1);
    			match.addPlayer(player2);
    			match.addPlayer(player3);
    			match.addPlayer(player4);
    			match.addPlayer(playerWorld);
    			
    			assertEquals(5 ,match.getPlayers().size());
    			match.startMatch();
    			
    			match.removePlayer(player1);

    			
    			assertEquals(5 ,match.getPlayers().size());
    			
    	logger.info("\n\n\nTEST REMOVE PLAYERS AFTER MATCH STARTS END \n\n\n");
	
	
	}
    
	public void testRankingAfterMatch() {

		logger.info("TEST RANKING AFTER MATCH START \n\n\n");

		// Create a match and wait for players to join it
		Match match = new Match(1);
		match.waitPlayers();

		assertTrue(match.getWaitingPlayers());
		assertFalse(match.getOngoing());
		assertFalse(match.getFinished());

		// Create Players

		Player player1 = new Player("JEFF", Weapons.AXE);
		Player player2 = new Player("BILL", Weapons.AXE);
		Player player3 = new Player("JEAN", Weapons.AXE);
		Player player4 = new Player("DENIS", Weapons.HANDGUN);
		Player player5 = new Player("MORGAN", Weapons.AXE);
		Player playerWorld = new Player(Enemies.DROWN);

		assertEquals("<WORLD>", playerWorld.getName());

		// add Players For Match
		match.addPlayer(player1);
		match.addPlayer(player2);
		match.addPlayer(player3);
		match.addPlayer(player4);
		match.addPlayer(player5);
		match.addPlayer(playerWorld);

		assertEquals(6, match.getPlayers().size());

		// Star Match
		match.startMatch();

		assertTrue(match.getOngoing());
		assertFalse(match.getWaitingPlayers());
		assertFalse(match.getFinished());

		// LETS ROLL
		DefinePlayerKills.killOrDie(player1, player2, 5);
		DefinePlayerKills.killOrDie(player2, player1, 6);
		DefinePlayerKills.killOrDie(player3, player4, 5);
		DefinePlayerKills.killOrDie(player4, player3, 6);
		DefinePlayerKills.killOrDie(player1, player4, 5);
		ChangePlayerWeapon.changeWeapon(player4, Weapons.SHOTGUN);
		
		DefinePlayerKills.killOrDie(player2, playerWorld, 5);
		DefinePlayerKills.killOrDie(player1, player2, 5);
		DefinePlayerKills.killOrDie(playerWorld, player2, 6);
		DefinePlayerKills.killOrDie(playerWorld, player2, 6);
		DefinePlayerKills.killOrDie(player1, player2, 6);
		DefinePlayerKills.killOrDie(player5, player3, 6);
		DefinePlayerKills.killOrDie(playerWorld, player4, 6);
		DefinePlayerKills.killOrDie(player1, player3, 6);
		DefinePlayerKills.killOrDie(player1, player4, 7);
		
		ChangePlayerWeapon.changeWeapon(player4, Weapons.KNIFE);

		DefinePlayerKills.killOrDie(playerWorld, player5, 7);
		DefinePlayerKills.killOrDie(player4, playerWorld, 6);
		DefinePlayerKills.killOrDie(player4, player4, 6);
		DefinePlayerKills.killOrDie(player5, player3, 6);
		DefinePlayerKills.killOrDie(playerWorld, player2, 6);
		DefinePlayerKills.killOrDie(player4, player2, 6);
		DefinePlayerKills.killOrDie(player1, player1, 6);
		DefinePlayerKills.killOrDie(player1, player2, 4);

		// End Match
		match.endMatch();
		assertTrue(match.getFinished());
		assertFalse(match.getWaitingPlayers());
		assertFalse(match.getOngoing());

		//Generate Ranking 		
		List<Player> ranking =  match.generateRanking();
		match.printRanking();
		
		assertEquals(player4, ranking.get(0) );
		assertEquals(player2, ranking.get(1) );
		assertEquals(player1, ranking.get(2) );
		assertEquals(player5, ranking.get(3) );
		assertEquals(player3, ranking.get(4) );
		
		logger.info("\n\n\n TEST RANKING AFTER MATCH END \n\n\n");

	}
    
	public void testThatKillingWorldEnemiesDontCountToRank() {

		logger.info("TEST THAT WORLD KILL DONT AFFECT RANK \n\n\n");

		// Create a match and wait for players to join it
		Match match = new Match(1);
		match.waitPlayers();

		assertTrue(match.getWaitingPlayers());
		assertFalse(match.getOngoing());
		assertFalse(match.getFinished());

		// Create Players

		Player player1 = new Player("JEFF", Weapons.AXE);
		Player player2 = new Player("BILL", Weapons.AXE);
		Player player3 = new Player("JEAN", Weapons.AXE);
		Player player4 = new Player("DENIS", Weapons.HANDGUN);
		Player player5 = new Player("MORGAN", Weapons.AXE);
		Player playerWorld = new Player(Enemies.DROWN);

		assertEquals("<WORLD>", playerWorld.getName());

		// add Players For Match
		match.addPlayer(player1);
		match.addPlayer(player2);
		match.addPlayer(player3);
		match.addPlayer(player4);
		match.addPlayer(player5);
		match.addPlayer(playerWorld);

		assertEquals(6, match.getPlayers().size());

		// Star Match
		match.startMatch();

		assertTrue(match.getOngoing());
		assertFalse(match.getWaitingPlayers());
		assertFalse(match.getFinished());

		// LETS ROLL
		DefinePlayerKills.killOrDie(player1, player2, 5);
		DefinePlayerKills.killOrDie(player2, player1, 6);
		DefinePlayerKills.killOrDie(player3, player4, 5);
		DefinePlayerKills.killOrDie(player4, player3, 6);
		DefinePlayerKills.killOrDie(player1, player4, 5);
		ChangePlayerWeapon.changeWeapon(player4, Weapons.SHOTGUN);
		
		DefinePlayerKills.killOrDie(player2, playerWorld, 5);
		DefinePlayerKills.killOrDie(player1, player2, 5);
		DefinePlayerKills.killOrDie(playerWorld, player2, 6);
		DefinePlayerKills.killOrDie(playerWorld, player2, 6);
		DefinePlayerKills.killOrDie(player1, player2, 6);
		DefinePlayerKills.killOrDie(player5, player3, 6);
		DefinePlayerKills.killOrDie(playerWorld, player4, 6);
		DefinePlayerKills.killOrDie(player1, player3, 6);
		DefinePlayerKills.killOrDie(player1, player4, 7);
		
		ChangePlayerWeapon.changeWeapon(player4, Weapons.KNIFE);

		DefinePlayerKills.killOrDie(playerWorld, player5, 7);
		DefinePlayerKills.killOrDie(player4, playerWorld, 6);
		DefinePlayerKills.killOrDie(player4, player4, 6);
		DefinePlayerKills.killOrDie(player5, player3, 6);
		DefinePlayerKills.killOrDie(playerWorld, player2, 6);
		DefinePlayerKills.killOrDie(player4, player2, 6);
		DefinePlayerKills.killOrDie(player1, player1, 6);
		DefinePlayerKills.killOrDie(player1, player2, 4);

		//EXTRA WORLD KILLS BY PLAYER 4
		DefinePlayerKills.killOrDie(player4, playerWorld, 6);
		DefinePlayerKills.killOrDie(player4, playerWorld, 6);
		DefinePlayerKills.killOrDie(player4, playerWorld, 6);
		DefinePlayerKills.killOrDie(player4, playerWorld, 6);
		DefinePlayerKills.killOrDie(player4, playerWorld, 6);

		// End Match
		match.endMatch();
		assertTrue(match.getFinished());
		assertFalse(match.getWaitingPlayers());
		assertFalse(match.getOngoing());

		//Generate Ranking 		
		List<Player> ranking =  match.generateRanking();
		match.printRanking();
		
		assertEquals(player4, ranking.get(0) );
		assertEquals(player2, ranking.get(1) );
		assertEquals(player1, ranking.get(2) );
		assertEquals(player5, ranking.get(3) );
		assertEquals(player3, ranking.get(4) );
		
		logger.info("\n\n\n TEST THAT WORLD KILL DONT AFFECT RANK \n\n\n");

	}
	
}
