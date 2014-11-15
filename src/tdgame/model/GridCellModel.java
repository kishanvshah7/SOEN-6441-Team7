/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Rectangle;
import tdgame.view.PlayScreenView;

/**
 * This is model for Grid Cells Module.
 * @author Rahul K Kikani
 */
public class GridCellModel extends Rectangle{
    
    private Rectangle[] towerRange;
    private int towerRangeSize = 100;
    private int gID;
    private int airID;
    private int loseTime = 100, loseFrame = 0;
    
    private int shotMob = -1;
    private boolean firing = false;

    /**
     * * This is constructor method for Grid Cell. It will set different properties for each grid cell.
     * @param x x point
     * @param y y point
     * @param width width of cell
     * @param height height of cell
     * @param gId ground id of cell
     * @param airId  air id of cell
     */
    GridCellModel(int x, int y, int width, int height, int gId, int airId) {
        setBounds(x, y, width, height);
        towerRange = new Rectangle[configModel.airTowerLaser.length];
        for(int i=0;i<configModel.airTowerLaser.length;i++){
            towerRange[i] = new Rectangle(x - ((configModel.airTowerRanger[i])/2), y - ((configModel.airTowerRanger[i])/2), width + configModel.airTowerRanger[i], height + configModel.airTowerRanger[i]);
        }
        this.gID = gID;
        this.airID = airID;
    }
    
    public void physic(CreatureModel[] cModel){
        
        for(int i=0;i<configModel.airTowerLaser.length;i++){
            //for(int tid=0;tid<configModel.airTowerLaser.length;tid++){
                if(getShotMob() != -1 && towerRange[gID].intersects(cModel[getShotMob()])){
                    setFiring(true);
                }
                else{
                    //System.out.println("Firing stop");
                    setFiring(false);
                }
            //}
        }

        if(!isFiring()){
            for(int tid=0;tid<configModel.airTowerLaser.length;tid++){
                if(airID == configModel.airTowerLaser[tid]){
                    for(int i=0;i<cModel.length;i++){
                        if(cModel[i].isInGame()){
                            if(towerRange[tid].intersects(cModel[i])){
                                setFiring(true);
                                shotMob = i;
                            }
                        }
                    }
                }
            }
        }
        
        if(isFiring() && getAirID() != -1){
            if(loseFrame >= loseTime){
                cModel[getShotMob()].loseHealth(1);
                loseFrame = 0;
            }else {
                loseFrame +=1;
            }
           
            if(cModel[getShotMob()].isDead()){
                //getMoney(cModel[getShotMob()].getMobID());
                setFiring(false);
                shotMob = -1;
                PlayScreenView.hasWon();
                //System.out.println("Killed: "+configModel.killed);
                //System.out.println("KilledToWin: x");
            }
        }
    }

    /**
     * @param gID the gID to set
     */
    public void setgID(int gID) {
        this.gID = gID;
    }

    /**
     * @param airID the airID to set
     */
    public void setAirID(int airID) {
        this.airID = airID;
    }

    /**
     * @return the gID
     */
    public int getgID() {
        return gID;
    }

    /**
     * @return the airID
     */
    public int getAirID() {
        return airID;
    }

    /**
     * @return the towerRange
     * @param x x index
     */
    public Rectangle getTowerRange(int x) {
        return towerRange[x];
    }
    
    public void getMoney(int mobID){
        System.out.println("Money Is increased");
        configModel.money += configModel.deathReward[0];
    }

    /**
     * @return the shotMob
     */
    public int getShotMob() {
        return shotMob;
    }

    /**
     * @return the firing
     */
    public boolean isFiring() {
        return firing;
    }

    /**
     * @param firing the firing to set
     */
    public void setFiring(boolean firing) {
        this.firing = firing;
    }
}
