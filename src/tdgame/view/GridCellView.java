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
 * This is GUI class of Grid Cell Module.
 * @author Rahul K Kikani
 */
public class GridCellView {
    
    /**
     * This is constructor.
     */
    public GridCellView(){
        
    }
    
    /**
     * This method will draw GUI Components.
     * @param g the Graphics
     * @param gcModel the model object
     */
    public void draw(GridCellModel gcModel,Graphics g){
        //System.out.println("abcd");
        g.drawImage(configModel.ground_level[gcModel.getgID()], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
        //g.drawRect(gcModel.x, gcModel.y, gcModel.width, gcModel.height);
        //System.out.println("airID"+gcModel.airID);
        
        if(gcModel.getAirID() != configModel.airAir && (gcModel.getAirID() ==8)){
           g.drawImage(configModel.air_level[0], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
        
        if(gcModel.getAirID() != configModel.airAir && (gcModel.getAirID() == 7)){
           g.drawImage(configModel.air_level[1], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
        
        if(gcModel.getAirID() != configModel.airAir){
           g.drawImage(configModel.air_level[gcModel.getAirID()], gcModel.x, gcModel.y, gcModel.width, gcModel.height, null);
       }
    }
    
    /**
     * This method will draw GUI Components.
     * @param g the Graphics
     * @param gcModel the model object
     */
    public void fireRangeOutline(GridCellModel gcModel,Graphics g){
        if(true){
            for(int i=0;i<configModel.airTowerLaser.length;i++){
                if(gcModel.getAirID() == configModel.airTowerLaser[i]){
                    g.setColor(Color.yellow);
                    g.drawRect(gcModel.getTowerRange(i).x, gcModel.getTowerRange(i).y, gcModel.getTowerRange(i).width, gcModel.getTowerRange(i).height);
                }
            }
            g.setColor(Color.white);
        }
    }
}
