/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import tdgame.model.ShopModel;
import tdgame.model.configModel;

/**
 *
 * @author Rahul K Kikani
 */
public class ShopView {
    
    public ShopView(){
        
    }
    
    public void draw(ShopModel sModel, Graphics g){ 
        
        for(int i=0;i<sModel.button.length;i++){
             if(sModel.button[i].contains(configModel.mse)){
                 g.setColor(new Color(255, 255, 255, 100));
                 g.fillRect(sModel.button[i].x, sModel.button[i].y, sModel.button[i].width, sModel.button[i].height);
             }
             
             g.drawRect(sModel.button[0].x - 5, sModel.button[0].y -5, (sModel.button[0].width * sModel.button.length) + 18, sModel.button[0].height+9);
             
            g.fillRect(sModel.button[i].x, sModel.button[i].y, sModel.button[i].width, sModel.button[i].height);
            g.drawImage(configModel.tileset_res[0], sModel.button[i].x, sModel.button[i].y, sModel.button[i].width, sModel.button[i].height ,null);
            if(sModel.buttonID[i] != configModel.airAir)
                    g.drawImage(configModel.air_level[sModel.buttonID[i]], sModel.button[i].x + sModel.itemIn, sModel.button[i].y + sModel.itemIn, sModel.button[i].width -(sModel.itemIn*2), sModel.button[i].height - (sModel.itemIn*2),null);
            if(sModel.getButtonPrice(i) > 0)
            {
                g.setColor(new Color(255, 255, 255));
                g.setFont(new Font("Courier New", Font.BOLD, 15));
                g.drawString("$"+sModel.getButtonPrice(i), sModel.button[i].x + sModel.itemIn, sModel.button[i].y + sModel.itemIn + 10);
            }
        }
        g.drawImage(configModel.tileset_res[1],sModel.btn_health.x , sModel.btn_health.y, sModel.btn_health.width, sModel.btn_health.height, null);
        g.drawImage(configModel.tileset_res[2],sModel.btn_coins.x, sModel.btn_coins.y, sModel.btn_coins.width, sModel.btn_coins.height, null);
        g.setFont(new Font("Courier New", Font.BOLD, 15));
        g.drawString(""+sModel.getHealth(), sModel.btn_health.x + sModel.icon_space, sModel.btn_health.height + 10);
        g.drawString(""+sModel.getMoney(), sModel.btn_coins.x + sModel.icon_space, sModel.btn_coins.height + 10);
        
        if(sModel.isHoldsItem()){
            //System.out.println("X:"+configModel.mse.x+" Y:"+configModel.mse.y);
            g.drawImage(configModel.air_level[sModel.getHeldID()], configModel.mse.x - ((sModel.button[0].width -(sModel.itemIn*2))/2) + sModel.itemIn, configModel.mse.y - ((sModel.button[0].width -(sModel.itemIn*2))/2) + sModel.itemIn, sModel.button[0].width -(sModel.itemIn*2), sModel.button[0].height - (sModel.itemIn*2),null);
        }
        
        if(sModel.isTowerInfo()){
            int temp_Height = 100;
            double x = sModel.getButtonPrice(sModel.getTowerID()) * 0.8;
            g.drawRect(sModel.button[0].x - 5, 115, 226, 85);
            g.drawString("Tower ID: "+sModel.getTowerID(), sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 10);
            g.drawString("Tower Price: "+sModel.getButtonPrice(sModel.getTowerID()), sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 25);
            g.drawString("Tower Range: "+configModel.airTowerRanger[sModel.getTowerID()], sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 40);
            g.drawString("Tower Health: "+configModel.Towerhealth[sModel.getTowerID()], sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 55);
            g.drawString("Refund Rate: "+(int)x, sModel.btn_health.x - 30 + sModel.icon_space, sModel.btn_health.height + temp_Height + 70);
        }
    }
}
