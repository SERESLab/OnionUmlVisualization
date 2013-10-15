
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
      * 3.2.1 Feature #1 - Zoom Shorcuts
      * 3.2.2 Feature #2 - Stereotype Display
      * 3.2.3 Feature #3 - Unique Stereotype Colors
      * 3.2.4 Feature #4 - Class Search Box
      * 3.2.5 Feature #5 - View Package Selector
      * 3.2.6 Feature #6 - Save Layout
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
	
- edu.ysu.onionuml
	- Activator.java
		- mPlugin
		- PLUGIN_ID
		- getDefault()
		- getImageDescriptor(String)
		- Activator()
		- start(BundleContext)
		- stop(BundleContext)
		- compact
- edu.ysu.onionuml.compact
	- DiagramGraph.java
		- Node
		- mClassNodeMap
		- mElements
		- mRootNodes
		- addElement(ClassElementGraphicalModel)
		- addRelationship(RelationshipElementGraphicalModel, ClassElementGraphicalModel, ClassElementGraphicalModel)
		- update()
		- updateGraph()
- edu.ysu.onionuml.core
	- ClassmlWriter.java
		- XMLNS
		- write(UmlClassModel, String)
	- RelationshipType.java
		- AGGREGATION
		- ASSOCIATION
		- COMPOSITION
		- DEPENDENCY
		- DIRECTEDASSOCIATION
		- GENERALIZATION
		- HYPER_AGGREGATION
		- HYPER_ASSOCIATION
		- HYPER_COMPOSITION
		- HYPER_DEPENDENCY
		- HYPER_DIRECTEDASSOCIATION
		- HYPER_GENERALIZATION
		- HYPER_REALIZATION
		- REALIZATION
		- parseRelationshipType(String)
		- toString()
	- UmlAttribute.java
		- dataType
		- name
		- visibility
		- UmlAttribute(Visibility, String, String)
		- toString()
	- UmlClassElement.java
		- mAttributes
		- mIsAbstract
		- mName
		- mOperations
		- mStereotype
		- mVisibility
		- UmlClassElement()
		- UmlClassElement(String)
		- UmlClassElement(String, String)
		- addAttribute(UmlAttribute)
		- addOperation(UmlOperation)
		- getAttributes()
		- getIsAbstract()
		- getName()
		- getOperations()
		- getStereotype()
		- getVisibility()
		- setIsAbstract(boolean)
		- setName(String)
		- setStereotype(String)
		- setVisibility(Visibility)
		- toString()
	- UmlClassModel.java
		- fromFile(String)
		- mDescription
		- mName
		- mPackages
		- mRelationships
		- UmlClassModel(String)
		- UmlClassModel(String, String, Map<String, UmlPackageElement>, Map<String, UmlRelationshipElement>)
		- getDescription()
		- getName()
		- getPackages()
		- getRelationships()
		- readFromFile(String)
		- toFile(String)
	- UmlOperation.java
		- isAbstract
		- isStatic
		- name
		- parameters
		- returnType
		- visibility
		- UmlOperation(Visibility, String, String, boolean, boolean)
		- toString()
	- UmlOperationParameter.java
		- dataType
		- name
		- UmlOperationParameter(String, String)
		- toString()
	- UmlPackageElement.java
		- mClasses
		- mName
		- UmlPackageElement(String)
		- UmlPackageElement(String, Map<String, UmlClassElement>)
		- addClass(String, UmlClassElement)
		- getClasses()
		- getName()
		- setName(String)
	- UmlRelationshipElement.java
		- Multiplicity
		- mHeadId
		- mHeadMultiplicityMax
		- mHeadMultiplicityMin
		- mLabel
		- mTailId
		- mTailMultiplicityMax
		- mTailMultiplicityMin
		- mType
		- UmlRelationshipElement(String, String, String, RelationshipType)
		- UmlRelationshipElement(String, String, String, RelationshipType, Multiplicity, Multiplicity, Multiplicity, Multiplicity)
		- getHeadId()
		- getHeadMultiplicityMax()
		- getHeadMultiplicityMin()
		- getLabel()
		- getTailId()
		- getTailMultiplicityMax()
		- getTailMultiplicityMin()
		- getType()
		- setHeadId(String)
		- setHeadMultiplicityMax(Multiplicity)
		- setHeadMultiplicityMin(Multiplicity)
		- setLabel(String)
		- setTailId(String)
		- setTailMultiplicityMax(Multiplicity)
		- setTailMultiplicityMin(Multiplicity)
		- setType(RelationshipType)
	- UmlSaxHandler.java
		- mElementNames
		- mObjects
		- mPackages
		- mRelationships
		- mTitle
		- endElement(String, String, String)
		- getPackages()
		- getRelationships()
		- getTitle()
		- startElement(String, String, String, Attributes)
	- Visibility.java
		- PRIVATE
		- PROTECTED
		- PUBLIC
		- parseVisibility(String)
		- toString()
- edu.ysu.onionuml.ui
	- DiagramControlView.java
		- ID
		- PADDING
		- TEXT_COMPACT_ALL
		- TEXT_COMPACT_SELECTED
		- TEXT_COMPACTION_CONTROLLER
		- TEXT_EXPAND_ALL
		- TEXT_EXPAND_SELECTED
		- TEXT_NO_DIAGRAM
		- TEXT_PACKAGES_CONTROLLER
		- TEXT_RESET_ALL
		- TEXT_SELECT_ALL
		- TEXT_SELECT_NONE
		- mCompactionControlView
		- mCurrentClassDiagram
		- mDefaultView
		- mPackagesView
		- mPackageTable
		- mParentComposite
		- createCompactionController(Composite)
		- createDefaultView(Composite)
		- createPackagesController(Composite)
		- createPartControl(Composite)
		- getCurrentClassDiagram()
		- onCompactSelectedPressed()
		- onExpandSelectedPressed()
		- populatePackageTable()
		- setCurrentClassDiagram(ClassDiagramEditPart)
		- setFocus()
	- ModelViewer.java
		- ID
		- ZOOM_LEVELS
		- mEditorInput
		- mModel
		- ModelViewer()
		- configureGraphicalViewer()
		- createPartControl(Composite)
		- dispose()
		- doSave(IProgressMonitor)
		- doSaveAs()
		- getAdapter(Class)
		- init(IEditorSite, IEditorInput)
		- initializeGraphicalViewer()
		- isDirty()
		- isSaveAsAllowed()
	- ModelViewerActionBarContributor.java
		- buildActions()
		- contributeToToolBar(IToolBarManager)
		- declareGlobalActionKeys()
	- ModelViewerInput.java
		- mModelList
		- mNextId
		- mId
		- ModelViewerInput(ClassDiagramGraphicalModel)
		- dispose()
		- equals(Object)
		- exists()
		- getAdapter(Class)
		- getId()
		- getImageDescriptor()
		- getModel()
		- getName()
		- getPersistable()
		- getToolTipText()
	- PerspectiveFactory.java
		- BOTTOM_FOLDER_ID
		- BOTTOM_FOLDER_RATIO
		- PROJECT_EXPOLORER_RATIO
		- createInitialLayout(IPageLayout)
- edu.ysu.onionuml.ui.graphics
	- EditPartFactory.java
		- createEditPart(EditPart, Object)
	- IEventListener.java
		- eventOccured(String)
	- IEventRegistrar.java
		- registerEventListener(IEventListener)
		- unregisterEventListener()
- edu.ysu.onionuml.ui.graphics.editparts
	- ClassDiagramEditPart.java
		- EVENT_ACTIVATED
		- EVENT_REFRESH_REQUIRED
		- mSelectedClasses
		- activate()
		- addToSelection(ClassElementEditPart)
		- createEditPolicies()
		- createFigure()
		- deactivate()
		- eventOccured(String)
		- getDragTracker(Request)
		- getModelChildren()
		- getSelectedClasses()
		- lookupEditPartById(String)
		- removeFromSelection(ClassElementEditPart)
	- ClassElementEditPart.java
		- ABSTRACT_FONT
		- CLASS_COLOR
		- NAME_ABSTRACT_FONT
		- NAME_FONT
		- NORMAL_FONT
		- STATIC_FONT
		- STEREOTYPE_FONT
		- mDragLocation
		- activate()
		- createEditPolicies()
		- createFigure()
		- getDragTracker(Request)
		- mouseDoubleClicked(MouseEvent)
		- mouseDragged(MouseEvent)
		- mouseEntered(MouseEvent)
		- mouseExited(MouseEvent)
		- mouseHover(MouseEvent)
		- mouseMoved(MouseEvent)
		- mousePressed(MouseEvent)
		- mouseReleased(MouseEvent)
		- refreshVisuals()
	- HyperClassElementEditPart.java
		- createEditPolicies()
		- createFigure()
		- refreshVisuals()
	- RelationshipElementEditPart.java
		- LABEL_DISTANCE
		- LINE_DASH_STYLE
		- createEditPolicies()
		- createFigure()
		- getDragTracker(Request)
		- isSelectable()
		- makeHeadDecoration(RelationshipType)
		- makeMultiplicityString(Multiplicity, Multiplicity)
		- refreshVisuals()
- edu.ysu.onionuml.ui.graphics.figures
	- ClassFigure.java
		- mContentFont
		- mIsOnion
		- mNameLabel
		- mOnionRelationshipsFigure
		- mOperationsFigure
		- mPropertiesFigure
		- mStereotypeLabel
		- ClassFigure(Color, Font, Font, Font)
		- addOnionRelationship(RelationshipType)
		- addOperation(String, Image, Font)
		- addProperty(String, Image, Font)
		- clear()
		- getNameString()
		- getOperationIndex(int, int)
		- getOperationString(int)
		- getPropertyIndex(int, int)
		- getPropertyString(int)
		- getStereotypeString()
		- reconstruct()
		- setIsOnion(boolean)
		- setNameFont(Font)
		- setNameIcon(Image)
		- setNameString(String)
		- setStereotypeString(String)
	- ClassSectionFigure.java
		- SPACING
		- ClassSectionFigure(boolean)
	- OnionRelationshipFigure.java
		- BORDER_THICKNESS
		- CANVAS
		- LINE_DASH
		- mFillColor
		- mLine
		- mLinePoints
		- mPolygon
		- mRelationshipType
		- OnionRelationshipFigure(RelationshipType)
		- makeShapes(RelationshipType)
		- paint(Graphics)
		- primTranslate(int, int)
- edu.ysu.onionuml.ui.graphics.graphicalmodels
	- ClassDiagramGraphicalModel.java
		- NUM_CLASSES_FOR_HYPEREDGES
		- mClassIdMap
		- mClassModel
		- mClassSizeChanged
		- mDiagramGraph
		- mElements
		- mListener
		- mRelationshipIdMap
		- ClassDiagramGraphicalModel(UmlClassModel)
		- eventOccured(String)
		- getClassModel()
		- getElements()
		- initEdges()
		- layout()
		- layoutEdges(Map<String, ArrayList<String>>, RelationshipType)
		- lookupGraphicalModelById(String)
		- lookupIdByGraphicalModel(IElementGraphicalModel)
		- registerEventListener(IEventListener)
		- unregisterEventListener()
		- update()
	- ClassElementGraphicalModel.java
		- EVENT_SIZE_CHANGED
		- mActualHead
		- mChildRelationships
		- mClassElement
		- mIsCompacted
		- mIsHyper
		- mIsParentCompacted
		- mIsVisible
		- mListener
		- mPackageElement
		- mPosition
		- mSize
		- ClassElementGraphicalModel(UmlClassElement, UmlPackageElement)
		- getActualHead()
		- getChildRelationships()
		- getClassElement()
		- getPackageElemet()
		- getPosition()
		- getSize()
		- isCompacted()
		- isHyper()
		- isParentCompacted()
		- isVisible()
		- registerEventListener(IEventListener)
		- setChildRelationships(List<RelationshipType>)
		- setIsCompacted(boolean)
		- setIsHyper(boolean, ClassElementGraphicalModel)
		- setIsParentCompacted(boolean)
		- setIsVisible(boolean)
		- setPosition(Point)
		- setSize(Dimension)
		- unregisterEventListener()
	- IElementGraphicalModel.java
		- getPosition()
		- getSize()
		- setPosition(Point)
		- setSize(Dimension)
	- RelationshipElementGraphicalModel.java
		- mPosition
		- mRelationship
		- mSize
		- RelationshipElementGraphicalModel(UmlRelationshipElement)
		- getPosition()
		- getRelationshipElement()
		- getSize()
		- setPosition(Point)
		- setSize(Dimension)
- edu.ysu.onionuml.ui.handler
	- OpenClassModelCommandHandler.java
		- OPEN_DIALOG_TITLE
		- OPEN_FILTER_EXT
		- OPEN_FILTER_NAMES
		- addHandlerListener(IHandlerListener)
		- dispose()
		- execute(ExecutionEvent)
		- isEnabled()
		- isHandled()
		- removeHandlerListener(IHandlerListener)
		
**1.4 REFERENCES**

  * kagdi-VISSOFT07-Onion Graphs for Focus+Context Views of UML Class Diagrams
  * OnionUML Visualization Tool: Falcone-ICPC2013

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

- **Name:** 
**Zoom Shortcuts**

- **Description:**
User can zoom in or out using standard 'CTRL+' and 'CTRL-' or CTRL with Scroll Wheel

- **Implementation:** 
This should only require modification of the plugin.xml file.  Zoom capability is already available from the Eclipse interface (currently using a dropdown box in the Eclipse toolbar).  We should be able to define an extension point in plugin.xml that will create shortcuts for this built-in Eclipse capability.

- **Resources:**
Eclipse Resources on Extension Points - http://www.eclipse.org/resources/?category=Extension%20point

- **Estimated Time:** 
3 hours 


**3.2.2 Feature #2**

- **Name:** 
**Stereotype Display**

- **Description:**
User can choose whether to display the stereotype in the class object above the class name as '&lt;&lt;stereotype>>'

- **Implementation:** 
    - Step 1. [COMPLETE] Create a preferences menu where the user can choose whether to display or hide class stereotypes.  This will be implemented as a new Eclipse PreferencePage or FieldEditorPreferencePage.

    - Step 2. Get every class opject to display the string '&lt;&lt;stereotype>>' above the class name.  (modifies onionuml.ui.graphics.figures.ClassFigure and possibly also onionuml.ui.graphics.editparts.ClassElementEditPart)

    - Step 3. Get the user preference for viewing stereotypes out of the Eclipse Preference Store and use it to display or hide the stereotype string.  (modifies same class(es) as Step 2, also consider building a new class that will return values from the Preference Store)

    - Step 4. Get stereotype name from .cml file and store as part of the class model.  Use this name to display the correct stereotype value for each class.  (modifies onionuml.core classes)

    - Step 5. We may need to build a .cml file for testing that has at least one of each stereotype represented.

- **Outstanding Questions**
How are stereotypes determined in the generation of the .cml file?  How are they represented in the .cml file?  Does the current model collect and store stereotype values, or will the model need to be modified to do so?

- **Estimated Time:** 
    - Step 1: 8 hours
    - Step 2: 1 hour
    - Step 3: 4 hours
    - Step 4: XX hours (time will be determined after outstanding questions are answered) 


**3.2.3 Feature #3**

- **Name:** 
**Unique Stereotype Colors**

- **Description:**
A user should be able to select the color scheme of the diagram. Certain classes or sets of classes that belong to a specific category (or stereotype) should be colored the same.  The default color scheme should be: (Stereoptype and RGB values)
    - Control - red 255 204 204
    - Boundary - blue 204 236 255
    - Entity - green 225 255 226
    - yellow for notes 255 255 226    

- **Dependencies**
requires functionality created in Feature 2 (Steps 1, 4 and 5)   
  
- **Implementation:** 
    - Step 1. [COMPLETE] Add elements to the preferences menu where the user can choose whether to show stereotypes in different colors and select which colors to use for each stereotype.  The default colors should be set to values in the table above. (modifies the Preferences package)

    - Step 2. Using the user's color preferences and the stereotype values for each class, assign the appropriate color to class objects.  (modifies onionuml.ui.graphics.figures.ClassFigure and possibly also onionuml.ui.graphics.editparts.ClassElementEditPart)

- **Estimated Time:** 
    - Step 1: 3 hours
    - Step 2: 10 hour


**3.2.4 Feature #4**

- **Name:** 
**Class Search Box**

- **Description:**
User can open a form for searching classes with Ctrl+F.  They will see a text box where they can enter a regular expression for searching class names.  Search results will appear as a list with checkboxes for each class.  When complete, a new diagram view will open with only the selected classes shown.

- **Implementation:** 


- **Estimated Time:** 


**3.2.5 Feature #5**

- **Name:** 
**View Package Selector**

- **Description:**
Currently, a selectable list of packages is shown in the Diagram Control View.  We need to implement the functionality of this tool. When a user deselects a package, the classes from that package should disappear from the diagram.  When a user reselects a package, the classes should reappear.

- **Implementation:** 


- **Estimated Time:** 


**3.2.6 Feature #6**

- **Name:** 
**Save Layout**

- **Description:**
Users will be able to store a specific layout to a file.  The layout file will be saved and stored together with the .cml file.  Users will need both the layout and .cml (model) files to reopen a project layout later.

- **Implementation:** 


- **Estimated Time:** 


**3.3 USE CASES**

**3.3.1 Use Case #1**

**3.3.2 Use Case #2**

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
2.  The system must be able to update the visualization in reasonable time.

#### Assumptions:
None

#### Notes and Issues:
None

**3.4 CLASSES/OBJECTS**

**3.4.1 Class/Object #1**

**3.4.2 Class/Object #2**

**3.5 DESIGN CONSTRAINTS**

**3.6 OTHER REQUIREMENTS**
    
####4 ANALYSIS MODELS

**4.1 SEQUENCE DIAGRAMS**

**4.2 DATA FLOW DIAGRAMS(DFD)**

**4.3 STATE TRNASITION DIAGRAMS(STD)**
  
####5 CHANGE MANAGMENT PROCESS
 