# scheduler

A Java/Springboot-based REST API with react for scheduling reports
  
  ## Requirements
  
  For building and running the application you need:
  
  - [JDK 1.8](http://www.oracle.com/technetwork/java/javase/downloads/jdk8-downloads-2133151.html)
  - [Maven 3](https://maven.apache.org)
  - [IntelliJ IDE](https://www.jetbrains.com/idea/download/#section=windows) or any other Java based IDE
  - Install Lombok in your IDE 
  - MySql
  - Node
  - React


## Getting Started

1. **Clone the application**

	```bash
	git clone https://github.com/victor-onu/scheduler.git
	cd scheduler
	```

2. **Create MySQL database**

	```bash
	create database expenseApp, although it will be created if it does not exist
	```


3. **Change MySQL username and password as per your MySQL installation**

	+ open `src/main/resources/application.yml` file.

	+ change `spring.datasource.username` and `spring.datasource.password` properties as per your mysql installation

4. **Run the app**

	There are several ways to run a Spring Boot application on your local machine. One way is to execute the `main` method in the `com.inits.expenseapp.expenseAppApplication` class from your IDE.
    
    Alternatively you can run the following command

	```bash
	mvn spring-boot:run
	```

	The server will start on port 8080.


## Testing the endpoints on Postman

# POST
**Saves a new recipient in the database - (Use [requests](#Requests) below)**

	http://localhost:8080/api/recipient

# POST
**Uploads a report in the database - (Use [requests](#Requests) below)**

	http://localhost:8080/api/report
  
# GET
**Downloads a report file already uploaded**

	http://localhost:8080/api/uploads/{file name}



# Requests

http://localhost:8080/api/recipient

	{
    "email": "victoronushaibu@gmail.com",
    "firstName": "Onu",
    "lastName": "Shaibu"
    }
  
  http://localhost:8080/api/report
  
	{
     "reportTitle": "Thppphf", 
     "reportDescription": "Description2", 
     "scheduleExpression": "* * * ? * *"
    }





## Built With

  - Java
  - Springboot
  - Javascript
  - React
  - MySql

