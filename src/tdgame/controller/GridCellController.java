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
    GridCellModel theModel;
    
    GridCellController(int i, int i0, int cellPixels, int cellPixels0, int groundGrass, int airAir) {
        
    }

    GridCellController() {
        
    }
    
    public void getDraw(int x, int y, int width, int height, Graphics g){
        this.theView.draw(x, y, width, height, g);
    }
    
    public void getfireRangeOutline(Graphics g){
        this.theView.fireRangeOutline(g);
    }
    
    public void logic(int x, int y){
        System.out.println("GridCellController.logic(): X:"+x+" Y:"+y);
    }
}
