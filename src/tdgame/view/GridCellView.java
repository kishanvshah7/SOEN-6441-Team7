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
<<<<<<< HEAD
                    g.setColor(Color.yellow);
                    g.drawRect(gcModel.getTowerRange(i).x, gcModel.getTowerRange(i).y, gcModel.getTowerRange(i).width, gcModel.getTowerRange(i).height);
                }
            }
            g.setColor(Color.white);
=======
                    g.setColor(Color.gray);
                    g.drawRect(gcModel.getTowerRange(i).x, gcModel.getTowerRange(i).y, gcModel.getTowerRange(i).width, gcModel.getTowerRange(i).height);
                }
            }
            g.setColor(Color.white);
        }
        if(gcModel != null && gcModel.isFiring() && gcModel.getAirID() != -1){
            if(gcModel.getAirID() == 4){
                    g.drawImage(configModel.fire[0], PlayScreenView.Creatures[gcModel.getShotMob()].x, PlayScreenView.Creatures[gcModel.getShotMob()].y, gcModel.width, gcModel.height, null);
            } else if(gcModel.getAirID() == 5){
            } else {
                g.drawImage(configModel.star[0], PlayScreenView.Creatures[gcModel.getShotMob()].x, PlayScreenView.Creatures[gcModel.getShotMob()].y, gcModel.width, gcModel.height, null);
            }
            
               //PlayScreenView.Creatures[gcModel.getShotMob()].walkSpeed = 40;
               //System.out.println("Firing Red: "+gcModel.getAirID());
            if(gcModel.getAirID() == 4){
                g.setColor(Color.yellow);
            } else if(gcModel.getAirID() == 5){
                g.setColor(Color.blue);
            } else if(gcModel.getAirID() == 6){
                g.setColor(Color.red);
            } else {
                g.setColor(Color.green);
            }
               
               //System.out.println(gcModel.getShotMob()+" - "+PlayScreenView.Creatures[gcModel.getShotMob()].x);
               g.drawLine(gcModel.x + (gcModel.width/2)+1, gcModel.y + (gcModel.height/2)+1, PlayScreenView.Creatures[gcModel.getShotMob()].x + (PlayScreenView.Creatures[gcModel.getShotMob()].width/2) + 1, PlayScreenView.Creatures[gcModel.getShotMob()].y + (PlayScreenView.Creatures[gcModel.getShotMob()].height/2) + 1);
               g.drawLine(gcModel.x + (gcModel.width/2), gcModel.y + (gcModel.height/2), PlayScreenView.Creatures[gcModel.getShotMob()].x + (PlayScreenView.Creatures[gcModel.getShotMob()].width/2), PlayScreenView.Creatures[gcModel.getShotMob()].y + (PlayScreenView.Creatures[gcModel.getShotMob()].height/2));
>>>>>>> origin/Rahul
        }
    }
}
