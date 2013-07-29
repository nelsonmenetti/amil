package generic.fps.ranking.util;

import generic.fps.ranking.common.Player;

import java.util.Random;

import org.apache.log4j.Logger;

public class DefinePlayerKills {

	private static final Logger logger = Logger.getLogger(DefinePlayerKills.class) ; 
		
	public static void randomKillOrDie(Player player1 , Player player2){

		Random randomValue = new Random();
		Integer fightResult =  Math.abs(randomValue.nextInt(10));		
				
		if(fightResult > 5){
			 player2.addDeath();

			 if(player1.getName().equals("<WORLD>")){
			 	 logger.debug(player1.getName() +" killed "+player2.getName()+" by "+player1.getCurrentWeapon());
			
			 }else if (player2.getName().equals("<WORLD>")) {
			 	 logger.debug(player1.getName() +" killed "+player2.getCurrentWeapon()+" using "+player1.getCurrentWeapon());
			 }
			 else{
				 player1.addKill();
			 	 logger.debug(player1.getName() +" killed "+player2.getName()+" using "+player1.getCurrentWeapon());

			 }
		 }else{
			 player1.addDeath();
			 if(player2.getName().equals("<WORLD>")){
				   logger.debug("<WORLD> killed  "+player1.getName()+" by "+player2.getCurrentWeapon());
			 }else if (player1.getName().equals("<WORLD>")) {
			 	 logger.debug(player2.getName() +" killed "+player1.getCurrentWeapon()+" using "+player2.getCurrentWeapon());
			 }
			 else{
			   player2.addKill();
			   logger.debug(player2.getName() +" killed "+player1.getName()+" using "+player2.getCurrentWeapon());
			 }
		 }
	}
	
	public static void killOrDie(Player player1 , Player player2 , Integer fightResult){

		if(fightResult == null){
			fightResult =0;
		}
		if(fightResult > 5){
			 player2.addDeath();

			 if(player1.getName().equals("<WORLD>")){
			 	 logger.debug(player1.getName() +" killed "+player2.getName()+" by "+player1.getCurrentWeapon());
			
			 }else if (player2.getName().equals("<WORLD>")) {
			 	 logger.debug(player1.getName() +" killed "+player2.getCurrentWeapon()+" using "+player1.getCurrentWeapon());
			 }
			 else{
				 player1.addKill();
			 	 logger.debug(player1.getName() +" killed "+player2.getName()+" using "+player1.getCurrentWeapon());

			 }
		 }else{
			 player1.addDeath();
			 if(player2.getName().equals("<WORLD>")){
				   logger.debug("<WORLD> killed  "+player1.getName()+" by "+player2.getCurrentWeapon());
			 }else if (player1.getName().equals("<WORLD>")) {
			 	 logger.debug(player2.getName() +" killed "+player1.getCurrentWeapon()+" using "+player2.getCurrentWeapon());
			 }
			 else{
			   player2.addKill();
			   logger.debug(player2.getName() +" killed "+player1.getName()+" using "+player2.getCurrentWeapon());
			 }
		 }
	}	


}
