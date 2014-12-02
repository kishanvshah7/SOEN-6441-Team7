/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Rectangle;
import tdgame.controller.CellContainerController;
import towerdefensegame.LogGenerator;

/**
 *
 * @author Rahul K Kikani
 */
public class CreatureModel extends Rectangle{
    
    private int xC, yC;
    private int health = 44;
    private int healthSize =3, healthWidth =5, healthHeight = 44;
    private int mobSize = 44;
    private int mobWalk = 0;
    private int upward =0,downward =1, right = 2, left = 3;
    private int direction = right;
    private int mobID = -1;
    private boolean inGame = false;
    private boolean hasUpward = false;
    private boolean hasDownward = false;
    private boolean hasLeft = false;
    private boolean hasRight = false;
    private CellContainerModel ccModel;
    private CellContainerController ccCont;
    
    /**
     * CreatureModel constructor
     */
    public CreatureModel(){
        
    }
    
    /**
     * CreatureModel constructor
     * @param ccModel the CellContainerModel object
     * @param ccCont the CellContainerController Object
     */
    public CreatureModel(CellContainerModel ccModel, CellContainerController ccCont){
        this.ccModel = ccModel;
        this.ccCont = ccCont;
    }
    
    /**
     * Creatures Movement
     * @param mobID  creaturesID
     * @return successFlag
     */
    public boolean spawnCreature(int mobID)
    {
        for(int y=0;y<ccModel.getGcModel().length;y++){
            if(ccModel.getGcModelObj(y, 0).getgID() == configModel.groundRoad){
                setBounds(ccModel.getGcModelObj(y, 0).x , ccModel.getGcModelObj(y, 0).y, getMobSize(), getMobSize());
                xC = 0;
                yC = y;
            }
        }
        this.mobID = mobID;
        //this.setHealth(getHealth());
        setInGame(true);
        //System.out.println("Mob Id: "+getHealth());
        LogGenerator.addLog("Mob Id:"+getMobID()+" get started moving.");
        return true;
    }
    
    /**
     * Delete Creatures
     * @return successFlag
     */
    public boolean deleteCreature(){
        this.setInGame(false);
        direction = right;
        mobWalk = 0;
        if(health <= 0){
            ccModel.getGcModelObj(yC, 0).getMoney(getMobID());
            configModel.killed +=1;
            configModel.total_killed +=1;
            LogGenerator.addLog("Mob Killed, Mob Id :"+getMobID());
            LogGenerator.addLog("Mob killing reward added to main amount.");
        }
        this.setHealth(0);
        return true;
    }

    /**
     * @return the healthWidth
     */
    public int getHealthWidth() {
        return healthWidth;
    }

    /**
     * @return the mobSize
     */
    public int getMobSize() {
        return mobSize;
    }

    /**
     * @return the health
     */
    public int getHealth() {
        return health;
    }
    
    /**
     * LoosHealth of the game
     * @return successFlag
     */
    public boolean loosHealth(){
        configModel.health -= 1;
        return true;
    }
    
    /**
     * LoseHealth for creatures
     * @param rate rate of health
     * @return successFlag
     */
    public boolean loseHealth(int rate){
        setHealth(health - rate);
        checkDeath();
        return true;
    }
    
    /**
     * check Creature's life status
     * @return successFlag
     */
    public boolean checkDeath(){
        if(health <= 0){
            deleteCreature();
        }
        return true;
    }
    
    /**
     * check Creature's dead status
     * @return successFlag
     */
    public boolean isDead(){
        if(inGame){
            return false;
        }
        else{
            return true;
        }
    }
    
    /**
     * Creatures movement
     */
    public int walkFrame = 0, walkSpeed = 20;
    public void physic(){
        if(walkFrame >= walkSpeed){
            if(direction == right){
                x += 1;
            }else if(direction == upward){
                y -= 1;
            }else if(direction == downward){
                y +=1;
            }else if(direction == left){
                x -=1;
            }
            mobWalk +=1;
            if(mobWalk == configModel.cellPixels){
                if(direction == right){
                    xC += 1;
                    hasRight = true;
                }else if(direction == upward){
                    yC -= 1;
                    hasUpward = true;
                }else if(direction == downward){
                    yC +=1;
                    hasDownward =true;
                }
                else if(direction == left){
                    xC -=1;
                    hasLeft =true;
                }

                if(!hasUpward)
                {
                    try{
                        if(ccCont.getgcModelObj(yC+1, xC).getgID() == configModel.groundRoad ){
                            direction = downward;
                        }
                    } catch(Exception e){}
                }
                
                if(!hasDownward)
                {
                    try{
                        if(ccCont.getgcModelObj(yC-1, xC).getgID() == configModel.groundRoad ){
                            direction = upward;
                        }
                    } catch(Exception e){}
                }
                
                if(!hasLeft)
                {
                    try{
                        if(ccCont.getgcModelObj(yC, xC+1).getgID() == configModel.groundRoad ){
                            direction = right;
                        }
                    } catch(Exception e){}
                }
                
                if(!hasRight)
                {
                    try{
                        if(ccCont.getgcModelObj(yC, xC-1).getgID() == configModel.groundRoad ){
                            direction = left;
                        }
                    } catch(Exception e){}
                }
                
                if(ccCont.getgcModelObj(yC, xC).getAirID()== configModel.airCave){
                    //System.out.println("Delete+loosHealth "+getMobID());
                    deleteCreature();
                    loosHealth();
                }
                
                hasUpward = false;
                hasDownward =false;
                hasLeft = false;
                hasRight = false;
                mobWalk = 0;
            }
            walkFrame = 0;
        }else{
            walkFrame+=1;
        }
    }

    /**
     * @return the inGame
     */
    public boolean isInGame() {
        return inGame;
    }

    /**
     * @return the mobID
     */
    public int getMobID() {
        return mobID;
    }

    /**
     * @return the healthHeight
     */
    public int getHealthHeight() {
        return health/configModel.level;
    }

    /**
     * @param healthHeight the healthHeight to set
     */
    public void setHealthHeight(int healthHeight) {
        this.healthHeight = healthHeight;
    }

    /**
     * @param health the health to set
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * @param inGame the inGame to set
     */
    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
    
}
