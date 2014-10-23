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
import javax.swing.*;
import tdgame.controller.MapBoxController;
import tdgame.controller.MapCreationController;


/**
 *
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
    JButton saveMapBtn;
    JButton exitBtn;
    
    JPanel map_object_panel;
    JPanel map_grid_panel;
    
    //MapCreationController mcCont;
    MapBoxController mbCont;
    
    public MapCreationView(){
        this.setTitle("Map Creation Window");
        this.setSize(800,700);
        this.setBackground(Color.BLUE);
        this.setLayout(new BorderLayout(5, 5));
        //setUndecorated(true);
        //getRootPane().setWindowDecorationStyle(JRootPane.NONE);
        
        map_object_panel = new JPanel();
        map_grid_panel = new JPanel();
        map_grid_panel.setBackground(Color.DARK_GRAY);
        
        xLabel = new JLabel("xBlock: ");
        yLabel = new JLabel("yBlock: ");
        
        xBlock = new JTextField("5",20);
        yBlock = new JTextField("5");
        
        submitBtn = new JButton("Set Grid");
        entryPBtn = new JButton("Entry Point");
        pathBtn = new JButton("Draw Path");
        exitPBtn = new JButton("Exit Point");
        saveMapBtn = new JButton("Save Map");
        exitBtn = new JButton("Exit");
        
        entryPBtn.setEnabled(false);
        pathBtn.setEnabled(false);
        exitPBtn.setEnabled(false);

        
        map_object_panel.setLayout(new GridLayout(0,6,5,5));
        map_object_panel.setBackground(Color.GRAY);
        
        map_object_panel.add(xLabel);
        map_object_panel.add(xBlock);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(entryPBtn);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(saveMapBtn);
        
        map_object_panel.add(yLabel);
        map_object_panel.add(yBlock);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(pathBtn);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(exitBtn);
        
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(submitBtn);
        map_object_panel.add(new JLabel(""));
        map_object_panel.add(exitPBtn);
        
        this.add(map_object_panel,BorderLayout.NORTH);
        this.add(map_grid_panel,BorderLayout.CENTER);
        this.setVisible(true);
    }
    
    public int getXBlockInput(){
        if(xBlock.getText().equals("")){
            return 0;
        }
        else
            return Integer.parseInt(xBlock.getText());
    }
    
    public int getYBlockInput(){
        if(yBlock.getText().equals("")){
            return 0;
        }
        else
            return Integer.parseInt(yBlock.getText());
    }
    
    public void addGridMap(MapBoxController mbCont){
        this.mbCont = mbCont;
        System.out.println("xbc"+mbCont.getXBlockCount());
        MapBoxView x = this.mbCont.getView();
        map_grid_panel.add(x);
        this.mbCont.setBtnGridClickListner();
        map_grid_panel.validate();
    }
    
    public void addButtonClickEventListner(ActionListener ListnerForButton){
        submitBtn.addActionListener(ListnerForButton);
        entryPBtn.addActionListener(ListnerForButton);
        pathBtn.addActionListener(ListnerForButton);
        exitPBtn.addActionListener(ListnerForButton);
        saveMapBtn.addActionListener(ListnerForButton);
        exitBtn.addActionListener(ListnerForButton);
    }
    
    public void disableSubmitButton(){
        submitBtn.setEnabled(false);
        xBlock.setEnabled(false);
        yBlock.setEnabled(false);
        entryPBtn.setEnabled(true);
        pathBtn.setEnabled(true);
        exitPBtn.setEnabled(true);
        saveMapBtn.setEnabled(true);
    }
    
    public void displayMessage(String str){
        JOptionPane.showMessageDialog(this, str);
    }
    
    public String getFileName(){
        return JOptionPane.showInputDialog(this, "Enter File Name");
    }
}
