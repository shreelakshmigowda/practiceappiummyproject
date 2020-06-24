package com.qa.utils;

import java.io.InputStream;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class TestUtils {
public static final long WAIT=10;

public HashMap<String, String> parsestringxml(InputStream file) throws Exception
{
	HashMap<String, String> stringMap= new HashMap<String, String>();
//Get Document Builder
DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();	
DocumentBuilder builder = factory.newDocumentBuilder();
//Build the document
Document document =builder.parse(file);
//Normalize the xml stucture, it is very important
document.getDocumentElement().normalize();
//Here comes the root node

Element root = document.getDocumentElement();
//Get all elements

NodeList nlist = document.getElementsByTagName("string");
for(int temp=0; temp<nlist.getLength(); temp++)
{
	Node node =nlist.item(temp);
	if (node.getNodeType()==Node.ELEMENT_NODE)
	{
		Element eElement= (Element)node;
		//Store each element key value in map
		
	
		stringMap.put(eElement.getAttribute("name"),eElement.getTextContent());
		
		
	}}
return stringMap;
			
}
}


