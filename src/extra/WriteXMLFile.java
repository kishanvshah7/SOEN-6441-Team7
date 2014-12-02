package extra;

/**
 *
 * @author Ankit
 */
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class WriteXMLFile {

    public static void main(String argv[]) {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            // root elements
            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("game");
            doc.appendChild(rootElement);

            // mapinfo elements
            Element mapinfo = doc.createElement("mapinfo");
            rootElement.appendChild(mapinfo);

            // map elements
            Element map = doc.createElement("map");
            mapinfo.appendChild(map);
            // set attribute to map element
            map.setAttribute("y", "2");
            map.setAttribute("x", "2");

            //loop start
            // tile elements
            Element tile = doc.createElement("tile");
            map.appendChild(tile);
            // set attribute to tile element
            tile.setAttribute("y", "1");
            tile.setAttribute("x", "1");
            tile.setAttribute("value", "2");
            //loop end

            // towerinfo elements
            Element towerinfo = doc.createElement("towerinfo");
            rootElement.appendChild(towerinfo);

            //loop start
            // tower elements
            Element tower = doc.createElement("tower");
            towerinfo.appendChild(tower);
            // set attribute to tower element
            tower.setAttribute("level", "1");
            tower.setAttribute("id", "3");
            //loop end

            // gameinfo elements
            Element gameinfo = doc.createElement("gameinfo");
            rootElement.appendChild(gameinfo);

            // level elements
            Element level = doc.createElement("level");
            gameinfo.appendChild(level);
            level.appendChild(doc.createTextNode("1"));
            // health elements
            Element health = doc.createElement("health");
            gameinfo.appendChild(health);
            health.appendChild(doc.createTextNode("3"));
            // money elements
            Element money = doc.createElement("money");
            gameinfo.appendChild(money);
            money.appendChild(doc.createTextNode("150"));

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

            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new File("SavedGame/Game1W.xml"));

		// Output to console for testing
            // StreamResult result = new StreamResult(System.out);
            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException | TransformerException pce) {
            pce.printStackTrace();
        }
    }
}
