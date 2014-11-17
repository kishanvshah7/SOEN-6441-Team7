/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 * This is GUI class of Map Chooser Module.
 * @author Rahul K Kikani
 */
public class MapChooserView extends JFrame {
    
    private JButton showButton;
    private JList fruitList;
    MainScreenView msView;
    
    /**
     * This method is constructor.
     */
    public MapChooserView(){
    }
    
    /**
     * This method will initialize GUI components for Map Chooser.
     * @param msView the view object Main Screen
     * @param fileList array of map files
     */
    public MapChooserView(MainScreenView msView, String[] fileList){
        this.setTitle("Select Your Map");
        this.setSize(200,300);
        this.msView = msView;
        listMapFiles(fileList);
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
    
    /**
     * this method will set top properties of JFrame.
     * @param f the boolean value
     */
    public void setMSTOp(boolean f){
        msView.setAlwaysOnTop(f);
    }
    
    /**
     * this method will read all files from MapFiles folder and list in JList Box.
     * @param fileList the array of map files
     */
    public void listMapFiles(String[] fileList){
        
        this.setLayout(new BorderLayout(5, 5));
        final DefaultListModel fruitsName = new DefaultListModel();

        for(int i=0;i<fileList.length;i++){
            fruitsName.addElement(fileList[i]);
        }

        fruitList = new JList(fruitsName);
        fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fruitList.setSelectedIndex(0);
        fruitList.setVisibleRowCount(3);        

        JScrollPane fruitListScrollPane = new JScrollPane(fruitList);    

        showButton = new JButton("Let's Play");
        this.add(fruitListScrollPane,BorderLayout.CENTER);  
        this.add(showButton,BorderLayout.SOUTH);
    }
    /**
     * this method will add button action listener to button.
     * @param ListnerForButton the ActionListener
     */
    public void addButtonClickEventListner(ActionListener ListnerForButton){
        showButton.addActionListener(ListnerForButton);
    }
    
    /**
     * This method will return the name of file that selected by user.
     * @return the file name
     */
    public String getSelectedFile(){
        if (fruitList.getSelectedIndex() != -1) {                     
                 return fruitList.getSelectedValue().toString();
        }
        else
            return "NONE";
    }
    
    /**
     * This method create Message show dialog box.
     * @param str message string.
     */
    public void displayMessage(String str){
        JOptionPane.showMessageDialog(this, str);
    }
}
