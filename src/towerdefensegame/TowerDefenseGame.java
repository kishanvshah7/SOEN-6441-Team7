/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefensegame;

import tdgame.controller.MainScreenController;
/**
 * Main Class to start game.
 * @author Rahul K Kikani
 */
public class TowerDefenseGame {

    /**
     * Main Method to initialize the game View, Model and Controller of first screen of game.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        TowerDefenseGame_SingleObject Singleobject = TowerDefenseGame_SingleObject.getInstance();

        MainScreenController theController = new MainScreenController(Singleobject.getTheView(), Singleobject.getTheModel());
        Singleobject.getTheView().setVisible(true);
    }
    
}
