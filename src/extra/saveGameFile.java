/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import tdgame.model.GridCellModel;
import tdgame.model.configModel;
import static tdgame.model.configModel.*;

/**
 *
 * @author Rahul K Kikani
 */
public class saveGameFile {

    public static DocumentBuilderFactory docFactory;
    public static DocumentBuilder docBuilder;
    public static Document doc;
    public static Element rootElement;
    public static File f;

    public static void initFile() {
        docFactory = DocumentBuilderFactory.newInstance();
        try {
            docBuilder = docFactory.newDocumentBuilder();
        } catch (ParserConfigurationException ex) {
            Logger.getLogger(saveGameFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        doc = docBuilder.newDocument();
        rootElement = doc.createElement("game");
        doc.appendChild(rootElement);

    }

    public static void mapInfo(GridCellModel[][] gcm) {
        Element mapinfo = doc.createElement("mapinfo");
        rootElement.appendChild(mapinfo);

        Element map = doc.createElement("map");
        mapinfo.appendChild(map);
        
        // set attribute to map element
        map.setAttribute("y", gcm.length+"");
        map.setAttribute("x", gcm[0].length+"");
        map.setAttribute("file", f.getName());

        //loop start
        for(int x=0;x<gcm[0].length;x++){
            for(int y=0;y<gcm.length;y++){
                //System.out.println("("+y+", "+x+")="+gcm[y][x].getAirID());
                // tile elements
                Element tile = doc.createElement("tile");
                map.appendChild(tile);
                // set attribute to tile element
                tile.setAttribute("y", (y+1)+"");
                tile.setAttribute("x", (x+1)+"");
                if(gcm[y][x].getAirID() == 7 || gcm[y][x].getAirID() ==8)
                    tile.setAttribute("value", gcm[y][x].getAirID()+""); 
                else if(gcm[y][x].getAirID() == -1 && gcm[y][x].getgID() != 1)
                    tile.setAttribute("value", 0+"");
                else if(gcm[y][x].getgID()== 1)
                    tile.setAttribute("value", 1+""); 
                else
                    tile.setAttribute("value", gcm[y][x].getAirID()+""); 
            }
            System.out.println("\n");
        }
    }

    public static void towerInfo() {
        // towerinfo elements
        Element towerinfo = doc.createElement("towerinfo");
        rootElement.appendChild(towerinfo);

        //loop start
        for (int i = 0; i < TowerLevel.length; i++) {
            // tower elements
            Element tower = doc.createElement("tower");
            towerinfo.appendChild(tower);
            // set attribute to tower element
            tower.setAttribute("level", TowerLevel[i]+"");
            tower.setAttribute("id", (i + 3)+"");
//            System.out.println("T ID+Level" + (i + 3) + "+" + TowerLevel[i]);
        }
        //loop end
    }

    public static void gameInfo() {
        // gameinfo elements
        Element gameinfo = doc.createElement("gameinfo");
        rootElement.appendChild(gameinfo);

        // level elements
        Element level = doc.createElement("level");
        gameinfo.appendChild(level);
        level.appendChild(doc.createTextNode(configModel.level+""));
        // health elements
        Element health = doc.createElement("health");
        gameinfo.appendChild(health);
        health.appendChild(doc.createTextNode(configModel.health+""));
        // money elements
        Element money = doc.createElement("money");
        gameinfo.appendChild(money);
        money.appendChild(doc.createTextNode(configModel.money+""));
    }

    public static void lofInfo() {
        // gameinfo elements
        Element loginfo = doc.createElement("loginfo");
        rootElement.appendChild(loginfo);

        //loop start
        // item elements
        Element item = doc.createElement("item");
        loginfo.appendChild(item);
        // set attribute to item element
        item.setAttribute("type", "T");
        item.setAttribute("time", "12");
        item.appendChild(doc.createTextNode("asdfasdf"));
        //loop end
    }

    public static void saveFile() {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(f);

            try {
                // Output to console for testing
                // StreamResult result = new StreamResult(System.out);
                transformer.transform(source, result);
            } catch (TransformerException ex) {
                Logger.getLogger(saveGameFile.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (TransformerConfigurationException ex) {
            Logger.getLogger(saveGameFile.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println("File saved!");
    }

    public static void main(String argv[]) {
        saveGameFile sgf = new saveGameFile();
        sgf.initFile();
        //sgf.mapInfo();
        sgf.towerInfo();
        sgf.gameInfo();
        sgf.saveFile();
    }
}
