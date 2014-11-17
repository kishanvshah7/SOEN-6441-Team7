/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.io.File;

/**
 * This is model for Map Chooser Module.
 * @author Rahul K Kikani
 */
public class MapChooserModel {
    private String[] FileList;
    
    /**
      This is constructor method for Map Chooser. It will call Map Folder reader.
     */
    public MapChooserModel(){
        listFilesForFolder(new File("MapFiles"));
    }
    
    /**
     * This method will read all files form Folder and save it in String array
     * @param folder folder location.
     */
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
     * this method will return Map File array.
     * @return map file array
     */
    public String[] getMapFileList(){
        return FileList;
    }
}
