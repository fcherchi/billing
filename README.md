# Coding Exercise
## High Level Description

### Code Organization
The code has been organized in one module with three main packages:
- Business:

    Contains the **business logic** for building orders and receipts. It has no knowledge of the Data package.

- Data:

    Contains the Data Storage mechanism.  
- Entities 
  
  Contains the **entities** (POJOs). Entities are known both in the *upper* Business layer and in the *lower* Data layer. 


### Design Principles

####Composition 
Composition has been widely used in the exercise. 


In order to follow the *Single Responsability Principle*, and as a way of a very simple Dependency Injection mechanism,
a design with constructors receiving the dependencies has been chosen.

####Interfaces
The Design Principle of *Code Against Interfaces And Not Implementations* has not been fully followed to preserve 
simplicity when only one implementor was planned, and when classes are not used for decoupling or inversion of 
control purposes.

For instance, for the `DataStorage` abstraction, even when only one implementation is provided, I understood that 
for the test it would be enough with a HashMap as storage, but in a real life implementation, only a new implementor
of `Data Storage` would be provided being for example `DataBaseStorage`.

####TDD
The code has been created with TDD approach testing all business logic. The coverage is 100% except for the boilerplate 
code in POJOs such as `hashcode`, `equals` or `toString` methods 


## How to Run 
An application as such (java executable) is not being provided, instead, the execution could be done using the Unit Tests.

To compile and test use maven as in `mvn clean install`. This should execute the tests and show the output in the console.

If preferred, from the IDE execute the test `ReceiptGeneratorTest.testReceiptCreation` to see the expected output.

*Note: A WIP investigation branch with an application reading the configuration from a JSON file is in the repo,
but not yet a real implementation with TDD, just a POC.*


 