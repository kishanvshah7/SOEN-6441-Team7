package extra;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
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
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Rahul K Kikani
 */
public class mapReader {

    private static int[][] GridArray;
    private static int xC;
    private static int yC;
    public static File mapFile;
    public static String createDate;
    public static String lastmodifiedDate;
    public static int[] topScore = new int[]{0, 0, 0, 0, 0};
    public static String[][] playerInfo = new String[1000][2];
    public static boolean mapFileEditFlag = false;
    public static String savedTime;

    public static void main(String[] args) {

        try {
            File file = new File("SavedGame/MapFile.xml");
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = dBuilder.parse(file);

            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            if (doc.hasChildNodes()) {
                printNote(doc.getChildNodes());
                //System.out.println("Player Info" + playerInfo);
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * @return the GridArray
     */
    public static int[][] getGridArray() {
        return GridArray;
    }

    /**
     * @return the xC
     */
    public static int getxC() {
        return xC;
    }

    /**
     * @return the yC
     */
    public static int getyC() {
        return yC;
    }

    public static boolean loadmapReader(File f) {
        try {
            File file = f;
            mapFile = f;
            mapFileEditFlag = true;
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            //System.out.println("Root element :" + doc.getDocumentElement().getNodeName());

            if (doc.hasChildNodes()) {
                printNote(doc.getChildNodes());
                //System.out.println("Player Info" + playerInfo);
            }
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    private static void printNote(NodeList nodeList) {
        nodeList = nodeList.item(0).getChildNodes();
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                //System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");

                if (tempNode.getNodeName().equals("tileinfo") && tempNode.hasChildNodes()) {
                    NodeList xnodeList = tempNode.getChildNodes();
                    for (int x = 0; x < xnodeList.getLength(); x++) {
                        Node xtempNode = xnodeList.item(x);
                        if (xtempNode.hasAttributes()) {
                            Element eElement = (Element) xtempNode;
                            //System.out.println("X: " + eElement.getAttribute("x"));
                            //System.out.println("Y: " + eElement.getAttribute("y"));
                            xC = Integer.parseInt(eElement.getAttribute("x"));
                            yC = Integer.parseInt(eElement.getAttribute("y"));
                            GridArray = new int[Integer.parseInt(eElement.getAttribute("y"))][Integer.parseInt(eElement.getAttribute("x"))];
                        }

                        NodeList tilenodeList = xtempNode.getChildNodes();
                        for (int j = 0; j < tilenodeList.getLength(); j++) {
                            Node jtempNode = tilenodeList.item(j);
                            if (jtempNode.hasAttributes()) {
                                Element eElement = (Element) jtempNode;
                                //System.out.println("(" + eElement.getAttribute("x") + "," + eElement.getAttribute("y") + ") = " + eElement.getAttribute("value"));
                                GridArray[Integer.parseInt(eElement.getAttribute("y")) - 1][Integer.parseInt(eElement.getAttribute("x")) - 1] = Integer.parseInt(eElement.getAttribute("value"));
                            }
                        }
                    }
                } else if (tempNode.getNodeName().equals("mapinfo")) {
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if (towerNode.hasAttributes()) {
                            Element eElement = (Element) towerNode;
                            //System.out.println("Create: " + eElement.getAttribute("create"));
                            //System.out.println("LastModified: " + eElement.getAttribute("lastModified"));

                            createDate = eElement.getAttribute("create");
                            lastmodifiedDate = eElement.getAttribute("lastModified");
                        }
                    }
                } else if (tempNode.getNodeName().equals("scoreinfo")) {
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if (towerNode.hasAttributes()) {
                            Element eElement = (Element) towerNode;
                            //System.out.println("No: " + eElement.getAttribute("no"));
                            //System.out.println("Val: " + towerNode.getTextContent());

                            topScore[Integer.parseInt(eElement.getAttribute("no")) - 1] = Integer.parseInt(towerNode.getTextContent());
                        }
                    }
                } else if (tempNode.getNodeName().equals("playerinfo")) {
                    //System.out.println("PlayerInfo");
                    NodeList towerList = tempNode.getChildNodes();
                    //System.out.println("PlayerInfo Count:" + towerList.getLength());
                    int i = 0;
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if (towerNode.hasAttributes()) {
                            Element eElement = (Element) towerNode;
                            //System.out.println("Time: " + eElement.getAttribute("time"));
                            //System.out.println("Score: " + eElement.getAttribute("score"));
                            playerInfo[i][0] = eElement.getAttribute("time");
                            playerInfo[i][1] = eElement.getAttribute("score");
                            i++;
                        }
                    }
                }
            }

        }

    }

    public static DocumentBuilderFactory docFactory;
    public static DocumentBuilder docBuilder;
    public static Document doc;
    public static Element rootElement;

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

    public static void mapInfo(int[][] gcm) {
        Element mapinfo = doc.createElement("tileinfo");
        rootElement.appendChild(mapinfo);

        Element map = doc.createElement("map");
        mapinfo.appendChild(map);

        // set attribute to map element
        map.setAttribute("y", gcm.length + "");
        map.setAttribute("x", gcm[0].length + "");

        //loop start
        for (int x = 0; x < gcm[0].length; x++) {
            for (int y = 0; y < gcm.length; y++) {
                //System.out.println("(" + y + ", " + x + ")=" + gcm[y][x]);
                // tile elements
                Element tile = doc.createElement("tile");
                map.appendChild(tile);
                // set attribute to tile element
                tile.setAttribute("y", (y + 1) + "");
                tile.setAttribute("x", (x + 1) + "");
                if(gcm[y][x]==1 || gcm[y][x]==7 || gcm[y][x]==8)
                    tile.setAttribute("value", gcm[y][x] + "");
                else
                    tile.setAttribute("value", 0+"");
            }
            //System.out.println("\n");
        }
    }

    public static void mapEInfo() {
        // towerinfo elements
        Element towerinfo = doc.createElement("mapinfo");
        rootElement.appendChild(towerinfo);

        Element tower = doc.createElement("modification");
        towerinfo.appendChild(tower);
        // set attribute to tower element
        tower.setAttribute("create", createDate);
        tower.setAttribute("loastModified", lastmodifiedDate);
    }

    public static void scoreInfo() {
        // gameinfo elements
        Element scoreinfo = doc.createElement("scoreinfo");
        rootElement.appendChild(scoreinfo);

        for (int i = 0; i < topScore.length; i++) {
            Element level = doc.createElement("item");
            scoreinfo.appendChild(level);
            level.setAttribute("no", (i + 1) + "");
            level.setTextContent(topScore[i] + "");
        }
    }

    public static void updateScore(int score) {
        boolean duplicateF = false;
        for (int i = 0; i < playerInfo.length; i++) {
            if (playerInfo[i][0] != null && playerInfo[i][0].equals(savedTime)) {
                playerInfo[i][1] = score + "";
                duplicateF = true;
                break;
            }
        }

        if (!duplicateF) {
            for (int i = 0; i < playerInfo.length; i++) {
                if (playerInfo[i][0] == null || playerInfo[i][0].equals("")) {
                    playerInfo[i][0] = savedTime+"";
                    playerInfo[i][1] = score + "";
                    break;
                }
            }
        }
        
        for (int i = 0; i < topScore.length; i++) {
            int temp_score = topScore[i];
            if ( temp_score < score) {
                int temp;
                for(int j=i+1;j<topScore.length;j++){
                    temp = topScore[j];
                    topScore[j] = temp_score;
                    temp_score = temp;
                }
                topScore[i] = score;
                break;
            }
        }
    }

    public static void playerInfo() {
        // gameinfo elements
        Element playerinfo = doc.createElement("playerinfo");
        rootElement.appendChild(playerinfo);
        for (int i = 0; i < playerInfo.length; i++) {
            if (playerInfo[i][0] != null && playerInfo[i][1] != null) {
                Element level = doc.createElement("item");
                playerinfo.appendChild(level);
                level.setAttribute("time", playerInfo[i][0]);
                level.setAttribute("score", playerInfo[i][1]);
            }
        }
    }

    public static void saveFile() {
        // write the content into xml file
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer;
        try {
            transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(mapFile);

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

}
