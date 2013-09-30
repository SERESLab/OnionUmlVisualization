Example Use Case
================

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