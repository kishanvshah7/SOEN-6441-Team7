/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.awt.Rectangle;

/**
 *
 * @author Rahul K Kikani
 */
public class GridCellModel extends Rectangle{
    
    public Rectangle[] towerRange;
    public int towerRangeSize = 100;
    public int gID;
    public int airID;
    public int loseTime = 100, loseFrame = 0;
    
    public int shotMob = -1;
    public boolean firing = false;

    GridCellModel(int x, int y, int width, int height, int gId, int airId) {
        setBounds(x, y, width, height);
        towerRange = new Rectangle[configModel.airTowerLaser.length];
        for(int i=0;i<configModel.airTowerLaser.length;i++){
            towerRange[i] = new Rectangle(x - ((configModel.airTowerRanger[i])/2), y - ((configModel.airTowerRanger[i])/2), width + configModel.airTowerRanger[i], height + configModel.airTowerRanger[i]);
        }
        this.gID = gID;
        this.airID = airID;
    }
    
}
