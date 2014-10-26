/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Graphics;
import tdgame.controller.CellContainerController;
import tdgame.controller.GridCellController;

/**
 *
 * @author Rahul K Kikani
 */
public class CellContainerView {
    public CellContainerController ccCont;
    
    public CellContainerView(CellContainerController ccCont){
        this.ccCont = ccCont;
    }
    
    public void draw(Graphics g){
        //Draw Block
        for(int y=0;y<ccCont.getyC();y++){
            for(int x=0;x<ccCont.getxC();x++){
                ccCont.getgcCont().getDraw(x, y, x, x, g);
            }
        }
        
        for(int y=0;y<ccCont.getyC();y++){
            for(int x=0;x<ccCont.getxC();x++){
                ccCont.getgcCont().getfireRangeOutline(g);
            }
        }
    }
}
