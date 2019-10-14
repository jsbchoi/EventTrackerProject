## Week 12 RESTful Payroll App
### Overview

This is a simple API designed to help organize and keep track of employee payroll. I will be using Angular to develop a GUI for CRUD operations and consuming the data from the REST endpoints.

### Technologies used

* Git
* Java
* Spring Data JPA
* Hibernate
* RESTful Services
* Spring Boot
* Spring Tool Suite 4
* MAMP
* MySQL/MySQL Workbench

### Endpoints:
| Return Type | Route| Functionality |
| --- | --- | --- |
|List<Employee>| GET api/employees | Gets all employees|
|Employee| GET api/employees/{id} | Gets employee by id|
|List<Employee>|GET api/employees/search/{role}| Gets all employees with a specific role|
|Employee| POST api/employees | Creates new employee |
|Employee| PUT api/employees/{id} | Replaces an existing employee by id |
|Boolean| DELETE api/employees/{id} |Deletes an existing post by id |
|List<Record>| GET api/records | Gets all records|
|List<Record>| GET api/payroll/{prId}/records | Gets records by payroll id|
|List<Record>|GET api/employees/{empId}/records| Gets all records by employee id|
|Record| POST payroll/{prId}/{empId}/records | Creates new record by payroll and employee |
|Record| PUT payroll/{prId}/{empId}/records/{id} | Replaces an existing record by id |
|Boolean| DELETE api/records/{id} |Deletes an existing record by id |
|List<Payroll>| GET api/payroll | Gets all payroll entries|
|Payroll| GET api/payroll/{prId}| Gets one payroll by payroll id|
|Payroll|POST api/payroll | Creates new payroll|
|Payroll| PUT payroll/{id} | Replaces an existing payroll by id |
|Boolean| DELETE api/payroll/{id} |Deletes an existing record by id |
### Lessons learned

* Learning how data is moved around in a RESTful way
* Learning to utilize Spring Boot to get a web app up and running quickly
* Learning how to formulate method queries with Spring Data JPA
