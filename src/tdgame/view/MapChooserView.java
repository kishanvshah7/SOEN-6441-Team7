/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

/**
 *
 * @author Rahul K Kikani
 */
public class MapChooserView extends JFrame {
    
    private JButton showButton;
    private JList fruitList;
    public MapChooserView(){
    }
    
    public MapChooserView(MainScreenView msView, String[] fileList){
        this.setTitle("Select Your Map");
        this.setSize(200,300);
        listMapFiles(fileList);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
    }
    
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
    
    public void addButtonClickEventListner(ActionListener ListnerForButton){
        showButton.addActionListener(ListnerForButton);
    }
    
    public String getSelectedFile(){
        if (fruitList.getSelectedIndex() != -1) {                     
                 return fruitList.getSelectedValue().toString();
        }
        else
            return "NONE";
    }
    
    public void displayMessage(String str){
        JOptionPane.showMessageDialog(this, str);
    }
}
