package extra;

/**
 *
 * @author Rahul K Kikani
 */
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
 
public class ReadXMLFile {
 
  public static void main(String[] args) {
 
    try {
 
	File file = new File("SavedGame/Game1.xml");
 
	DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance()
                             .newDocumentBuilder();
 
	Document doc = dBuilder.parse(file);
 
	System.out.println("Root element :" + doc.getDocumentElement().getNodeName());
 
	if (doc.hasChildNodes()) {
            printNote(doc.getChildNodes());
	}
 
    } catch (Exception e) {
	System.out.println(e.getMessage());
    }
 
  }
 
  private static void printNote(NodeList nodeList) {
      nodeList = nodeList.item(0).getChildNodes();
    for (int count = 0; count < nodeList.getLength(); count++) {
	Node tempNode = nodeList.item(count);
	if (tempNode.getNodeType() == Node.ELEMENT_NODE) {
		
		System.out.println("\nNode Name =" + tempNode.getNodeName() + " [OPEN]");
		//System.out.println("Node Value =" + tempNode.getTextContent());
		
                if (tempNode.getNodeName().equals("mapinfo") && tempNode.hasChildNodes()) {
                    NodeList xnodeList = tempNode.getChildNodes();
                    for (int x = 0; x < xnodeList.getLength(); x++) {
                        Node xtempNode = xnodeList.item(x);
                        if (xtempNode.hasAttributes()) {
                            Element eElement = (Element) xtempNode;
                            System.out.println("X: "+eElement.getAttribute("x"));
                            System.out.println("Y: "+eElement.getAttribute("y"));
                        }

                        NodeList tilenodeList = xtempNode.getChildNodes();
                        for (int j = 0; j < tilenodeList.getLength(); j++) {
                            Node jtempNode = tilenodeList.item(j);
                            if (jtempNode.hasAttributes()) {
                                Element eElement = (Element) jtempNode;
                                System.out.println("("+eElement.getAttribute("x")+","+eElement.getAttribute("x")+") = "+eElement.getAttribute("value"));
                            }
                        }
                    }
		} else if(tempNode.getNodeName().equals("towerinfo")){
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if (towerNode.hasAttributes()) {
                            Element eElement = (Element) towerNode;
                            System.out.println("ID: "+eElement.getAttribute("id"));
                            System.out.println("Level: "+eElement.getAttribute("level"));
                        }
                    }
                } else if(tempNode.getNodeName().equals("gameinfo")){
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if(!towerNode.getNodeName().equals("#text"))
                        {
                            System.out.println("Name: "+towerNode.getNodeName());
                            System.out.println("Val: "+towerNode.getTextContent());
                        }
                    }
                } else if(tempNode.getNodeName().equals("loginfo")){
                    NodeList towerList = tempNode.getChildNodes();
                    for (int x = 0; x < towerList.getLength(); x++) {
                        Node towerNode = towerList.item(x);
                        if(!towerNode.getNodeName().equals("#text"))
                        {
                            Element eElement = (Element) towerNode;
                            System.out.println("time: "+eElement.getAttribute("time"));
                            System.out.println("type: "+eElement.getAttribute("type"));
                            System.out.println("Name: "+towerNode.getNodeName());
                            System.out.println("Val: "+towerNode.getTextContent());
                        }
                    }
                }
		System.out.println("Node Name =" + tempNode.getNodeName() + " [CLOSE]");
	}
 
    }
 
  }
 
}