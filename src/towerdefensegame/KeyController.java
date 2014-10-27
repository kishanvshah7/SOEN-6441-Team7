package towerdefensegame;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import tdgame.controller.ShopController;
import tdgame.model.configModel;

/**
 * This class will take input from user via mouse and trigger the appropriate function.
 * @author Rahul K Kikani
 */
public class KeyController implements MouseMotionListener, MouseListener {

    /**
     * This Method will listen mouse dragged movement and store x,y coordinates in point variable.
     * @param e the MouseEvent
     */
    public void mouseDragged(MouseEvent e) {
        //configModel.mse = new Point((e.getX()) + 10, e.getY() + 20);
    }

    /**
     * This Method will listen mouse moved movement and store x,y coordinates in point variable.
     * @param e the MouseEvent
     */
    public void mouseMoved(MouseEvent e) {
        configModel.mse = new Point((e.getX()) - 4, e.getY() - 25);
    }

    
    public void mouseClicked(MouseEvent e) {
        
    }

    /**
     * This Method will listen mouse pressed movement and call appropriate method;
     * @param e the MouseEvent
     */
    public void mousePressed(MouseEvent e) {
        if(e.getButton() == MouseEvent.BUTTON1)
	    {
	      ShopController.click(1);
	    }	    
	    else if(e.getButton() == MouseEvent.BUTTON3)
	    {
	      ShopController.click(0);
	    }
        
    }

    
    public void mouseReleased(MouseEvent e) {
        
    }

    
    public void mouseEntered(MouseEvent e) {
        
    }

    
    public void mouseExited(MouseEvent e) {
        
    }
    
}
