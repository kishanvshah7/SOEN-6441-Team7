/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Rectangle;
import tdgame.controller.CellContainerController;

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
    
    public CreatureModel(){
        
    }
    
    public CreatureModel(CellContainerModel ccModel, CellContainerController ccCont){
        this.ccModel = ccModel;
        this.ccCont = ccCont;
    }
    
    public void spawnCreature(int mobID)
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
        System.out.println("Mob Id: "+getHealth());
    }
    
    public void deleteCreature(){
        this.setInGame(false);
        //System.out.println("Not in Game: "+mobID);
        //this.health = 0;
        direction = right;
        mobWalk = 0;
        if(health <= 0){
            ccModel.getGcModelObj(yC, 0).getMoney(getMobID());
            configModel.killed +=1;
            configModel.total_killed +=1;
        }
        this.setHealth(0);
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
    
    public void loosHealth(){
        configModel.health -= 1;
    }
    
    public void loseHealth(int rate){
        setHealth(health - rate);
        checkDeath();
    }
    
    public void checkDeath(){
        if(health <= 0){
            deleteCreature();
        }
    }
    
    public boolean isDead(){
        if(inGame){
            return false;
        }
        else{
            return true;
        }
    }
    
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
