Save Layout
================

**Use Case ID:** UC7  
**Use Case Name:** Save Layout  

**Created By:** John Meinken  
**Date Created:** 10/30/13  

**Last Updated By:**  
**Last Revision Date:**   

#### Actors:
all users 

#### Description:
The CML file only contains the UML model, not the layout.  This use case enables the user to save a separate layout file that can later be reopened together with its corresponding CML file.

#### Trigger:
When a new OnionUML model is opened from a CML file, it initializes to a default layout.  As soon as this initial layout is modified by the user (by moving classes or relationships, compacting a group of classes into OnionUML format, etc.), this use case can be used.

#### Preconditions:

1.  User has opened a UML Model View from a CML file in Eclipse

#### Normal Flow:


1.  User modifies the model view layout (see UC1, UC2, UC5)
2.  System displays a * character after the model name in the UML model View selection tab, indicating that the view has changed and can be saved.
3.  User uses the Eclipse menu to select File > Save / Save As / Save All
4.  A dialog box will prompt the user for the layout file name.
5.  User enters the desired layout file name.
6.  System saves the layout under the provided file name with ".onion" as the file extension.
7.  System changes the model name in the UML model View selection tab from the .CML file name to the newly created .ONION file name.
8.  System removes the * character from the UML model View selection tab, indicating that there are no new changes to the layout since the last save.


#### Alternative Flows:

##### [Alternative Flow 1 – Already Saved as .ONION file]

* 3a.  In step 3 of the normal flow, if user selects Save or Save All and the .onion file already exists

	1.  Use Case resumes on step 6 (skip steps 4 and 5)


#### Exceptions:  

* 5a.  In step 5 of the normal flow, if user enters a layout name that already exists 

	1.  System prompts user if they would like to overwrite the existing file.
	2.  If yes, Use Case  resumes on step 6.  If no, Use Case resumes on step 4.

#### Includes:
Includes any use cases that modify the layout.  Currently, Use Cases UC1, UC2 and UC5.

#### Notes and Issues:

1.  What is the best extension for the layout file?  I put .onion, but that might not be the best choice.
