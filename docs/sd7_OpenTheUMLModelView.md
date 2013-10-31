## Opening and Closing The UML Model View - Sequence Diagram ##


![Class Model showing base class and ui interaction with Eclipse](images/sequence_diagram_open_model.png)

**Notes**

This sequence diagram does not show the display of the zoom selector in the Eclipse Action Bar.  This separate process is handled by the ModelViewerActionBarContributor class.  Its behavior is defined in the plugin.xml file.  The zoom selector is shown when a OnionUML model is visible.  Otherwise, it is hidden.  

The UML Diagram Control View is automatically opened and displayed when the Onion UML perspective is loaded, not when a model view is created.  If it is closed manually, it will not reopen automatically.  To reopen, the user has to manually reopen by selecting Window -> Show View -> Other -> Other -> UML Diagram Control.



