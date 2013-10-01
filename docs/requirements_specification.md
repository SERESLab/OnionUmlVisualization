
Onion Uml Visualization

Software Requirements Specification

Version 2

9/30/2013

<Samuel Addai>


Table of Contents
=================
* 1. INTRODUCTION

    * 1.1 PURPOSE
    * 1.2 SCOPE
    * 1.3 DATA DICTIONARY, ACRONYMS, AND ABBREVIATIONS
    * 1.4 REFERENCES
    
* 2. GENERAL DESCRIPTION

	* 2.1 PRODUCT PERSPECTIVE
	* 2.2 PRODUCT FUNCTIONS
	* 2.3 USER CHARETERISTICS
	* 2.4 GENERAL CONSTRAINTS
	* 2.5 ASSUMPTIONS AND DEPENDENCIES

* 3. SPECIFIC REQUIREMENTS

    * 3.1 EXTERNAL INTERFACE REQUIREMENTS
      * 3.1.1 User Interface
    * 3.2 FUNCTIONAL REQUIREMENTS
      * 3.2.1 Feature #1
      * 3.2.1 Feature #2
    * 3.3 USE CASES
      * 3.3.1 Use Case #1
      * 3.3.2 Search Dialog 
    * 3.4 CLASSES/OBJECTS
      * 3.4.1 Class/Object #1
      * 3.4.2 Class/Object #2
    * 3.5 DESIGN CONSTRAINTS
    * 3.6 OTHER REQUIREMENTS
    
* 4 ANALYSIS MODELS

   * 4.1 SEQUENCE DIAGRAMS
   * 4.2 DATA FLOW DIAGRAMS(DFD)
   * 4.3 STATE TRNASITION DIAGRAMS(STD)
   
* 5 CHANGE MANAGMENT PROCESS





####1. Introduction

The OnionUmlVisualization is an Eclipse plug-in that reduce the number of visible 
classes in a UML class diagram while preserving structure and semantics of the UML
elements. The goal is that developers will be able to view and understand a 
subsystems of a large software while being able to visualize how that system fits 
into the whole system.

**1.1 PURPOSE**

Currently some of the functions of the plug-in does not work. The purpose of this 
project is to make some of the functions of the plug-in work and also if possible 
add some more functionalities.

**1.2 SCOPE**

Onion Uml Visualization is a Uml class diagram visualization program.
This Eclipse plug-in will allow a developer(user) to visualize and understand how
the subsystem of a larger software relates to the whole system. Relation among 
class and object in the system could also b seen too. This plug-in cannot display
these diagrams unless a .cml file has already been generated and then open in the
Onion Uml Visualization program.

**1.3 DATA DICTIONARY, ACRONYMS, AND ABBREVIATIONS**

.cml Chemical Mark-up Language.

**1.3.1 DATA DICTIONARY**

* RelationshipType (Types of Relationships)

	* Aggregation
	* Association
	* DirectedAssociation
	* Composition
	* Dependency
	* Realization
	* Generalization
	* Hyper_DirectedAssociation
	* Hyper_Composition
	* Hyper_Dependancy
	* Hyper_Realization
	* HyperGeneralization
	
* UmlClassElement: (Elements of classes)

	* Name
	* StereoType
	* Visiblity
	* isAbstract
	* Attributes
	* Operations
	
* UmlClassModel: (More elements of classes)

	* Packages
	* Relationships
	* Description
	
* UmlOperation: (Operation in UML class diagram)

	* Visibility
	* returnType
	* isStatic
	* isAbstract
	
* ModelViewer: (View and workspace of GUI)

	* Zoom_Levels
	
* PerspectiveFactory: (Layout of UML)

	* PageLayout
	
* ClassElementEditPart: (Layout for classes)

	* Color
	* Name_Font
	* Stereotype_Font
	* Name_Abstract_Font
	* Normal_Font
	* Abstract_Font
	* Static_Font
	
* RelationshipElementEditPart: (Layout for relationships)

	* Line_Dask_Style
	* Label_Distance
	
* ClassFigure: (Layout for classes)  

	* Font
	* Label
	* PropertiesFigure
	* OperationsFigure
	* OnionRelationshipsFigure
	
**1.4 REFERENCES**

kagdi-VISSOFT07-Onion Graphs for Focus+Context Views of UML Class Diagrams
OnionUML Visualization Tool: Falcone-ICPC2013

####2. GENERAL DESCRIPTION

**2.1 PRODUCT PERSPECTIVE**
**2.2 PRODUCT FUNCTIONS**
**2.3 USER CHARETERISTICS**
**2.4 GENERAL CONSTRAINTS**
**2.5 ASSUMPTIONS AND DEPENDENCIES**

####3. SPECIFIC REQUIREMENTS

**3.1 EXTERNAL INTERFACE REQUIREMENTS**
**3.1.1 User Interface**

**3.2 FUNCTIONAL REQUIREMENTS**
**3.2.1 Feature #1**
**3.2.1 Feature #2**

**3.3 USE CASES**
**3.3.1 Use Case #1**

**3.3.2 Search Dialog**

**Use Case ID:** UC2  
**Use Case Name:** Search Dialog  

**Created By:** Braden Walters  
**Date Created:** 29 September 2013  

**Last Updated By:** Braden Walters  
**Last Revision Date:** 29 September 2013  

#### Actors:
Initialised by the Eclipse user. All input comes from the user and the data
which is extracted from the CML file.

#### Description:
The user can search by class name to return a list of resulting matches and then
specify the visibility of that class.

#### Trigger:
When the user strikes Control+F, the search window will open.

#### Preconditions:
1.  User has onion UML graph in focus.

#### Normal Flow:
1.  User strikes Control+F to launch search dialog.
2.  User enters search term into search box.
3.  User strikes the return key or the search button.
4.  System performs case insensitive partial-string search of all classes in the
    current class model.
5.  System displays results in grid with a check box near each entry (default:
    selected checkbox).
6.  User selects or unselects check boxes in result grid.
7.  Upon closing dialog, system updates visualisation to only show classes that
    have not been unselected.

#### Alternative Flows:
None

#### Exceptions:
None

#### Includes:
None

#### Frequency of Use:
To be determined.

#### Special Requirements:
1.  The system must be able to resolve searches in reasonable time.
2.  The system must be able to update the visualisation in reasonable time.

#### Assumptions:
None

#### Notes and Issues:
None

**3.4 CLASSES/OBJECTS**
*3.4.1 Class/Object #1*
*3.4.2 Class/Object #2*

**3.5 DESIGN CONSTRAINTS**

**3.6 OTHER REQUIREMENTS**
    
####4 ANALYSIS MODELS

**4.1 SEQUENCE DIAGRAMS**
**4.2 DATA FLOW DIAGRAMS(DFD)**
**4.3 STATE TRNASITION DIAGRAMS(STD)**
  
####5 CHANGE MANAGMENT PROCESS
 