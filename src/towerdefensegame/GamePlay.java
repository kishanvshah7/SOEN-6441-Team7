/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefensegame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.io.File;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import tdgame.controller.MapValidation;
import tdgame.controller.PlayScreenController;
import tdgame.model.CellContainerModel;
import tdgame.model.GridCellModel;
import tdgame.model.PlayScreenModel;
import tdgame.model.ShopModel;
import tdgame.model.configModel;
import tdgame.view.CellContainerView;
import tdgame.view.GridCellView;
import tdgame.view.PlayScreenView;
import tdgame.view.ShopView;

/**
 * This class initialize the game play screen.
 * @author Rahul K Kikani
 */
public class GamePlay extends JFrame {
    
    private PlayScreenController psCont;
    /**
     * This method will initialize JFrame and set some basic properties(Title, Size, Background Color, Location).
     * @param f Map file which is selected by user from list box.
     * @param w Width of Play screen based on map size
     * @param h Height of Play screen based on map size
     */
    public GamePlay(File f, int w, int h)
    {
        int width = w*40 + 350;
        int height = h*40 + 120;
        this.setTitle("Tower Defence Game");
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setBackground(Color.darkGray);
        init_elements(f);
    }
    
    /**
     * This method will check the Map file and based on the type of map, it will bind the model to the controller and create the play view using the View.
     * @param f Map file which is selected by user from list box.
     */
    public void init_elements(File f)
    {
        this.setLayout(new GridLayout(1, 1, 0, 0));
        
        PlayScreenModel psModel = new PlayScreenModel();
                        boolean temp = psModel.LoadMap(f);
                        if(temp){
                            MapValidation mv = new MapValidation(psModel.getGridCellArray());
                            if(mv.isValid()){
                                System.out.println("Map is Valid");
                                configModel cModel = new configModel(); 
                            
                                psModel.initCellContainerModel();
                                psModel.setGridCellVal();

                                ShopModel sModel = new ShopModel(psModel.getStartX(),psModel.getStartY());
                                ShopView sView = new ShopView();

                                CellContainerView ccView = new CellContainerView();
                                CellContainerModel ccModel = psModel.getCellContainerModel();

                                GridCellView gcView = new GridCellView();
                                GridCellModel[][] gcModel = ccModel.getGcModel();

                                PlayScreenView psView = new PlayScreenView(this);
                                add(psView);
                                psCont = new PlayScreenController(psView, psModel, gcView, gcModel, ccView, ccModel, sView, sModel);
                                psView.setController(getPsCont());
                                psView.startGame();
                                this.setVisible(true);
                            }else{
                                System.out.println("Map Is Invalid");
                                JOptionPane.showMessageDialog(this,"Map is Invalid", null, WIDTH);
                            }  
                        }else{
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "Invalid Map File", null, WIDTH);
                        }
    }

    /**
     * @return the psCont
     */
    public PlayScreenController getPsCont() {
        return psCont;
    }
}
