Example Use Case
================

**Use Case ID:** [ID HERE]  
**Use Case Name:** [NAME_HERE]  

**Created By:** [CREATED BY HERE]  
**Date Created:** [DATE CREATED HERE]  

**Last Updated By:** [REVISION BY HERE]  
**Last Revision Date:** [REVISION DATE HERE]  

#### Actors:
[An actor is a person or other entity external to the software system being
specified who interacts with the system and performs use cases to accomplish
tasks. Different actors often correspond to different user classes, or roles,
identified from the customer community that will use the product. Name the actor
that will be initiating this use case (primary) and any other actors who will
participate in completing the use case (secondary).] 

#### Description:
[Provide a brief description of the reason for and outcome of this use case.]

#### Trigger:
[Identify the event that initiates the use case. This could be an external
business event or system event that causes the use case to begin, or it could
be the first step in the normal flow.]

#### Preconditions:
[List any activities that must take place, or any conditions that must be true,
before the use case can be started. Number each pre-condition. e.g.

1.  Customer has active deposit account with ATM privileges
2.  Customer has an activated ATM card.]

#### Normal Flow:
[Provide a detailed description of the user actions and system responses that
will take place during execution of the use case under normal, expected
conditions. This dialog sequence will ultimately lead to accomplishing the goal
stated in the use case name and description.

1.  Customer inserts ATM card
2.  Customer enters PIN
3.  System prompts customer to enter language performance English or Spanish
4.  System validates if customer is in the bank network
5.  System prompts user to select transaction type
6.  Customer selects Withdrawal From Checking
7.  System prompts user to enter withdrawal amount
8.  ...
9.  System ejects ATM card]

Note: The above can also have sub points for e.g.,

	4.
		4.1
		4.2 

#### Alternative Flows:

##### [Alternative Flow 1 – Not in Network]
[Document legitimate branches from the main flow to handle special conditions
(also known as extensions). For each alternative flow reference the branching
step number of the normal flow and the condition which must be true in order for
this extension to be executed. e.g. Alternative flows in the Withdraw Cash
transaction:

* 4a.  In step 4 of the normal flow, if the customer is not in the bank network  

	1.  System will prompt customer to accept network fee
	2.  Customer accepts
	3.  Use Case resumes on step 5

* 4b.  In step 4 of the normal flow, if the customer is not in the bank network  

	1.  System will prompt customer to accept network fee
	2.  Customer declines
	3.  Transaction is terminated
	4.  Use Case resumes on step 9 of normal flow

Note: Insert a new row for each distinctive alternative flow. ]

#### Exceptions:
[Describe any anticipated error conditions that could occur during execution
of the use case, and define how the system is to respond to those conditions.
e.g. Exceptions to the Withdraw Case transaction  

* 2a. In step 2 of the normal flow, if the customer enters and invalid PIN

	1.  Transaction is disapproved
	2.  Message to customer to re-enter PIN
	3.  Customer enters correct PIN
	4.  Use Case resumes on step 3 of normal flow]

#### Includes:
[List any other use cases that are included ("called") by this use case. Common
functionality that appears in multiple use cases can be split out into a
separate use case that is included by the ones that need that common
functionality. e.g. steps 1-4 in the normal flow would be required for all types
of ATM transactions- a Use Case could be written for these steps and "included"
in all ATM Use Cases.]

#### Frequency of Use:
[How often will this Use Case be executed. This information is primarily useful
for designers. e.g. enter values such as 50 per hour, 200 per day, once a week,
once a year, on demand etc.]

#### Special Requirements:
[Identify any additional requirements, such as nonfunctional requirements, for
the use case that may need to be addressed during design or implementation.
These may include performance requirements or other quality attributes.]

#### Assumptions:
[List any assumptions that were made in the analysis that led to accepting this
use case into the product description and writing the use case description. e.g.
For the Withdraw Cash Use Case, an assumption could be: The Bank Customer
understands either English or Spanish language.]

#### Notes and Issues:
[List any additional comments about this use case or any remaining open issues
or TBDs (To Be Determined) that must be resolved. e.g.

1.  What is the maximum size of the PIN that a use can have?]  