/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package towerdefensegame;

import java.awt.Rectangle;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import tdgame.model.CellContainerModel;
import tdgame.model.configModel;

/**
 * Log Generator
 * @author Rahul K Kikani
 */
public class LogGenerator {
    
    public static boolean fileFlag = false;
    public static String fileName;
    public static Writer writer = null;
    public static String[] waveLog = new String[10];
    
    /**
     * Adding Log
     * @param msg Log message
     */
    public static void addLog(String msg){
        if(fileFlag){
            try {
                Date date = new Date();
                SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
                writer.write("["+ft.format(date)+"] : "+ msg +"\n");
                GameLogViewer.addText_GGjtp("["+ft.format(date)+"] : "+ msg +"\n");
            } catch (IOException ex) {
                Logger.getLogger(LogGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            createLogFile();
            addLog(msg);
        }
    }
    
    /**
     * Add Collective Log
     * @param msg Log Message
     */
    public static void addCollectiveTowerLog(String msg){
                Date date = new Date();
                SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
                GameLogViewer.addText_CTjtp("["+ft.format(date)+"] : "+ msg +"\n");
    }
    
    /**
     * Add Individual Tower Log
     * @param msg log Message
     */
    public static void addIndividualTowerLog(String msg){
                Date date = new Date();
                GameLogViewer.addText_ITjtp(msg);
    }
    
    /**
     * Add Wave Log
     * @param msg Log Message
     */
    public static void addWaveLog(String msg){
                Date date = new Date();
                SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
                waveLog[configModel.level-1] += "["+ft.format(date)+"] : "+ msg +"\n";
                GameLogViewer.addText_Wjtp("["+ft.format(date)+"] : "+ msg +"\n");
    }
    
    /**
     * Create Log File
     */
    public static void createLogFile(){
        try {
            try {
                Date date = new Date();
                SimpleDateFormat ft =  new SimpleDateFormat ("dd.MM.yyyy hh_mm_ss a");
                fileName = ft.format(date);
                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Log/temp.log"), "utf-8"));
//                writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("Log/"+ft.format(date)+".log"), "utf-8"));
                fileFlag = true;
            } catch (FileNotFoundException ex) {
                Logger.getLogger(LogGenerator.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (UnsupportedEncodingException ex) {
            Logger.getLogger(LogGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Close Log File
     */
    public static void closeLog(){
        try {
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(LogGenerator.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    /**
     * Get Log Time
     * @return time in string
     */
    public static String getLogTime(){
        Date date = new Date();
        SimpleDateFormat ft =  new SimpleDateFormat ("dd/MM/yyyy hh:mm:ss a");
        return "["+ft.format(date)+"] : ";
    }
    
    /**
     * Add Tower Level Log
     */
    public static void towerLog(){
        for(int y=0;y < configModel.gridY; y++){
                    for(int x=0;x < configModel.gridX; x++){
                        for(int i=0;i<configModel.airTowerLaser.length;i++){
                            if(CellContainerModel.gcModel[y][x].towerLog[i] != null){
                                addTotalKilled(y,x,i);
                                System.out.println(CellContainerModel.gcModel[y][x].towerLog[i]);
                            }
                        }
                    }
                }
    }
    
    /**
     * Total killed by tower
     * @param y y coordinate of tower
     * @param x x coordinate of tower
     * @param i type of tower
     */
    public static void addTotalKilled(int y, int x, int i){
        CellContainerModel.gcModel[y][x].towerLog[i] += LogGenerator.getLogTime()+"Total no of Creatures Killed by this tower is: "+CellContainerModel.gcModel[y][x].towerCKilled[i]+"\n";
    }
}
