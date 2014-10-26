/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.Graphics;
import tdgame.model.CellContainerModel;
import tdgame.model.PlayScreenModel;
import tdgame.view.PlayScreenView;

/**
 *
 * @author Rahul K Kikani
 */
public class PlayScreenController {

    PlayScreenModel theModel;
    PlayScreenView theView;
    CellContainerModel ccModel;
    private CellContainerController ccCont;
    
    PlayScreenController(PlayScreenView psView, PlayScreenModel psModel) {
        theModel = psModel;
        theView = psView;
        ccModel = theModel.getCellContainerModel();
    }

    public void logic() {
        if(ccCont != null)
        {
            for(int y=0;y<ccModel.getyC();y++){
                for(int x=0;x<ccModel.getxC();x++){
                    ccCont.getgcCont().logic(x,y);
                    //block[y][x].physic();
                }
            }
        }
    }

    /**
     * @param ccCont the ccCont to set
     */
    public void setCcCont(CellContainerController ccCont) {
        this.ccCont = ccCont;
    }
    
    public void getccDraw(Graphics g){
       if(ccCont !=null)
            ccCont.getDraw(g);
    }
}
