# NagarroAssignment
Steps to Run:
1. On downloading, import the project to IDE.
2. Build and run com.nagarro.assignment.AssignmentApplication.java
3. In Postman, run below urls:


For user with name 'admin':

i) Login
POST : http://localhost:8080/authenticate
In Request Body :
{
  "username": "admin",
  "password": "admin"
}
In Response you will get a JWT token

ii) Get last 3 months statement
In Authorization, select Type as Bearer and provide the JWT token obtained from Login request
GET : http://localhost:8080/statements/1

iii) Post req to get statements within provided date and amount range
In Authorization, select Type as Bearer and provide the JWT token obtained from Login request
In Request Body :
{
  "accountId": "1",
  "fromDate": "2013-01-28",
  "toDate": "2023-01-28",
  "fromAmount": "10",
  "toAmount": "11111110"
}
POST : http://localhost:8080/statements/1 or
POST : http://localhost:8080/statements

iv) Post req to get statements within provided date range
In Authorization, select Type as Bearer and provide the JWT token obtained from Login request
In Request Body :
{
  "accountId": "1",
  "fromDate": "2013-01-28",
  "toDate": "2023-01-28"
}
POST : http://localhost:8080/statements/1 or
POST : http://localhost:8080/statements

iv) Post req to get statements within provided amount range
In Authorization, select Type as Bearer and provide the JWT token obtained from Login request
In Request Body :
{
  "accountId": "1",
  "fromAmount": "10",
  "toAmount": "11111110"
}
POST : http://localhost:8080/statements/1 or
POST : http://localhost:8080/statements

v)  Post req to get last 3 months statement
In Authorization, select Type as Bearer and provide the JWT token obtained from Login request
In Request Body :
{
  "accountId": "1"
}
POST : http://localhost:8080/statements/1 or
POST : http://localhost:8080/statements

For User name with 'user':
i) Login
POST : http://localhost:8080/authenticate
In Request Body :
{
  "username": "user",
  "password": "user"
}
In Response you will get a JWT token

ii) Get last 3 months statement
In Authorization, select Type as Bearer and provide the JWT token obtained from Login request
GET : http://localhost:8080/statements/1


4.To run unit test cases:
In test folder, Run com.nagarro.assignment.controllers.StatementControllersTest.java file
