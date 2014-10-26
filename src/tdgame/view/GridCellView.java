/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Rahul K Kikani
 */
public class GridCellView {
    
    public GridCellView(){
        
    }
    
    public void draw(int x,int y,int width,int height,Graphics g){
        System.out.println("abcd");
        //g.drawImage(ScreenMap.ground_level[gID], x, y, width, height, null);
        g.drawRect(x, y, width, height);
       //if(airID != ValueConfig.airAir){
       //    g.drawImage(ScreenMap.air_level[airID], x, y, width, height, null);
       //}
    }
    
    public void fireRangeOutline(Graphics g){
        System.out.println("fireRangeOutline()");
    }
}
