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
import tdgame.controller.CellContainerController;
import tdgame.controller.GridCellController;
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
 *
 * @author Rahul K Kikani
 */
public class GamePlay extends JFrame {
    
    public GamePlay(File f, int w, int h)
    {
        int width = w*40 + 300;
        int height = h*40 + 90;
        this.setTitle("Tower Defence Game");
        this.setSize(width,height);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setBackground(Color.darkGray);
        init_elements(f);
    }
    
    public void init_elements(File f)
    {
        this.setLayout(new GridLayout(1, 1, 0, 0));
        
        PlayScreenModel psModel = new PlayScreenModel();
                        boolean temp = psModel.LoadMap(f);
                        if(temp){
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
                            PlayScreenController psCont = new PlayScreenController(psView, psModel, gcView, gcModel, ccView, ccModel, sView, sModel);
                            psView.setController(psCont);
                            psView.startGame();
                         this.setVisible(true);   
                        }else{
                            this.dispose();
                            JOptionPane.showMessageDialog(this, "Invalid Map File", null, WIDTH);
                        }
    }
    
    /**
     * @param args the command line arguments
     */
    //public static void main(String[] args) {
    //    GamePlay start_game  = new GamePlay("MapFiles/Rahul.txt");
    //}
    
    public void paintComponent(Graphics g){
        System.out.println("Main File");
    } 
    
}
