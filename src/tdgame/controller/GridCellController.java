/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.Graphics;
import tdgame.model.GridCellModel;
import tdgame.view.GridCellView;

/**
 * This Class will bind and initialize Model-View of Play Screen Module.
 * @author Rahul K Kikani
 */
public class GridCellController {

    GridCellView theView;
    GridCellModel[][] theModel;
   
    /**
     * This method will set GridCellView and GridCellModel
     * @param gcView the GridCellView
     * @param gcModel the GridCellModel
     */
    public GridCellController(GridCellView gcView, GridCellModel[][] gcModel) {
        this.theModel = gcModel;
        this.theView = gcView;
    }
    
    /**
     * This method will call fireRangeOutline() method of Cell Container view
     * @param gcModelObj the GridCellModel
     * @param g the Graphics
     */
    public void getfireRangeOutline(GridCellModel gcModelObj, Graphics g){
        this.theView.fireRangeOutline(gcModelObj, g);
    }
    
    /**
     * This method will call draw() method of Cell Container view
     * @param g the Graphics
     * @param gcModelObj the GridCellModel
     */
    public void getDraw(GridCellModel gcModelObj, Graphics g) {
        this.theView.draw(gcModelObj,g);
    }
}
