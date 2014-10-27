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
 *
 * @author Rahul K Kikani
 */
public class GridCellController {

    GridCellView theView;
    GridCellModel[][] theModel;
    
    public GridCellController(int i, int i0, int cellPixels, int cellPixels0, int groundGrass, int airAir) {
        
    }

    public GridCellController(GridCellView gcView, GridCellModel[][] gcModel) {
        this.theModel = gcModel;
        this.theView = gcView;
    }
    
    public void getfireRangeOutline(GridCellModel gcModelObj, Graphics g){
        this.theView.fireRangeOutline(gcModelObj, g);
    }
    
    public void logic(int x, int y){
        //System.out.println("GridCellController.logic(): X:"+x+" Y:"+y);
    }

    public void getDraw(GridCellModel gcModelObj, Graphics g) {
        this.theView.draw(gcModelObj,g);
    }
}
