package xml;

import javax.xml.parsers.*;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.*;
import com.sun.org.apache.xml.internal.serialize.*;


public class writeproducts {
	
	public writeproducts()
	{
		try
		{
			this.xml();
		}
		catch(ParserConfigurationException p)
		{
			
		}
		catch(FileNotFoundException f)
		{
			
		}

		catch(IOException I)
		{
			
		}
		
		
	}
	
	private void xml () throws ParserConfigurationException, FileNotFoundException, IOException
	{
		//document builder factory
		DocumentBuilderFactory docfac = DocumentBuilderFactory.newInstance();
		
		//document builder
		DocumentBuilder docbuild = docfac.newDocumentBuilder();
		//document
		Document xmldoc = docbuild.newDocument();
				
		//build xml elements and text nodes
		/*
		 * <game>
		 *    <towerinfo>
		 *           <tower id = "3">
		 *              <level>1</level>
		 *             </tower>
		 *     <towerinfo> 
		 *  </game>
		 */      
		Element rootElement = xmldoc.createElement("game");
		Element mainElement = xmldoc.createElement("towerinfo");
		//Element subElement1 = xmldoc.createElement("tower");
		Element subElement1 = xmldoc.createElement("tower");
		subElement1.setAttribute("id", "1");
		
		Text productName =  xmldoc.createTextNode("1");
		Element nameElement = xmldoc.createElement("level");
		nameElement.appendChild(productName);
		//subElement2.appendChild(nameElement);
		subElement1.appendChild(nameElement);
		mainElement.appendChild(subElement1);
		rootElement.appendChild(mainElement);
		xmldoc.appendChild(rootElement);
		
		//output format
		
		OutputFormat outformat = new OutputFormat(xmldoc);
		outformat.setIndenting(true);
		
		//decalre file
		
		File td = new File("towerdefense.xml");
		
		
		//declare file outputstream
		FileOutputStream file1 = new FileOutputStream(td);
		
		
		//xml serializer
		XMLSerializer ser = new XMLSerializer(file1, outformat);
		//specified format
		ser.serialize(xmldoc);
	
		
		
	}
	
	public static void main(String[] args)
	{
	    System.out.println("hi how are you");
		writeproducts wr = new writeproducts();
	}

}
