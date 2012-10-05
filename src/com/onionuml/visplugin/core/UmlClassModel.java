package com.onionuml.visplugin.core;

import java.io.IOException;
import java.util.Map;

import javax.xml.XMLConstants;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.ErrorHandler;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.XMLReader;


/**
 * Represents a UML class model that can be read from and written to
 * files using the ClassML markup language.
 */
public class UmlClassModel{
	
	// PRIVATE MEMBER VARIABLES -----------------------------
	
	private String mName;
	private String mDescription;
	private Map<String,UmlNodeElement> mNodes;
	private Map<String,UmlRelationshipElement> mRelationships;
	
	
	// PRIVATE CONSTRUCTORS --------------------------------------
	private UmlClassModel(){}
	
	private UmlClassModel(String filename){
		
		mDescription = filename;
		readFromFile(filename);
	}
	
	
	
	
	// PUBLIC METHODS ---------------------------------
	
	/**
	 * Creates a new ClassModel from the specified ClassML file.
	 * @param filename path to a file in ClassML markup language
	 * @return a ClassModel object representing the model in filename
	 */
	public static UmlClassModel fromFile(String filename){
		return new UmlClassModel(filename);
	}
	
	/**
	 * Gets the name of the class model.
	 */
	public String getName(){
		return mName;
	}
	
	/**
	 * Returns a description of the class model.
	 */
	public String getDescription(){
		return mDescription;
	}
	
	/**
	 * Gets a reference to the map of nodes in this model.
	 */
	public Map<String,UmlNodeElement> getNodes(){
		return mNodes;
	}
	
	/**
	 * Gets a reference to the map of relationships in this model.
	 */
	public Map<String,UmlRelationshipElement> getRelationships(){
		return mRelationships;
	} 
	
	
	// PRIVATE METHODS -----------------------------------
	
	private void readFromFile(String filename){
		
		Schema schema = null;
	    try {
	    	SchemaFactory sf = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			schema = sf.newSchema(new StreamSource(getClass().getResourceAsStream("/classml.xsd")));
		} catch (SAXException e) {
			throw new RuntimeException("Could not read schema document: " + e.getMessage());
		}
	    
	    SAXParser saxParser = null;
	    try {
	    	SAXParserFactory spf = SAXParserFactory.newInstance();
		    spf.setSchema(schema);
		    spf.setNamespaceAware(true);
		    saxParser = spf.newSAXParser();
		} catch (ParserConfigurationException e) {
			throw new RuntimeException("A parser for the specified file could not be created.");
		} catch (SAXException e) {
			throw new RuntimeException("SAX error: " + e.getMessage());
		}
	    
	    UmlSaxHandler saxHandler = new UmlSaxHandler();
	    try {
			XMLReader reader = saxParser.getXMLReader();
			reader.setErrorHandler(new ErrorHandler(){

				@Override
				public void error(SAXParseException e) throws SAXException {
					throw new RuntimeException("Parse error: " + e.getMessage());
				}

				@Override
				public void fatalError(SAXParseException e)
						throws SAXException {
					throw new RuntimeException("Parse error: " + e.getMessage());
				}

				@Override
				public void warning(SAXParseException e) throws SAXException {
				}
				
			});
			reader.setContentHandler(saxHandler);
			reader.parse(filename);
		} catch (SAXException e) {
			throw new RuntimeException("Error reading the specified file: " + e.getMessage());
		} catch (IOException e) {
			throw new RuntimeException("Error reading the specified file.");
		}
	    
	    mName = saxHandler.getTitle();
	    mNodes = saxHandler.getNodes();
	    mRelationships = saxHandler.getRelationships();
	}
}
