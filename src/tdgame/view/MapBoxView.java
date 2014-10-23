/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.view;

import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import tdgame.controller.MapBoxController;

/**
 *
 * @author Rahul K Kikani
 */
public class MapBoxView extends JPanel {

    int xC=0;
    int yC=0;
    JButton[][] cellBtn;
    
    boolean entryPointFlag = false;
    boolean pathPointFlag = false;
    boolean exitPointFlag = false;
    
    MapBoxController mbCon;
    
    public MapBoxView(MapBoxController mbCon){
        this.mbCon = mbCon;
        System.out.println("MapBoxView");
    }
    
    public MapBoxView(MapBoxController mbCon,int xC, int yC) {
        this.mbCon = mbCon;
        this.xC = xC;
        this.yC = yC;
        cellBtn = new JButton[xC][yC];
      GridBagLayout layout = new GridBagLayout();
      this.setLayout(layout);        
      GridBagConstraints gbc = new GridBagConstraints();

      for(int x=0;x<xC;x++){
          gbc.fill = GridBagConstraints.HORIZONTAL;
          for(int y=0;y<yC;y++){
                gbc.gridx = x;
                gbc.gridy = y;
                gbc.ipady = 20;
                cellBtn[x][y] = new JButton();
                cellBtn[x][y].setName(x+"_"+y);
                this.add(cellBtn[x][y],gbc);
          }
      }
    }
    
    public void addButtonClickEventListner(ActionListener ListnerForButton){
        for(int x=0;x<mbCon.getXBlockCount();x++){
          for(int y=0;y<mbCon.getYBlockCount();y++){
                cellBtn[x][y].addActionListener(ListnerForButton);
                cellBtn[x][y].setBackground(Color.green);
                cellBtn[x][y].setEnabled(false);
          }
      }
    }
    
    public void setEntryPointFlag(){
        System.out.println(""+mbCon.getEentryPointData());
        for(int x=0;x<mbCon.getXBlockCount();x++){
          for(int y=0;y<mbCon.getYBlockCount();y++){
              if(x==0){
                  cellBtn[0][y].setBackground(Color.gray);
                  cellBtn[0][y].setEnabled(true);
              }
              else{
                  cellBtn[x][y].setEnabled(false);
                  cellBtn[x][y].setBackground(Color.white);
              }
            }
        }
        setSlectedCell();
    }
    
    public void setPathPointFlag(){
        for(int x=0;x<mbCon.getXBlockCount();x++){
          for(int y=0;y<mbCon.getYBlockCount();y++){
              if(x==0){
                  cellBtn[0][y].setBackground(Color.white);
                  cellBtn[0][y].setEnabled(false);
              } else if(x==mbCon.getXBlockCount()-1)
              {
                  cellBtn[mbCon.getXBlockCount()-1][y].setBackground(Color.white);
                  cellBtn[mbCon.getXBlockCount()-1][y].setEnabled(false);
              }
              else{
                  cellBtn[x][y].setEnabled(true);
                  cellBtn[x][y].setBackground(Color.green);
              }
            }
        }
        setSlectedCell();
    }
    
    public void setExitPointFlag(){
        for(int x=0;x<mbCon.getXBlockCount();x++){
          for(int y=0;y<mbCon.getYBlockCount();y++){
              if(x==mbCon.getXBlockCount()-1)
              {
                  cellBtn[mbCon.getXBlockCount()-1][y].setBackground(Color.gray);
                  cellBtn[mbCon.getXBlockCount()-1][y].setEnabled(true);
              }
              else{
                  cellBtn[x][y].setEnabled(false);
                  cellBtn[x][y].setBackground(Color.white);
              }
            }
        }
        setSlectedCell();
    }
    
    public void setSlectedCell(){
        if(mbCon.getEentryPointData() != 9)
            cellBtn[0][mbCon.getEentryPointData()].setBackground(new Color(165, 42, 42));
        if(mbCon.getExitPointData() != 9)
            cellBtn[mbCon.getXBlockCount()-1][mbCon.getExitPointData()].setBackground(new Color(165, 42, 42));
        int[][] temp = mbCon.getMapGirdArray();
        for(int x=0;x<mbCon.getXBlockCount();x++){
            for(int y=0;y<mbCon.getYBlockCount();y++){
                if(temp[x][y] == 1)
                    cellBtn[x][y].setBackground(Color.yellow);
            }
        }
    }
    
    public void setEntryPoint(int xC, int yC){
        for(int y=0;y<mbCon.getYBlockCount();y++){
            cellBtn[0][y].setBackground(Color.gray);
        }
        cellBtn[xC][yC].setBackground(new Color(165, 42, 42));
    }
    
    public void setPathPoint(int xC, int yC){
        cellBtn[xC][yC].setBackground(Color.yellow);
    }
    
    public void setExitPoint(int xC, int yC){
        for(int y=0;y<mbCon.getYBlockCount();y++){
            cellBtn[mbCon.getXBlockCount()-1][y].setBackground(Color.gray);
        }
        cellBtn[xC][yC].setBackground(new Color(165, 42, 42));
    }
    
    public void displayMessage(String str){
        JOptionPane.showMessageDialog(this, str);
    }
}
