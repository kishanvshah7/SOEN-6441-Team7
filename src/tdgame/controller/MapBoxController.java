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
    
    public void setmapGirdArrayElement(int y, int x, int val){
        this.theModel.setmapGirdArrayElement(y, x, val);
    }
    
    public void setmapGirdArrayElementF(int y, int x, int val){
        this.theModel.setmapGirdArrayElementF(y, x, val);
    }
    
    public int getmapGirdArrayElement(int x, int y){
        return this.theModel.getmapGirdArrayElement(x, y);
    }
    
    public boolean getFileFlag(){
        return this.theModel.getFileFlag();
    }
    
    public void setFileFlag(boolean b){
        this.theModel.setFileFlag(b);
    }

    public String validPath(int[][] ga){
        MapValidation mapV = new MapValidation(ga);
        System.out.println("Path Validation: "+mapV.isValid());
        if(mapV.isValid()){
            return "Done";
        }else{
            return "Invalid";
        }
    }
    
    public void saveMap(String str){
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(
            new FileOutputStream("MapFiles/"+str+".team7"), "utf-8"));
            writer.write(theModel.getXBlockCount()+" "+theModel.getYBlockCount());
            writer.write(System.getProperty("line.separator"));
            for(int y=0;y<theModel.getYBlockCount();y++){
                for(int x=0;x<theModel.getXBlockCount();x++){                
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
                String btnName = ((JComponent)e.getSource()).getName();
                System.out.println("You Clicked Grid."+btnName);
                String[] temp = new String[2];
                temp = btnName.split("_");
                int x = Integer.parseInt(temp[0]);
                int y = Integer.parseInt(temp[1]);
                System.out.println("X: "+x+" Y:"+y);
                if(x==0){
                    theView.setEntryPoint(y, x);
                    theModel.setmapGirdArrayElement( y, x, 7);
                }else if(x==(theModel.getXBlockCount()-1)){
                    theView.setExitPoint(y, x);
                    theModel.setmapGirdArrayElement( y, x, 8);
                }else if(x>0 && y <= (theModel.getYBlockCount())){
                    theModel.setmapGirdArrayElement( y, x, 1);
                    theView.setPathPoint(y, x);
                }
            }
        }
    }
}
