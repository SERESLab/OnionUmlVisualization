package edu.ysu.onionuml.core;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.HashMap;
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
	private Map<String,UmlPackageElement> mPackages =
			new HashMap<String,UmlPackageElement>();
	private Map<String,UmlRelationshipElement> mRelationships =
			new HashMap<String,UmlRelationshipElement>();
	
	
	// CONSTRUCTORS --------------------------------------
	
	private UmlClassModel(String filename){
		if(filename == null){
			throw new InvalidParameterException();
		}
		
		mDescription = filename;
		readFromFile(filename);
	}
	
	/**
	 * Constructs a new class model with the specified parameters.
	 * @param name the name of the model
	 * @param description description of the model, may be null
	 * @param packages map of package ids to package element objects, may be null
	 * @param relationships map of relationship ids to relationship element objects,
	 * may be null
	 * @throws InvalidParameterException if the name string is null
	 */
	public UmlClassModel(String name, String description, Map<String,UmlPackageElement> packages,
			Map<String,UmlRelationshipElement> relationships){
		
		if(name == null){
			throw new InvalidParameterException();
		}
		
		mName = name;
		mDescription = (description != null ? description : "");
		
		if(packages != null){
			mPackages = packages;
		}
		if(relationships != null){
			mRelationships = relationships;
		}
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
	 * Writes the class model to the specified file in ClassML format.
	 */
	public void toFile(String filename){
		if(filename == null){
			throw new InvalidParameterException();
		}
		
		try{
			ClassmlWriter.write(this, filename);
		}
		catch(Exception e){
			throw new RuntimeException(e);
		}
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
	 * Gets a reference to the map of packages in this model.
	 */
	public Map<String,UmlPackageElement> getPackages(){
		return mPackages;
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
	    mPackages = saxHandler.getPackages();
	    mRelationships = saxHandler.getRelationships();
	}
}
