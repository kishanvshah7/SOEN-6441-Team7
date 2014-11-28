/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tdgame.controller;

import extra.mapReader;
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
import towerdefensegame.LogGenerator;

/**
 * This Class will bind and initialize Model-View of Main Creation Module.
 *
 * @author Rahul K Kikani
 */
public class MapCreationController {

    MapCreationView theView;
    MapCreationModel theModel;

    MapBoxController mbCon;
    MainScreenController msCon;

    /**
     * This method is constructor.
     */
    public MapCreationController() {

    }

    /**
     * This method will bind and initialize Model-View of Map Creation Model. It
     * will also initialize Main Screen Controller object which will be used in
     * this class.
     *
     * @param view view object of Map Creation
     * @param model model object of Map Creation
     * @param msCon Controller object of Main Screen
     */
    public MapCreationController(MapCreationView view, MapCreationModel model, MainScreenController msCon) {
        System.out.println("MapCreationController");
        this.theView = view;
        this.theModel = model;
        this.msCon = msCon;
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }

    /**
     * This class will perform action based on buttons pressed in Map Creation
     * GUI.
     */
    class ButtonActionDetector implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if (e.getSource() instanceof JButton) {
                if (tempBtnStr.equals("Set Grid")) {
                    int xC = theView.getXBlockInput();
                    int yC = theView.getYBlockInput();
                    String errMsg = "";
                    if (xC == 0 || yC == 0) {
                        errMsg += "Input is Invalid.";
                    }
                    if (xC < 7) {
                        errMsg += "Sorry, Your X Block Size is smaller than 7. Please use big value.\n";
                    }
                    if (yC < 7) {
                        errMsg += "Sorry, Your Y Block Size is smaller than 7. Please use big value.\n";
                    }
                    if (xC > 20) {
                        errMsg += "Sorry, Your X Block Size is bigger than 20. Please use small value.\n";
                    }
                    if (yC > 15) {
                        errMsg += "Sorry, Your Y Block Size is bigger than 15. Please use small value.\n";
                    }

                    if (errMsg.equals("")) {
                        theView.setdisabledloadMapBtn();
                        System.out.println("Map Grid is Created.");
                        mbCon = new MapBoxController();
                        mbCon.setXBlockCount(xC);
                        mbCon.setYBlockCount(yC);
                        mbCon.setGridArray();
                        theView.addGridMap(mbCon);
                        theView.disableSubmitButton();
                    } else {
                        LogGenerator.addLog("Invalid Map x,y parameter");
                        theView.displayMessage(errMsg);
                    }
                }

                if (tempBtnStr.equals("Entry Point")) {
                    mbCon.setEntryPointFlag();
                }

                if (tempBtnStr.equals("Draw Path")) {
                    mbCon.setPathPointFlag();
                }

                if (tempBtnStr.equals("Exit Point")) {
                    mbCon.setExitPointFlag();
                }

                if (tempBtnStr.equals("Load Map")) {
                    LogGenerator.addLog("Load Map Initialized.");
                    System.out.println("Load Map: Clicked");
                    final JFileChooser fileDialog = new JFileChooser();
                    fileDialog.setCurrentDirectory(new File("MapFiles"));
                    FileFilter filter = new FileNameExtensionFilter("TDMap file", "txt");
                    fileDialog.addChoosableFileFilter(filter);
                    int returnVal = fileDialog.showOpenDialog(theView);
                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        File file = fileDialog.getSelectedFile();
                        theView.setdisabledsubmitBtn();
                        mbCon = new MapBoxController();
                        LogGenerator.addLog("MapFile Selected By user : " + file.getName());
                        if (theModel.readFile_XML(mbCon, file.getName(), file)) {
                            System.out.println("Map Grid is Created from file.");
                            theView.addGridMap(mbCon);
                            theView.disableSubmitButton();
                            theView.disableLoadButton();
                            LogGenerator.addLog("Loadded Map is Valid");
                        } else {
                            LogGenerator.addLog("Loadded Map is inalid");
                            theView.displayMessage("Invalid Map File");
                        }
                    } else {
                        //theView.displayMessage("Open command cancelled by user." );           
                    }
                }

                if (tempBtnStr.equals("Save Map")) {
                    LogGenerator.addLog("User wants to save map.");
                    System.out.println("Save Clicked");
                    if (mbCon.validPath(mbCon.getMapGirdArray()).equals("Done")) {
                        LogGenerator.addLog("User edited map is valid.");
                        if (mapReader.mapFileEditFlag) {
                                Date date = new Date();
                                SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy hh_mm_ss a");
                                System.out.println("" + ft.format(date));
                                mbCon.saveMap_XML(ft.format(date),"Loaded");
                                theView.displayMessage("Thank You, Your MapXML is successfully saved with " + ft.format(date));
                                theView.dispose();
                                msCon.setTopEnabled();
                                LogGenerator.addLog("MapFile saved as : " + ft.format(date));
                                
                        } else {
                            String file_name = theView.getFileName();
                            System.out.println("file " + file_name);
                            if (file_name == null) {

                            } else if (file_name.isEmpty()) {
                                Date date = new Date();
                                SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy hh_mm_ss a");
                                System.out.println("" + ft.format(date));
                                mbCon.saveMap_XML(ft.format(date), "New");
                                theView.displayMessage("Thank You, Your Map is successfully saved with " + ft.format(date));
                                theView.dispose();
                                msCon.setTopEnabled();
                                LogGenerator.addLog("MapFile saved as : " + ft.format(date));
                            } else {
                                mbCon.saveMap_XML(file_name,"New");
                                theView.displayMessage("Thank You, Your Map is successfully saved with " + file_name);
                                theView.dispose();
                                msCon.setTopEnabled();
                                LogGenerator.addLog("MapFile saved as : " + file_name);
                            }
                        }
                    } else {
                        LogGenerator.addLog("User edited map is not valid.");
                        theView.displayMessage("Sorry, Your Path is invalid.");
                    }
                }

                if (tempBtnStr.equals("Exit")) {
                    theView.displayMessage("Exit from Map Creation window.");
                    theView.dispose();
                    msCon.setTopEnabled();
                }
            }
        }
    }

    /**
     * This method will call setVisible() from View.
     */
    public void startMapCreation() {
        this.theView.setVisible(true);
    }

    /**
     * This method will call setXBlockCount(x) from Model.
     *
     * @param x the x to set
     */
    public void setXBlockCount(int x) {
        theModel.setXBlockCount(x);
    }

    /**
     * This method will call setYBlockCount(y) from Model.
     *
     * @param y the y to set
     */
    public void setYBlockCount(int y) {
        theModel.setYBlockCount(y);
    }

    /**
     * This method will call getXBlockCount() from Model.
     *
     * @return the x to get
     */
    public int getXBlockCount() {
        return theModel.getXBlockCount();
    }

    /**
     * This method will call getYBlockCount() from Model.
     *
     * @return the y to get
     */
    public int getYBlockCount() {
        return theModel.getYBlockCount();
    }
}
