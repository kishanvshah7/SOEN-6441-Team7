/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import tdgame.model.MapCreationModel;
import tdgame.view.MapCreationView;

/**
 *
 * @author Rahul K Kikani
 */
public class MapCreationController {
    
    MapCreationView theView;
    MapCreationModel theModel;
    
    MapBoxController mbCon;
    MainScreenController msCon;
    
    public MapCreationController(){
        
    }
    
    public MapCreationController(MapCreationView view, MapCreationModel model, MainScreenController msCon){
        System.out.println("MapCreationController");
        this.theView = view;
        this.theModel = model;
        this.msCon = msCon;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Set Grid")){
                    int xC = theView.getXBlockInput();
                    int yC = theView.getYBlockInput();
                    String errMsg = "";
                    if(xC == 0 || yC ==0){
                        errMsg += "Input is Invalid.";
                    }
                    if(xC < 7){
                        errMsg += "Sorry, Your X Block Size is smaller than 7. Please use big value.\n";
                    }
                    if(yC < 7){
                        errMsg += "Sorry, Your Y Block Size is smaller than 7. Please use big value.\n";
                    }
                    if(xC > 20){
                        errMsg += "Sorry, Your X Block Size is bigger than 20. Please use small value.\n";
                    }
                    if(yC > 15){
                        errMsg += "Sorry, Your Y Block Size is bigger than 15. Please use small value.\n";
                    }
                    
                    if(errMsg.equals("")){
                        theView.setdisabledloadMapBtn();
                        System.out.println("Map Grid is Created.");
                        mbCon = new MapBoxController();
                        mbCon.setXBlockCount(xC);
                        mbCon.setYBlockCount(yC);
                        mbCon.setGridArray();
                        theView.addGridMap(mbCon);
                        theView.disableSubmitButton();
                    }
                    else
                    {
                        theView.displayMessage(errMsg);
                    }
                }
                
                if(tempBtnStr.equals("Entry Point")){
                    mbCon.setEntryPointFlag();
                }
                
                if(tempBtnStr.equals("Draw Path")){
                    for(int y=0;y<mbCon.getYBlockCount();y++){
                        for(int x=0;x<mbCon.getXBlockCount();x++){                
                            System.out.print(mbCon.getmapGirdArrayElement(y, x)+" ");
                        }
                        System.out.print("\n");
                    }
                    mbCon.setPathPointFlag();
                }
                
                if(tempBtnStr.equals("Exit Point")){
                    mbCon.setExitPointFlag();
                }
                
                if(tempBtnStr.equals("Load Map")){
                    System.out.println("Load Map: Clicked");
                    final JFileChooser  fileDialog = new JFileChooser();
                    FileFilter filter = new FileNameExtensionFilter("TDMap file", "txt");
                    fileDialog.addChoosableFileFilter(filter);
                    int returnVal = fileDialog.showOpenDialog(theView);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                       File file = fileDialog.getSelectedFile();
                       theView.setdisabledsubmitBtn();
                       mbCon = new MapBoxController();
                       if(theModel.readFile(mbCon, file.getName(), file)){
                        System.out.println("Map Grid is Created from file.");
                        theView.addGridMap(mbCon);
                        theView.disableSubmitButton();
                        theView.disableLoadButton();
                       }else{
                           theView.displayMessage("Invalid Map File");
                       }
                    }
                    else{
                       //theView.displayMessage("Open command cancelled by user." );           
                    } 
                }

                if(tempBtnStr.equals("Save Map")){
                    System.out.println("Save Clicked");
                    if(mbCon.validPath(mbCon.getMapGirdArray()).equals("Done")){
                        String file_name = theView.getFileName();
                        System.out.println("file "+file_name);
                        if(file_name == null){
                            
                        }else if(file_name.isEmpty()){
                             Date date = new Date();
                             SimpleDateFormat ft =  new SimpleDateFormat ("dd.MM.yyyy hh_mm_ss a");
                            System.out.println(""+ft.format(date));
                            mbCon.saveMap(ft.format(date));
                            theView.displayMessage("Thank You, Your Map is successfully saved with "+ft.format(date));
                            theView.dispose();
                            msCon.setTopEnabled();
                        }else{
                            mbCon.saveMap(file_name);
                            theView.displayMessage("Thank You, Your Map is successfully saved with "+file_name);
                            theView.dispose();
                            msCon.setTopEnabled();
                        }
                    }else{
                        theView.displayMessage("Sorry, Your Path is invalid.");
                    }
                }

                if(tempBtnStr.equals("Exit")){
                    theView.dispose();
                    msCon.setTopEnabled();
                }
            }
        }
    }
    
    public void startMapCreation(){
        this.theView.setVisible(true);
    }
    
    public void setXBlockCount(int x){
        theModel.setXBlockCount(x);
    }
    public void setYBlockCount(int y){
        theModel.setYBlockCount(y);
    }
    public int getXBlockCount(){
        return theModel.getXBlockCount();
    }
    public int getYBlockCount(){
        return theModel.getYBlockCount();
    }
}
