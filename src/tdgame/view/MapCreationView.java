/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.util.Observable;
import java.util.Observer;
import javax.swing.*;
import tdgame.controller.MapBoxController;
import tdgame.controller.MapCreationController;


/**
 * This is GUI class of Map Creation Module.
 * @author Rahul K Kikani
 */
public class MapCreationView extends JFrame{
    
    JLabel xLabel;
    JLabel yLabel;
    
    JTextField xBlock;
    JTextField yBlock;
    
    JButton entryPBtn;
    JButton exitPBtn;
    JButton pathBtn;
    JButton submitBtn;
    JButton loadMapBtn;
    JButton saveMapBtn;
    JButton exitBtn;
    
    JPanel map_object_panel;
    JPanel map_grid_panel;
    
    MapBoxController mbCont;
    
    /**
     * This method is initialize GUI components for Map Creation Screen.
     */
    public MapCreationView(){
        this.setTitle("Map Creation Window");
        this.setSize(1000,700);
        this.setLocationRelativeTo(null);
        this.setBackground(Color.BLUE);
        this.setLayout(new BorderLayout(5, 5));
        
        map_object_panel = new JPanel();
        map_grid_panel = new JPanel();
        map_grid_panel.setBackground(Color.DARK_GRAY);
        
        xLabel = new JLabel("xBlock: ");
        yLabel = new JLabel("yBlock: ");
        
        xBlock = new JTextField("5",20);
        yBlock = new JTextField("5",20);
        
        submitBtn = new JButton("Set Grid");
        entryPBtn = new JButton("Entry Point");
        pathBtn = new JButton("Draw Path");
        exitPBtn = new JButton("Exit Point");
        saveMapBtn = new JButton("Save Map");
        loadMapBtn = new JButton("Load Map");
        exitBtn = new JButton("Exit");
        
        entryPBtn.setEnabled(false);
        pathBtn.setEnabled(false);
        exitPBtn.setEnabled(false);
        saveMapBtn.setEnabled(false);
        
        map_object_panel.setLayout(new GridLayout(0,6,5,5));
        map_object_panel.setBackground(Color.GRAY);
        
        map_object_panel.add(xLabel);
        map_object_panel.add(xBlock);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(entryPBtn);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(loadMapBtn);
        
        map_object_panel.add(yLabel);
        map_object_panel.add(yBlock);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(pathBtn);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(saveMapBtn);
        
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(submitBtn);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(exitPBtn);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(exitBtn);
        
        this.add(map_object_panel,BorderLayout.NORTH);
        this.add(map_grid_panel,BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    /**
     * This method will return value of input box x coordinate.
     * @return the x coordinate
     */
    public int getXBlockInput(){
        if(xBlock.getText().equals("")){
            return 0;
        }
        else{
            int temp = 0;
            try{
                temp  = Integer.parseInt(xBlock.getText());
            } catch(Exception e){
                System.out.println("Inter exception");
                temp = 0;
            }
            return temp;
        }
    }
    
    /**
     * This method will return value of input box y coordinate.
     * @return the y coordinate
     */
    public int getYBlockInput(){
        if(yBlock.getText().equals("")){
            return 0;
        }
        else{
            int temp = 0;
            try{
                temp  = Integer.parseInt(yBlock.getText());
            } catch(Exception e){
                System.out.println("Inter exception");
                temp = 0;
            }
            return temp;
        }
    }
    
    /**
     * This method will add gridMap in Map Creation Screen.
     * @param  mbCont the controller object of map box controller.
     * @return flag
     */
    public boolean addGridMap(MapBoxController mbCont){
        this.mbCont = mbCont;
        System.out.println("xbc"+mbCont.getXBlockCount());
        MapBoxView x = this.mbCont.getView();
        map_grid_panel.add(x);
        this.mbCont.setBtnGridClickListner();
        map_grid_panel.validate();
        return true;
    }
    
    /**
     * This method will add button listener.
     * @param ListnerForButton the ActionListener
     */
    public void addButtonClickEventListner(ActionListener ListnerForButton){
        submitBtn.addActionListener(ListnerForButton);
        entryPBtn.addActionListener(ListnerForButton);
        pathBtn.addActionListener(ListnerForButton);
        exitPBtn.addActionListener(ListnerForButton);
        loadMapBtn.addActionListener(ListnerForButton);
        saveMapBtn.addActionListener(ListnerForButton);
        exitBtn.addActionListener(ListnerForButton);
    }
    
    /**
     * This method is being used to modify button properties.
     */
    public void disableSubmitButton(){
        submitBtn.setEnabled(false);
        xBlock.setEnabled(false);
        yBlock.setEnabled(false);
        entryPBtn.setEnabled(true);
        pathBtn.setEnabled(true);
        exitPBtn.setEnabled(true);
        saveMapBtn.setEnabled(true);
    }
    
    /**
     * this method will disable load map button
     */
    public void disableLoadButton(){
        loadMapBtn.setEnabled(false);
    }
    
    /**
     * This method create Message show dialog box.
     * @param str message string.
     */
    public void displayMessage(String str){
        JOptionPane.showMessageDialog(this, str);
    }
    
    /**
     * this method will disable load map button
     */
    public void setdisabledloadMapBtn(){
       loadMapBtn.setEnabled(false);
    }
    
    /**
     * this method will disable submit button
     */
    public void setdisabledsubmitBtn(){
       submitBtn.setEnabled(false);
    }
    
    /**
     * this method will take file name input
     * @return name of file
     */
    public String getFileName(){
        return JOptionPane.showInputDialog(this, "Enter File Name");
    }
}
