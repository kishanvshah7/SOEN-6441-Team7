/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.Graphics;
import tdgame.model.GridCellModel;
import tdgame.model.configModel;

/**
 *
 * @author Rahul K Kikani
 */
public class GridCellView {
    
    public GridCellView(){
        
    }
    
    public void draw(GridCellModel gcModel,Graphics g){
        //System.out.println("abcd");
        g.drawImage(configModel.ground_level[gcModel.gID], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
        g.drawRect(gcModel.x, gcModel.y, gcModel.width, gcModel.height);
        //System.out.println("airID"+gcModel.airID);
        
        if(gcModel.airID != configModel.airAir && (gcModel.airID ==8)){
           g.drawImage(configModel.air_level[0], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
        
        if(gcModel.airID != configModel.airAir && (gcModel.airID == 7)){
           g.drawImage(configModel.air_level[1], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
        
        if(gcModel.airID != configModel.airAir){
           g.drawImage(configModel.air_level[gcModel.airID], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
    }
    
    public void fireRangeOutline(GridCellModel gcModel,Graphics g){
        if(true){
            for(int i=0;i<configModel.airTowerLaser.length;i++){
                if(gcModel.airID == configModel.airTowerLaser[i]){
                    g.setColor(Color.yellow);
                    g.drawRect(gcModel.towerRange[i].x, gcModel.towerRange[i].y, gcModel.towerRange[i].width, gcModel.towerRange[i].height);
                }
            }
            g.setColor(Color.white);
        }
    }
}
