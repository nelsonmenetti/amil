package generic.fps.ranking;

import generic.fps.ranking.common.Enemies;
import generic.fps.ranking.common.Match;
import generic.fps.ranking.common.Player;
import generic.fps.ranking.common.Weapons;
import generic.fps.ranking.util.ChangePlayerWeapon;
import generic.fps.ranking.util.DefinePlayerKills;

import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class BonusFunctionsTests extends TestCase{

	private static Logger logger = Logger.getLogger(BonusFunctionsTests.class);

	public static Test suite()
    {
        return new TestSuite( BonusFunctionsTests.class );
    }

	
	public void testPreferedWeaponByPlayer(){


		logger.info("TEST PREFERRED WEAPONS BY PLAYER START \n\n\n");

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
		Player player4 = new Player("DENIS", Weapons.AXE);
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
				

				DefinePlayerKills.killOrDie(playerWorld, player5, 7);
				DefinePlayerKills.killOrDie(player4, playerWorld, 6);
				DefinePlayerKills.killOrDie(player4, player4, 6);

				ChangePlayerWeapon.changeWeapon(player4, Weapons.KNIFE);

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
		
		//Get List of Top Player Weapon (DENIS)		
		Map<String, Long>  mostUsedWeapons=  ranking.get(0).getPreferredWeapons();
		ranking.get(0).printPreferredWeapons();
		
		//Confirm Kils with Axe
		Long killWithAxe = mostUsedWeapons.get(Weapons.AXE.getName());
		assertEquals(3,killWithAxe.longValue());
		
		
		
		logger.info("\n\n\n TEST  PREFERRED WEAPONS BY PLAYER END \n\n\n");

	
	}
	
   public void testBiggestKillStreakPerPlayer(){



		logger.info("TEST MAX KILL STREAK BY PLAYER START \n\n\n");

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
		Player player4 = new Player("DENIS", Weapons.AXE);
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
				

				DefinePlayerKills.killOrDie(playerWorld, player5, 7);
				DefinePlayerKills.killOrDie(player4, playerWorld, 6);
				DefinePlayerKills.killOrDie(player4, player4, 6);

				ChangePlayerWeapon.changeWeapon(player4, Weapons.KNIFE);

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
		
		//Killstreaks 
		
		match.printMaxKillStreaksByPlayer();
		
		//Confirm Kill Streak Values
		
		assertEquals(3l, ranking.get(0).getCurrentMatchHighestKillStreak().longValue());
		assertEquals(2l, ranking.get(1).getCurrentMatchHighestKillStreak().longValue());
		assertEquals(3l, ranking.get(2).getCurrentMatchHighestKillStreak().longValue());
		assertEquals(1l, ranking.get(3).getCurrentMatchHighestKillStreak().longValue());
		assertEquals(0l, ranking.get(4).getCurrentMatchHighestKillStreak().longValue());
		
		logger.info("\n\n\n TEST  MAX KILL STREAK BY PLAYER END \n\n\n");

	
	}

	
   public void testAwardsForNotDying(){




		logger.info("TEST MAX KILL STREAK BY PLAYER START \n\n\n");

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
		Player player4 = new Player("DENIS", Weapons.AXE);
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
				

				DefinePlayerKills.killOrDie(playerWorld, player5, 4);
				DefinePlayerKills.killOrDie(player4, playerWorld, 6);
				DefinePlayerKills.killOrDie(player4, player4, 6);

				ChangePlayerWeapon.changeWeapon(player4, Weapons.KNIFE);

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
		
		//Awards 
		
		match.printPlayerAwards();
		
		assertEquals("Escaping Death",player5.getAwards().get(0).getName());
		
		logger.info("\n\n\n TEST  MAX KILL STREAK BY PLAYER END \n\n\n");
		
   }
   
   public void testAwardsForKilling5inLessThan5Minutes(){





		logger.info("TEST MAX KILL STREAK BY PLAYER START \n\n\n");

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
		Player player4 = new Player("DENIS", Weapons.AXE);
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
				

				DefinePlayerKills.killOrDie(playerWorld, player5, 4);
				DefinePlayerKills.killOrDie(player4, playerWorld, 6);
				DefinePlayerKills.killOrDie(player4, player4, 6);

				ChangePlayerWeapon.changeWeapon(player4, Weapons.KNIFE);

				DefinePlayerKills.killOrDie(player5, player3, 6);
				DefinePlayerKills.killOrDie(playerWorld, player2, 6);
				DefinePlayerKills.killOrDie(player4, player2, 6);
				DefinePlayerKills.killOrDie(player1, player1, 6);
				DefinePlayerKills.killOrDie(player1, player2, 4);
				DefinePlayerKills.killOrDie(player1, player2, 6);
				DefinePlayerKills.killOrDie(player1, player3, 6);
				DefinePlayerKills.killOrDie(player1, player4, 6);
				DefinePlayerKills.killOrDie(player1, player5, 6);
				DefinePlayerKills.killOrDie(player1, player3, 6);


		// End Match
		match.endMatch();
		assertTrue(match.getFinished());
		assertFalse(match.getWaitingPlayers());
		assertFalse(match.getOngoing());

		//Generate Ranking 		
		List<Player> ranking =  match.generateRanking();
		match.printRanking();
		
		//Awards 
		
		match.printPlayerAwards();
		
		assertEquals("An Quick Hands Job",player1.getAwards().get(0).getName());
		
		logger.info("\n\n\n TEST  MAX KILL STREAK BY PLAYER END \n\n\n");
		
  
   }
   
   public void testNOAwardsForKilling5inMoreThan5Minutes() throws InterruptedException{

		logger.info("TEST MAX KILL STREAK BY PLAYER START \n\n\n");

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
		Player player4 = new Player("DENIS", Weapons.AXE);
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
				

				DefinePlayerKills.killOrDie(playerWorld, player5, 4);
				DefinePlayerKills.killOrDie(player4, playerWorld, 6);
				DefinePlayerKills.killOrDie(player4, player4, 6);

				ChangePlayerWeapon.changeWeapon(player4, Weapons.KNIFE);

				DefinePlayerKills.killOrDie(player5, player3, 6);
				DefinePlayerKills.killOrDie(playerWorld, player2, 6);
				DefinePlayerKills.killOrDie(player4, player2, 6);
				DefinePlayerKills.killOrDie(player1, player1, 6);
				DefinePlayerKills.killOrDie(player1, player2, 4);
				DefinePlayerKills.killOrDie(player1, player2, 6);
				DefinePlayerKills.killOrDie(player1, player3, 6);
				DefinePlayerKills.killOrDie(player1, player4, 6);
				DefinePlayerKills.killOrDie(player1, player5, 6);
				Thread.sleep(300000);
				
				DefinePlayerKills.killOrDie(player1, player3, 6);


		// End Match
		match.endMatch();
		assertTrue(match.getFinished());
		assertFalse(match.getWaitingPlayers());
		assertFalse(match.getOngoing());

		//Generate Ranking 		
		List<Player> ranking =  match.generateRanking();
		match.printRanking();
		
		//Awards 
		
		match.printPlayerAwards();
		
		assertEquals(0,player1.getAwards().size());
		
		logger.info("\n\n\n TEST  MAX KILL STREAK BY PLAYER END \n\n\n");
		
 
  }
   
}
