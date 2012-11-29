package edu.ysu.onionuml.core;

import java.io.File;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import edu.ysu.onionuml.core.UmlRelationshipElement.Multiplicity;

/**
 * Object for writing ClassML files.
 */
public class ClassmlWriter {
	
	private static final String XMLNS = "xmlns://classml";
	
	/**
	 * Writes the specified class model to the specified filename.
	 * @throws ParserConfigurationException from the document builder
	 * @throws TransformerException from the document builder
	 */
	public static void write(UmlClassModel model, String filename) throws ParserConfigurationException, TransformerException{
		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = docBuilderFactory.newDocumentBuilder();
		
		Document doc = docBuilder.newDocument();
		Element classmlElement = doc.createElement("classml");
		doc.appendChild(classmlElement);
		classmlElement.setAttribute("xmlns", XMLNS);
		classmlElement.setAttribute("title", model.getName());
		
		Map<String,UmlPackageElement> packages = model.getPackages();
		Iterator<Entry<String,UmlPackageElement>>  itPackages = packages.entrySet().iterator();
		while (itPackages.hasNext()) {
			Entry<String,UmlPackageElement> pkgPairs = (Entry<String,UmlPackageElement>)itPackages.next();
			String pkgId = pkgPairs.getKey();
			UmlPackageElement p = pkgPairs.getValue();
			Element pkgElement = doc.createElement("package");
			classmlElement.appendChild(pkgElement);
			pkgElement.setAttribute("id", pkgId);
			pkgElement.setAttribute("label", p.getName());
			
			if(p.getClasses().size() > 0){
				Element classesElement = doc.createElement("classes");
				pkgElement.appendChild(classesElement);
				
				Map<String,UmlClassElement> classes = p.getClasses();
				Iterator<Entry<String,UmlClassElement>>  itClasses = classes.entrySet().iterator();
				while (itClasses.hasNext()) {
					Entry<String,UmlClassElement> classPairs = (Entry<String,UmlClassElement>)itClasses.next();
					String classId = classPairs.getKey();
					UmlClassElement c = classPairs.getValue();
					Element classElement = doc.createElement("class");
					classesElement.appendChild(classElement);
					classElement.setAttribute("id", classId);
					classElement.setAttribute("label", c.getName());
					classElement.setAttribute("isAbstract", Boolean.toString(c.getIsAbstract()));
					classElement.setAttribute("stereotype", c.getStereotype());
					classElement.setAttribute("visibility", c.getVisibility().toString());
					
					if(c.getAttributes().size() > 0){
						Element attributesElement = doc.createElement("attributes");
						classElement.appendChild(attributesElement);
						for(UmlAttribute a : c.getAttributes()){
							Element attributeElement = doc.createElement("attribute");
							attributesElement.appendChild(attributeElement);
							attributeElement.setAttribute("dataType", a.dataType);
							attributeElement.setAttribute("label", a.name);
							attributeElement.setAttribute("visibility", a.visibility.toString());
						}
					}
					
					if(c.getOperations().size() > 0){
						Element operationsElement = doc.createElement("operations");
						classElement.appendChild(operationsElement);
						for(UmlOperation o : c.getOperations()){
							Element operationElement = doc.createElement("operation");
							operationsElement.appendChild(operationElement);
							operationElement.setAttribute("returnType", o.returnType);
							operationElement.setAttribute("label", o.name);
							operationElement.setAttribute("visibility", o.visibility.toString());
							operationElement.setAttribute("isAbstract", Boolean.toString(o.isAbstract));
							operationElement.setAttribute("isStatic", Boolean.toString(o.isStatic));
							for(UmlOperationParameter op : o.parameters){
								Element parameterElement = doc.createElement("parameter");
								operationElement.appendChild(parameterElement);
								parameterElement.setAttribute("dataType", op.dataType);
								parameterElement.setAttribute("label", op.name);
							}
						}
					}
				}
			}
		}
		
		if(model.getRelationships().size() > 0){
			Element relationshipsElement = doc.createElement("relationships");
			classmlElement.appendChild(relationshipsElement);
			
			Map<String,UmlRelationshipElement> relationships = model.getRelationships();
			Iterator<Entry<String,UmlRelationshipElement>>  itRelationships = relationships.entrySet().iterator();
			while (itRelationships.hasNext()) {
				Entry<String,UmlRelationshipElement> relPairs = (Entry<String,UmlRelationshipElement>)itRelationships.next();
				String relId = relPairs.getKey();
				UmlRelationshipElement r = relPairs.getValue();
				Element relationshipElement = doc.createElement("relationship");
				relationshipsElement.appendChild(relationshipElement);
				relationshipElement.setAttribute("id", relId);
				relationshipElement.setAttribute("label", r.getLabel());
				relationshipElement.setAttribute("head", r.getHeadId());
				relationshipElement.setAttribute("tail", r.getTailId());
				relationshipElement.setAttribute("type", r.getType().toString());
				if(r.getHeadMultiplicityMin() != Multiplicity.NONE){
					relationshipElement.setAttribute("headMultiplicityMin",
							r.getHeadMultiplicityMin().toString());
				}
				if(r.getHeadMultiplicityMax() != Multiplicity.NONE){
					relationshipElement.setAttribute("headMultiplicityMax",
							r.getHeadMultiplicityMax().toString());
				}
				if(r.getTailMultiplicityMin() != Multiplicity.NONE){
					relationshipElement.setAttribute("tailMultiplicityMin",
							r.getTailMultiplicityMin().toString());
				}
				if(r.getTailMultiplicityMax() != Multiplicity.NONE){
					relationshipElement.setAttribute("tailMultiplicityMax",
							r.getTailMultiplicityMax().toString());
				}
			}
		}
		
		// write file contents
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		transformer.setOutputProperty(OutputKeys.INDENT, "yes");
		transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "1");
		transformer.transform(new DOMSource(doc), new StreamResult(new File(filename)));
	}
}
