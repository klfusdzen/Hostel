# Hostel
Hostel is an application with REST API architecture for hostel systems. The main functions of the application are:
- registration and booking online
- view bookings quickly and easily
- easy access to booking manipulations
- selection of options for room
- establishing the level of loyalty
# Technologies
- java 17 version;
- Spring (modules: Spring boot, Spring Data, Spring Security);
- lombok;
- PostgreSQL;
- Swagger;
- Maven;
- JUnit 5;
- javadoc.
# Installation
1. Install Java Development Kit (JDK) on your system.
2. Install and configure PostgreSQL on your system.
3. Clone this repository to your local machine.
4. Open the project in your favorite integrated development environment (IDE).
5. Configure the PostgreSQL database connection settings in the application.properties file.
6.Run the application.
# Registration
- go to "http://localhost:8080/security/registration" via swagger or postman
- user have to pass json format (POST method: {"firstName" : "testFirstName", "lastName" : "testLastName", "email": "testMail@gmail.com", "password": "testPassword"});
- the user is saved to the database with ROLE_USER;
- the first name can be changed by going to "http://localhost:8080/user/first-name/{id}" and change first name via json. ( Example: (PUT method: "firstName": "newFirstName" ));
- the last name can be changed by going to "http://localhost:8080/user/last-name/{id}" and change last name via json. ( Example: (PUT method: "lastName": "newLastName" ));
- the email can be changed by going to "http://localhost:8080/user/email/{id}" and change email via json. ( Example: (PUT method: "email": "newTestMail" ));
- the password can be changed by going to "http://localhost:8080/user/password/{id}" and change password via json. ( Example: (PUT method: "password": "newPassword1234" )).
# Login
- to log n to the application you need to follow the link "http://localhost:8080/security" and pass json format (POST method: {"email": "testMail@gmail.com", "password": "testPassword1-9"});
- use the generated token for further operations.
# User capabilities
- register
- login
- change own email
- change own password
- create bookings
- do transactions
- chose options
- delete own bookings
- get own data from database
# Moderator capabilities
- register
- login
- change email
- change password
- create bookings
- do transactions
- chose options
- delete own bookings
- get all clients data from database
- get booking by id from database
- change room
- delete room from database
- change user
- # Admin capabilities
- - register
- login
- change email
- change password
- create bookings
- do transactions
- chose options
- delete own bookings
- get all clients data from database
- get booking by id from database
- change room
- delete room from database
- change user
- delete user from database
- change payment
# Thank you for your attention
