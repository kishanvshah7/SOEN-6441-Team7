/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Rectangle;

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
    
}
