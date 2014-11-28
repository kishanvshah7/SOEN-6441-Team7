/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import extra.mapReader;
import java.io.File;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import tdgame.controller.MapBoxController;

/**
 * This is model class of Map Creation Module.
 * @author Rahul K Kikani
 */
public class MapCreationModel {
    
    private int xBlockCount = 0;
    private int yBlockCount = 0;
    private String FileName;
    private String FilePath;
    
    /**
     * This method will set value of xBlockCount.
     * @param x the x to set
     */
    public void setXBlockCount(int x){
        xBlockCount = x;
    }
    
    /**
     * This method will set value of yBlockCount.
     * @param y the y to set
     */
    public void setYBlockCount(int y){
        yBlockCount = y;
    }
    
    /**
     * This method will return value of xBlockCount.
     * @return the XBlockCount
     */
    public int getXBlockCount(){
        return xBlockCount;
    }
    
    /**
     * This method will return value of xBlockCount.
     * @return the yBlockCount
     */
    public int getYBlockCount(){
        return yBlockCount;
    }
    
    /**
     * This method reads map file.
     * @param mbCon the object of Map Box Controller
     * @param name the name of file
     * @param path the path of file
     * @return file validation result
     */
    public boolean readFile(MapBoxController mbCon, String name, File path){
        System.out.println("Selected FilePath: "+path);
        try{
            Scanner loadScanner = new Scanner(path);
            int xC = loadScanner.nextInt();
            int yC = loadScanner.nextInt();
            
             while(loadScanner.hasNext()){
                mbCon.setXBlockCount(xC);
                mbCon.setYBlockCount(yC);
                mbCon.setGridArray();
                 Thread.sleep(100);
                 
                for(int y=0;y<yC;y++){
                    for(int x=0;x<xC;x++){
                        int val = loadScanner.nextInt();
                        mbCon.setmapGirdArrayElementF(y, x, val);
                        
                    }
                }
             }

             loadScanner.close();
             mbCon.setFileFlag(true);
             return true;
         } catch(Exception e){
             System.out.println("Hey, Somtething is wrong in file.");
             return false;
         }
    }
    
    /**
     * This method reads map file.
     * @param mbCon the object of Map Box Controller
     * @param name the name of file
     * @param path the path of file
     * @return file validation result
     */
    public boolean readFile_XML(MapBoxController mbCon, String name, File path){
        
        mapReader mr = new mapReader();
        if(mr.loadmapReader(path)){
            mbCon.setXBlockCount(mr.getxC());
                mbCon.setYBlockCount(mr.getyC());
                mbCon.setGridArray();
                
                for(int y=0;y<mr.getyC();y++){
                    for(int x=0;x<mr.getxC();x++){
                        mbCon.setmapGirdArrayElementF(y, x, mr.getGridArray()[y][x]);
                    }
                }
            mbCon.setFileFlag(true);
            return true;
        } else {
            return false;
        }
        
    }
}
