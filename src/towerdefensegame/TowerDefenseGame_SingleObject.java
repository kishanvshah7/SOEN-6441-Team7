/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefensegame;

import tdgame.model.MainScreenModel;
import tdgame.view.MainScreenView;

/**
 *
 * @author Rahul K Kikani
 */
public class TowerDefenseGame_SingleObject {
    private MainScreenView theView;
    private MainScreenModel theModel;
    /** 
    * create an object of SingleObject embedded as a static member of the class itself
    */
   private static TowerDefenseGame_SingleObject instance = new TowerDefenseGame_SingleObject();
   /** 
    * Make the constructor private so that this class cannot be instantiated
    */
   private TowerDefenseGame_SingleObject(){
        this.theView = new MainScreenView();
        this.theModel = new MainScreenModel();
   }
   /**
    * If the instance was not previously created, create it. Then return the instance
    */
   public static TowerDefenseGame_SingleObject getInstance(){
      if (instance == null)
      {
        instance = new TowerDefenseGame_SingleObject();
      }
      return instance;
   }

    /**
     * @return the theView
     */
    public MainScreenView getTheView() {
        return theView;
    }

    /**
     * @return the theModel
     */
    public MainScreenModel getTheModel() {
        return theModel;
    }
}
