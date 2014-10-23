/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import javax.swing.JButton;
import javax.swing.JComponent;
import tdgame.model.MapBoxModel;
import tdgame.view.MapBoxView;

/**
 *
 * @author Rahul K Kikani
 */
public class MapBoxController {
    
    MapBoxView theView;
    MapBoxModel theModel;
    
    public MapBoxController(){
        System.out.println("MapBoxController");
        this.theModel = new MapBoxModel();
        this.theView = new MapBoxView(this);
    }
    
    public MapBoxView getView(){
        return this.theView = new MapBoxView(this,theModel.xBlockCount,theModel.yBlockCount);
    }
    
    public void updateView(MapBoxView mbView){
        this.theView = mbView;
    }
    
    public int getXBlockCount(){
        return theModel.getXBlockCount();
    }
    public int getYBlockCount(){
        return theModel.getYBlockCount();
    }
    
    public void setXBlockCount(int x){
        System.out.println("MapBoxController.setXBlockCount()");
        theModel.setXBlockCount(x);
    }
    public void setYBlockCount(int y){
        System.out.println("MapBoxController.setYBlockCount()");
        theModel.setYBlockCount(y);
    }
    
    public void setGridArray(){
        theModel.setGridArray();
    }
    
    public void setEntryPointFlag(){
        theView.setEntryPointFlag();
    }
    
    public void setPathPointFlag(){
        theView.setPathPointFlag();
    }
    
    public void setExitPointFlag(){
        theView.setExitPointFlag();
    }

    public void setBtnGridClickListner() {
        this.theView.addButtonClickEventListner(new ButtonActionDetector());
    }
    
    public int getExitPointData(){
        return this.theModel.getExitPointData();
    }
    
    public int getEentryPointData(){
        return this.theModel.getEntryPointData();
    }
    
    public int[][] getMapGirdArray(){
        return this.theModel.getMapGirdArray();
    }
    
    public void validPath(){
        for(int x=0;x<theModel.getXBlockCount();x++){
            System.out.print("\n");
            for(int y=0;y<theModel.getYBlockCount();y++){
                System.out.print(""+theModel.getmapGirdArrayElement(y, x));
            }
        }
    }
    
    public void saveMap(String str){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
                  new FileOutputStream("MapFiles/"+str+".txt"), "utf-8"));
            for(int x=0;x<theModel.getXBlockCount();x++){
                for(int y=0;y<theModel.getYBlockCount();y++){                 
                    writer.write(theModel.getmapGirdArrayElement(y, x)+" ");
                }
                writer.write(System.getProperty("line.separator"));
            }
        } catch (IOException ex) {
        } finally {
           try {writer.close();} catch (Exception ex) {}
        }
    }
    
    class ButtonActionDetector implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            String tempBtnStr = e.getActionCommand();
            if(e.getSource() instanceof JButton)
            {
                System.out.println("You Clicked Grid.");
                String btnName = ((JComponent)e.getSource()).getName();
                String[] temp = new String[2];
                temp = btnName.split("_");
                int x = Integer.parseInt(temp[0]);
                int y = Integer.parseInt(temp[1]);
                System.out.println("X: "+x+" Y:"+y);
                if(x==0){
                    theView.setEntryPoint(x, y);
                    theModel.setmapGirdArrayElement( x, y, 7);
                }else if(x==(theModel.getXBlockCount()-1)){
                    theView.setExitPoint(x, y);
                    theModel.setmapGirdArrayElement( x, y, 8);
                }else if(x>0 && y < (theModel.getXBlockCount())){
                    theView.setPathPoint(x, y);
                    theModel.setmapGirdArrayElement( x, y, 1);
                }
            }
        }
    }
}
