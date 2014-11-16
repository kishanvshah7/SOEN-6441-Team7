/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Rectangle;
import tdgame.model.ShopModel;
import tdgame.model.configModel;

/**
  * This is GUI class of Shop(Tower) Module.
 * @author Rahul K Kikani
 */
public class ShopView {
    
    /**
     * This method is initialize GUI components for Shop(Tower).
     */
    public ShopView(){
        
    }
    
    /**
     * This method will draw GUI Components. It will used in tower buying, selling and deleting.
     * @param g the Graphics
     * @param sModel the shop model object
     */
    public void draw(ShopModel sModel, Graphics g){ 
        
        g.setColor(new Color(255, 255, 255));
        g.drawRect(sModel.btn_health.x - 5, sModel.btn_health.y -5, 226, 50);
        g.setFont(new Font("Courier New", Font.BOLD, 15));
        g.drawString("L:"+configModel.level, sModel.btn_health.x + 5, sModel.btn_health.height + 10);
        g.drawString("R:"+configModel.waveLap+"/3", sModel.btn_health.x + 55, sModel.btn_health.height + 10);
        g.drawString("K:"+configModel.killed + "/" + configModel.killsToWin, sModel.btn_coins.x + 5, sModel.btn_health.height + 10);
        
        for(int i=0;i<sModel.button.length;i++){
             if(sModel.button[i].contains(configModel.mse)){
                 g.setColor(new Color(255, 255, 255, 100));
                 g.fillRect(sModel.button[i].x, sModel.button[i].y + 15, sModel.button[i].width, sModel.button[i].height);
             }
             
             g.drawRect(sModel.button[0].x - 5, sModel.button[0].y + 10, (sModel.button[0].width * sModel.button.length) + 18, sModel.button[0].height+25);
             
            g.fillRect(sModel.button[i].x, sModel.button[i].y + 15, sModel.button[i].width, sModel.button[i].height);
            g.drawImage(configModel.tileset_res[0], sModel.button[i].x, sModel.button[i].y + 15, sModel.button[i].width, sModel.button[i].height ,null);
            if(sModel.buttonID[i] != configModel.airAir)
                    g.drawImage(configModel.air_level[sModel.buttonID[i]], sModel.button[i].x + sModel.itemIn, sModel.button[i].y + sModel.itemIn + 15, sModel.button[i].width -(sModel.itemIn*2), sModel.button[i].height - (sModel.itemIn*2),null);
            if(sModel.getButtonPrice(i) > 0)
            {
                g.setColor(new Color(255, 255, 255));
                g.setFont(new Font("Courier New", Font.BOLD, 15));
                g.drawString("$"+sModel.getButtonPrice(i), sModel.button[i].x + sModel.itemIn + sModel.getButtonSize()/ 4 - 5 , sModel.button[i].y + sModel.itemIn + sModel.getButtonSize() + 25);
            }
        }
        g.drawImage(configModel.tileset_res[1],sModel.btn_health.x , sModel.btn_health.y + 20, sModel.btn_health.width, sModel.btn_health.height, null);
        g.drawImage(configModel.tileset_res[2],sModel.btn_coins.x, sModel.btn_coins.y + 20, sModel.btn_coins.width, sModel.btn_coins.height, null);
        g.setFont(new Font("Courier New", Font.BOLD, 15));
        g.drawString(""+configModel.health, sModel.btn_health.x + sModel.icon_space, sModel.btn_health.height + 30);
        g.drawString(""+configModel.money, sModel.btn_coins.x + sModel.icon_space, sModel.btn_coins.height + 30);
        
        if(sModel.isHoldsItem()){
            //System.out.println("X:"+configModel.mse.x+" Y:"+configModel.mse.y);
            g.drawImage(configModel.air_level[sModel.getHeldID()], configModel.mse.x - ((sModel.button[0].width -(sModel.itemIn*2))/2) + sModel.itemIn, configModel.mse.y - ((sModel.button[0].width -(sModel.itemIn*2))/2) + sModel.itemIn, sModel.button[0].width -(sModel.itemIn*2), sModel.button[0].height - (sModel.itemIn*2),null);
        }
        
        if(sModel.isTowerInfo()){
            int temp_Height = 100;
            double x = sModel.getButtonPrice(sModel.getTowerID()) * 0.8;
            g.drawRect(sModel.button[0].x - 5, 150, 226, 85);
            g.drawString("Tower ID: "+sModel.getTowerID(), sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 45);
            g.drawString("Tower Price: "+sModel.getButtonPrice(sModel.getTowerID()), sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 60);
            g.drawString("Tower Range: "+configModel.airTowerRanger[sModel.getTowerID()], sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 75);
            g.drawString("Tower Health: "+configModel.Towerhealth[sModel.getTowerID()], sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 90);
            g.drawString("Refund Rate: "+(int)x, sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 105);
            
            g.setColor(new Color(255, 255, 255, 100));            
            Rectangle wx = new Rectangle(sModel.button[0].x - 5, 240, 226, 20);
            if(wx.contains(configModel.mse)){
                 g.setColor(new Color(200, 200, 200));
                 g.fillRect(wx.x, wx.y, wx.width, wx.height);
                 g.setColor(new Color(0, 0, 0));
             } else {
                g.fillRect(wx.x, wx.y, wx.width, wx.height);
                g.setColor(new Color(255, 255, 255));
            }
            g.drawString("Upgrade Level", sModel.btn_health.x + 20 + sModel.icon_space, sModel.btn_health.height + 235);
        }
    }
}
