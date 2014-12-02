/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package extra;

/**
 * XML File Reader
 *
 * @author Rahul K Kikani
 */
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import tdgame.model.configModel;

public class ReadXMLFile {

    private static int[][] GridArray;
    private static int xC;
    private static int yC;

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

    /**
     * XML File Reading
     *
     * @param f file location
     */
    public ReadXMLFile(File f) {
        try {

            File file = f;
            saveGameFile.f = f;
            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                printNote(doc.getChildNodes());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Main method
     *
     * @param args arguments
     */
    public static void main(String[] args) {

        try {

            File file = new File("SavedGame/Game1.xml");

            DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder();

            Document doc = dBuilder.parse(file);

            if (doc.hasChildNodes()) {
                printNote(doc.getChildNodes());
            }

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * Get XML node List
     *
     * @param nodeList node list
     */
    private static void printNote(NodeList nodeList) {
        nodeList = nodeList.item(0).getChildNodes();
        for (int count = 0; count < nodeList.getLength(); count++) {
            Node tempNode = nodeList.item(count);
            if (tempNode.getNodeType() == Node.ELEMENT_NODE) {

                if (tempNode.getNodeName().equals("mapinfo") && tempNode.hasChildNodes()) {
                    NodeList xnodeList = tempNode.getChildNodes();
                    for (int x = 0; x < xnodeList.getLength(); x++) {
                        Node xtempNode = xnodeList.item(x);
                        if (xtempNode.hasAttributes()) {
                            Element eElement = (Element) xtempNode;

                            xC = Integer.parseInt(eElement.getAttribute("x"));
                            yC = Integer.parseInt(eElement.getAttribute("y"));
                            mapReader.mapFile = new File("MapFiles/" + eElement.getAttribute("file"));
                            Date date = new Date();
                            SimpleDateFormat ft = new SimpleDateFormat("dd.MM.yyyy hh_mm_ss a");
                            mapReader.loadmapReader(mapReader.mapFile);
                            mapReader.savedTime = ft.format(date);
                            GridArray = new int[Integer.parseInt(eElement.getAttribute("y"))][Integer.parseInt(eElement.getAttribute("x"))];
                        }

                        NodeList tilenodeList = xtempNode.getChildNodes();
                        for (int j = 0; j < tilenodeList.getLength(); j++) {
                            Node jtempNode = tilenodeList.item(j);
                            if (jtempNode.hasAttributes()) {
                                Element eElement = (Element) jtempNode;

                                GridArray[Integer.parseInt(eElement.getAttribute("y")) - 1][Integer.parseInt(eElement.getAttribute("x")) - 1] = Integer.parseInt(eElement.getAttribute("value"));
                            }
                        }
                    }
                } else if (tempNode.getNodeName().equals("towerinfo")) {
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if (towerNode.hasAttributes()) {
                            Element eElement = (Element) towerNode;
                            configModel.TowerLevel[Integer.parseInt(eElement.getAttribute("id")) - 3] = Integer.parseInt(eElement.getAttribute("level"));
                            int level = Integer.parseInt(eElement.getAttribute("level"));
                            if (level != 1) {
                                configModel.airTowerRanger[Integer.parseInt(eElement.getAttribute("id")) - 3] += 20 * level;
                            }

                        }
                    }
                } else if (tempNode.getNodeName().equals("gameinfo")) {
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if (!towerNode.getNodeName().equals("#text")) {

                            if (towerNode.getNodeName().equals("level")) {
                                int w = Integer.parseInt(towerNode.getTextContent().toString());
                                configModel.level = w;
                            } else if (towerNode.getNodeName().equals("health")) {
                                int w = Integer.parseInt(towerNode.getTextContent().toString());
                                configModel.health = w;
                            } else if (towerNode.getNodeName().equals("money")) {
                                int w = Integer.parseInt(towerNode.getTextContent().toString());
                                configModel.money = w;
                            }
                        }
                    }
                } else if (tempNode.getNodeName().equals("loginfo")) {
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if (!towerNode.getNodeName().equals("#text")) {
                            Element eElement = (Element) towerNode;

                        }
                    }
                }
            }

        }

    }

    /**
     * @return the GridArray
     */
    public static int[][] getGridArray() {
        return GridArray;
    }

}
