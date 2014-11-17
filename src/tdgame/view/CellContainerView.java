/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.Graphics;
import tdgame.controller.CellContainerController;

/**
 * This is GUI class of Cell Container Module.
 * @author Rahul K Kikani
 */
public class CellContainerView {
    private CellContainerController ccCont;
    
    /**
     * This is constructor.
     */
    public CellContainerView(){
    }
    
    /**
     * This method will draw GUI Components.
     * @param g the Graphics
     */
    public void draw(Graphics g){
        //Draw Block
        for(int y=0;y<ccCont.getyC();y++){
            for(int x=0;x<ccCont.getxC();x++){
                ccCont.getgcCont().getDraw(ccCont.getgcModelObj(y, x),g);
            }
        }
        
        for(int y=0;y<ccCont.getyC();y++){
            for(int x=0;x<ccCont.getxC();x++){
                ccCont.getgcCont().getfireRangeOutline(ccCont.getgcModelObj(y, x), g);
            }
        }
    }

    /**
     * @param ccCont the ccCont to set
     */
    public void setCcCont(CellContainerController ccCont) {
        this.ccCont = ccCont;
    }
}
