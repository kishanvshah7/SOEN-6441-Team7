/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import extra.ReadXMLFile;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import tdgame.model.PlayScreenModel;
import tdgame.view.MainScreenView;
import towerdefensegame.GamePlay;
import towerdefensegame.LogGenerator;

/**
 *
 * @author Rahul K Kikani
 */
public class GameChooser extends JFrame implements ActionListener{
    
    private JButton showButton;
    private JList fruitList;
    MainScreenView msView;
    private String[] FileList;
    
    /**
     * This method is constructor.
     */
    public GameChooser(){
    }
    
    /**
     * This method will initialize GUI components for Map Chooser.
     * @param msView the view object Main Screen
     * @param fileList array of map files
     */
    public GameChooser(MainScreenView msView){
        
        listFilesForFolder(new File("SavedGame"));
        
        this.setTitle("Select Saved Game");
        
        this.setSize(200,300);
        this.msView = msView;
        listMapFiles();
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
    public void listMapFiles(){
        
        this.setLayout(new BorderLayout(5, 5));
        final DefaultListModel fruitsName = new DefaultListModel();

        for(int i=0;i<FileList.length;i++){
            fruitsName.addElement(FileList[i]);
        }

        fruitList = new JList(fruitsName);
        fruitList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        fruitList.setSelectedIndex(0);
        fruitList.setVisibleRowCount(3);        

        JScrollPane savedgameListScrollPane = new JScrollPane(fruitList);    

        showButton = new JButton("Let's Play");
        this.add(savedgameListScrollPane,BorderLayout.CENTER);  
        this.add(showButton,BorderLayout.SOUTH);
        showButton.addActionListener(this);
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
    
    public void listFilesForFolder(final File folder) {
        FileList = new String[folder.listFiles().length];
        int i=0;
        for (final File fileEntry : folder.listFiles()) {
            if (fileEntry.isDirectory()) {
                //listFilesForFolder(fileEntry);
            } else {
                FileList[i] = fileEntry.getName();
                i++;
                //System.out.println(fileEntry.getName());
            }
        }
    }
    
    /**
     * This method create Message show dialog box.
     * @param str message string.
     */
    public void displayMessage(String str){
        JOptionPane.showMessageDialog(this, str);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                if(tempBtnStr.equals("Let's Play")){
                    if(getSelectedFile().equals("NONE")){
                        displayMessage("Please Select At least one file.");
                    }else{
                        System.out.println("User's loaded file:"+getSelectedFile());
                        PlayScreenModel psModel = new PlayScreenModel();
                        ReadXMLFile gameFile = new ReadXMLFile(new File("SavedGame/"+getSelectedFile()));
                        
                            GamePlay gp = new GamePlay(new File("SavedGame/"+getSelectedFile()), gameFile.getxC(), gameFile.getyC(), "GameLoad");
                            setMSTOp(false);
                            dispose();
                    }
                }
            }
        }
}
