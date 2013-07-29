package generic.fps.ranking.util;

import generic.fps.ranking.common.Enemies;
import generic.fps.ranking.common.Player;
import generic.fps.ranking.common.Weapons;

import java.util.Random;

public class ChangePlayerWeapon {

	public static void randomChangeWeapon(Player player){
		
		Random randomValue = new Random();
		Integer weapon =  Math.abs(randomValue.nextInt(8));		
		
		if(player.getName().equals("<WORLD>")){
			switch (weapon){ 
			     case 1 : player.setCurrentWeapon(Enemies.DROWN.getName()); break;
			     case 2 : player.setCurrentWeapon(Enemies.SMOUGH.getName());break;
			     case 3 : player.setCurrentWeapon(Enemies.AXEGOLEM.getName());break;
			     case 4 : player.setCurrentWeapon(Enemies.QUELANA.getName());break;
			     case 5 : player.setCurrentWeapon(Enemies.GWYN.getName());break;
			     case 6 : player.setCurrentWeapon(Enemies.ELF.getName());break;
			     case 7 : player.setCurrentWeapon(Enemies.DRAGON.getName());break;
			     case 8 : player.setCurrentWeapon(Enemies.CAPRADEMON.getName());break;
			}	
		}
		else{
			switch (weapon){ 
			     case 1 : player.setCurrentWeapon(Weapons.HANDGUN.getName()); break;
			     case 2 : player.setCurrentWeapon(Weapons.SHOTGUN.getName());break;
			     case 3 : player.setCurrentWeapon(Weapons.KNIFE.getName());break;
			     case 4 : player.setCurrentWeapon(Weapons.AXE.getName());break;
			     case 5 : player.setCurrentWeapon(Weapons.WIDOWMAKER.getName());break;
			     case 6 : player.setCurrentWeapon(Weapons.BOMB.getName());break;
			     case 7 : player.setCurrentWeapon(Weapons.BAZUKA.getName());break;
			     case 8 : player.setCurrentWeapon(Weapons.VOODOO.getName());break;
			}	
		}
	}
	
	public static void changeWeapon(Player player , Weapons weapon){
		if(player != null && !player.getName().equals("<WORLD>")){
			 player.setCurrentWeapon(weapon.getName());
		}
	}

	public static void changeEnemie(Player player , Enemies enemy){
		if(player != null && player.getName().equals("<WORLD>")){
			 player.setCurrentWeapon(enemy.getName());
		}
	}
	
}
