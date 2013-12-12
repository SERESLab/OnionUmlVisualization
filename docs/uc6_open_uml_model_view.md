Open the UML Model View
================

**Use Case ID:** UC6  
**Use Case Name:** Open the UML Model View

**Created By:** John Meinken  
**Date Created:** 10/30/2013 

**Last Updated By:**   
**Last Revision Date:**  

#### Actors:
all users

#### Description:
Import a CML file into Eclipse and open the OnionUML model view for the file

#### Trigger:
First step when using the OnionUML tool.

#### Preconditions:

1.  User has already generated a CML file using srcml2classml.jar or similar tool.
2.  User has the OnionUML Eclipse plugin installed.
3.  User has the Onion UML Perspective open.

#### Normal Flow:

1.  User import the CML file into Eclipse  
	1.1  Right-click on the project folder in the Project Explorer  
	1.2  Select Import > General> File System  
	1.3  Navigate to the directory where the CML file is located  
	1.4  Select the CML file and click 'Finished'  
	1.5  The CML file will now be added to the project and visible in the Project Explorer  
2.  User double-clicks on the CML file  
3.  System displays the Onion UML model, populates the Diagram Control View with the appropriate options and adds a zoom control tool to the Eclipse action bar. 
4.  User has access to model and can perform all available actions related to that model.


#### Exceptions:
[Describe any anticipated error conditions that could occur during execution
of the use case, and define how the system is to respond to those conditions.
e.g. Exceptions to the Withdraw Case transaction  

* 2a. In step 2 of the normal flow, if the CML file has errors

	1.  Parse error is displayed


#### Frequency of Use:
Every time the plugin is used


#### Assumptions:
* User knows how to install and use the OnionUML plugin.
* User is able to generate a CML file.

#### Notes and Issues:

1.  Currently if the UML Diagram Control is not already open, clicking the CML file in step 2 does not open it.
