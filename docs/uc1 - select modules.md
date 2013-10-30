**Use Case ID:** UC2  
**Use Case Name:** Select modules 

**Created By:** Samuel Addai
**Date Created:** 20 September 2013  

**Last Updated By:** Dominic Pascarella
**Last Revision Date:** 29 October 2013  


## Primary Actor
All users

## Story
A user loads a .cml file to see then uml diagrams of an existing system.

## Trigger
A user runs the OnioUmlVisualization plugin as an eclipse application, selects the Onion Uml perspective and open a .cml file

## Preconditions
* The user has all the plugins and the dependencies for the OnioUmlVisualization plugin to run properly installed.

* The user has a .cml file generated using the right method.

## Postconditions
The uml diagrams in the .cml are extracted and displayed

## Normal Flow
1. User  selects the onion uml perspective and opens a .cml file
2. System displays the diagrams in the .cml file in the display window
3. User selects modules 
4. User click on the UML Diagram control
5. Include :: Compress selected module. User click on Compress selected 
6. System compress the selected module

## Alternative Flows
#### Alternative Flow 1 – expand
* 3a  If selected module is already compressed
1. User click on the UML Diagram control
2. Include :: Expand modules. User click on Expand selected 
3. System compress the selected module
#### Alternative Flow 1 – move
* 3b
1. Include ::Move module 
2. System change position of the selected
## Frequency of Use
10
## Special Requirements
The .cml file must be generated using  the src2srcML and srcML2classML method
## Assumptions
The version of the eclipse can run the OnionUmlVisualization plugin

