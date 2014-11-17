package tdgame.controller;

import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.Timer;



public class TowerPropertiesController {

	
	public abstract class Tower<GameGrid>{

	   
	    /** The total initial health an enemey starts with*/
	    protected double fullHealth;
	    /** The 'hit points' this enemy has*/
	    protected double health;
	    /** The ammount of damage each attack does*/
	    protected double damage;
	    /** The range of the tower in cells*/
	    protected int range;
	    /** The number of times per second the tower fires*/
	    protected double speed = 1;
	    /** The current cost of the tower*/
	    protected double cost;
	    /** The upgrade state of the tower(0 - 4)*/
	    protected int upgradeState = 0;
	    /** The listener to listen for signs to attack*/
	    protected ActionListener attackListener;
	    
	    protected ActionListener newEnemyListener;
	    protected Timer attackTimer;
		private GameGrid grid;
		
		{
	    
	    /**
	     * Adds this tower to a given grid. A tower can exist in only one cell and
	     * cannot be moved, so if already placed or cell already full it 
	     * will result in IllegalArgumentException
	     * @param grid The grid to add this actor to.
	     * @throws IllegalArgumentException if already placed or cell already full
	     */
	 
	        attackTimer = new Timer((int) (1000 / speed), attackListener);
	        attackTimer.start();
	    }
	}
}
