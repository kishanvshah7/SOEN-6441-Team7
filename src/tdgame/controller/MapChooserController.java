/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import tdgame.model.CellContainerModel;
import tdgame.model.GridCellModel;
import tdgame.model.MapChooserModel;
import tdgame.model.PlayScreenModel;
import tdgame.view.CellContainerView;
import tdgame.view.GridCellView;
import tdgame.view.MapChooserView;
import tdgame.view.PlayScreenFrame;
import tdgame.view.PlayScreenView;

/**
 *
 * @author Rahul K Kikani
 */
public class MapChooserController {

    MapChooserView theView = new MapChooserView();
    MapChooserModel theModel = new MapChooserModel();
    
    MapChooserController(MapChooserView mcView, MapChooserModel mcModel) {
        this.theView = mcView;
        this.theModel = mcModel;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    public String[] getMapFileList(){
        return theModel.getMapFileList();
    }
    
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Let's Play")){
                    if(theView.getSelectedFile().equals("NONE")){
                        theView.displayMessage("Please Select At least one file.");
                    }else{
                        PlayScreenModel psModel = new PlayScreenModel();
                        boolean temp = psModel.LoadMap(new File("MapFiles/"+theView.getSelectedFile()));
                        if(temp){
                            psModel.initCellContainerModel();
                            CellContainerModel ccModel = psModel.getCellContainerModel();
                            CellContainerController ccCont = new CellContainerController();
                            ccCont.setCCModel(ccModel);
                            
                            GridCellController gcCont = new GridCellController();
                            ccCont.setgcCont(gcCont);
                            
                            GridCellView gcView = new GridCellView();
                            
                            CellContainerView ccView = new CellContainerView(ccCont);
                            
                            PlayScreenView psView = new PlayScreenView();
                            
                            PlayScreenFrame psf = new PlayScreenFrame(psView);
                            PlayScreenController psCont = new PlayScreenController(psView, psModel);
                            psCont.setCcCont(ccCont);
                            psView.setController(psCont);
                            
                            
                        }else{
                            System.out.println("Map is not loaded.");
                        }
                        theView.dispose();
                        System.out.println(" : "+theView.getSelectedFile());
                    }
                }
            }
        }
    }
}
