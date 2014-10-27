/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package tdgame.model;

import java.io.File;

/**
 *
 * @author Rahul K Kikani
 */
public class MapChooserModel {
    private String[] FileList;
    
    public MapChooserModel(){
        listFilesForFolder(new File("MapFiles"));
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
    
    public String[] getMapFileList(){
        return FileList;
    }
}
