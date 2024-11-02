# ToDo Application

This is a ToDo application built using Spring Boot, Spring Data JPA, MySQL, and Spring Security with JWT authentication and authorization.

### This Applications test api calls are given below for localhost url. If you setup the application locally and run it following the steps given below, you can keep the domain as localhost.
### This application is also hosted in a EC2 instance. If you test the apis with that instance, replace the localhost domain with this ip address:
### The Api descriptions are given at the bottom of this readme file. Also you can test apis with Swagger documentation: http://domain:8080/swagger-ui.html - (Username - anupa, Password - password)
```dtd
    52.91.39.165
```

## Features

- User registration and login with JWT-based authentication.
- CRUD operations for managing ToDo items.
- Authentication and Authorization with Access Controlling Enabled.
- Secure password encoding with BCrypt.
- RESTful API for ToDo operations.
- Database integration with MySQL.

## Technologies Used

- **Spring Boot** - Application framework.
- **Spring Security** - Authentication and authorization.
- **JWT (JSON Web Tokens)** - For stateless authentication.
- **Spring Data JPA** - Data access layer.
- **MySQL** - Database for persistent storage.
- **BCrypt** - For password encoding.
- **JUnit & Mockito** - For unit testing.

## Getting Started

### 1. Prerequisites

- Java 17 or later
- Maven 3.6+
- MySQL
- Postman or a similar tool to test the API

### 2. Install Git
#### Windows:
- Download Git from [https://git-scm.com/](https://git-scm.com/downloads).
- Follow the installation instructions and keep the default settings.
- After installation, open **Git Bash** (search for it in the Start Menu).

#### macOS:
- Open **Terminal** and run:
 ```dtd
    bash
    brew install git
```

#### Ubuntu/Debian:
- Open **Terminal** and run:
 ```dtd
    sudo apt update
    sudo apt install git
```

#### Fedora:
- Open **Terminal** and run:
 ```dtd
    sudo dnf install git
```

### 3. Configure Git
- After installing Git, configure your user information. Open the terminal (Git Bash on Windows, Terminal on macOS/Linux), and run:
```dtd
    git config --global user.name "Your Name"
    git config --global user.email "your.email@example.com"
```

### 4. Clone the Repository
- To get a copy of the project on your machine, use Git to clone the repository.
```dtd
    git clone https://github.com/AnupaHemachandra/IruSri-ToDoApp.git
```

### 5. Install Java
- If you don't have Java installed, follow these steps to install it.
#### Windows:
- Download the Java JDK from [Oracle](https://www.oracle.com/java/technologies/downloads/)
- Run the installer and follow the instructions.

#### macOS:
- Open the terminal and run:
```dtd
    brew install openjdk@21
```


#### Ubuntu/Debian:
```dtd
    sudo apt update
    sudo apt install openjdk-21-jdk
```

### 6. Install Maven

#### Windows:
- Download Maven from the [Apache Maven website](https://dlcdn.apache.org/maven/maven-3/3.8.8/binaries/apache-maven-3.8.8-bin.zip).
- Extract the zip file to a directory (e.g., C:\Program Files\Apache\maven).
- Add the bin directory of the extracted folder to the PATH environment variable.

- Search for "Environment Variables" in Windows.
- Under "System Properties," click on "Environment Variables."
- In the "System variables" section, find the Path variable and edit it.
- Click "New" and add the path to the bin directory (e.g., C:\Program Files\Apache\maven\apache-maven-3.x.x\bin).

Open a new Command Prompt and verify the installation by running:
```dtd
    mvn -v
```

### 7. Setup the application.
Once you have Git and Java installed, navigate to the project folder:
```dtd
    cd IruSri-ToDoApp
```
Then build the project using Maven. This will download all dependencies and build the application.
```dtd
    mvn clean install
```

### 8. Setup MySQL

- Download mysql db server and install in your computer.
- Create a database for the application:

```sql
    CREATE DATABASE irusri-todo;
```

### 9. Open the project’s application.properties file located in src/main/resources/ and update it with your MySQL credentials:

```dtd
    spring.datasource.url=jdbc:mysql://localhost:3306/todo_db
    spring.datasource.username=<your_mysql_username>
    spring.datasource.password=<your_mysql_password>
```

### 10. Run the application.
```dtd
    mvn spring-boot:run
```

### 11. Testing the APIs with Post Man.

#### Authentication Apis.

Register User.
```bash
curl -X 'POST' \
  'http://localhost:8080/v1/auth/register' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "string",
  "password": "string"
}'
```

Login and Authenticate.
```bash
curl -X 'POST' \
  'http://localhost:8080/v1/auth/login' \
  -H 'accept: application/json' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "string",
  "password": "string"
}'
```

Update Passsword.
```bash
curl -X 'PATCH' \
  'http://localhost:8080/v1/auth/update-password' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "username": "string",
  "password": "string",
  "newPassword": "string",
  "confirmNewPassword": "string"
}'
```

Delete User. (Need to pass Bearer JWT token.)
```bash
curl -X 'DELETE' \
  'http://localhost:8080/v1/auth' \
  -H 'accept: */*'
```

#### ToDo items management Apis.

Get item by id. (Need to pass Bearer JWT token.)
```bash
curl -X 'GET' \
  'http://localhost:8080/v1/todo/{itemid}' \
  -H 'accept: */*'
```

Get all items for the user with sorting and sorting order. (Need to pass Bearer JWT token.)
```bash
curl -X 'GET' \
  'http://localhost:8080/v1/todo/all?sortingField=<fieldName>&descending=<true/false>' \
  -H 'accept: */*'
```

Get all items for the user with paging and sorting with sorting order. (Need to pass Bearer JWT token.)
```bash
curl -X 'GET' \
  'http://localhost:8080/v1/todo/page?offset=0&pageSize=10&sortingField=title&descending=false' \
  -H 'accept: */*'
```

Search all items for the user with keyword. (Need to pass Bearer JWT token.)
```bash
curl -X 'GET' \
  'http://localhost:8080/v1/todo/search?keyword=searchkeyword1' \
  -H 'accept: */*'
```

Search all items for the user by keyword with paging and sorting with sorting order. (Need to pass Bearer JWT token.)
```bash
curl -X 'GET' \
  'http://localhost:8080/v1/todo/searchpages?keyword=keyword1&offSet=0&pagesize=10&sortby=title&descending=false' \
  -H 'accept: */*'
```

Create a new ToDo item. (Need to pass Bearer JWT token.)
```bash
curl -X 'POST' \
  'http://localhost:8080/v1/todo' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "category": "string",
  "completionDate": "2024-11-02T09:50:20.073Z",
  "description": "string",
  "dueDate": "2024-11-02T09:50:20.073Z",
  "priority": "string",
  "reminder": "2024-11-02T09:50:20.073Z",
  "status": "string",
  "tags": "string",
  "timezone": "string",
  "title": "string",
  "sortingField": "string",
  "descending": true
}'
```

Update new ToDo item. (Need to pass Bearer JWT token.)
```bash
curl -X 'PUT' \
  'http://localhost:8080/v1/todo?id=1' \
  -H 'accept: */*' \
  -H 'Content-Type: application/json' \
  -d '{
  "id": 0,
  "category": "string",
  "completionDate": "2024-11-02T09:51:16.698Z",
  "description": "string",
  "dueDate": "2024-11-02T09:51:16.698Z",
  "priority": "string",
  "reminder": "2024-11-02T09:51:16.698Z",
  "status": "string",
  "tags": "string",
  "timezone": "string",
  "title": "string",
  "sortingField": "string",
  "descending": true
}'
```

Update ToDo item completion status. (Need to pass Bearer JWT token.)
```bash
curl -X 'PATCH' \
  'http://localhost:8080/v1/todo/completion?id=1&status=Completed' \
  -H 'accept: */*'
```

Delete ToDo item by id. (Need to pass Bearer JWT token.)
```bash
curl -X 'DELETE' \
  'http://localhost:8080/v1/todo?id=1' \
  -H 'accept: */*'
```

Delete all ToDo items for user. (Need to pass Bearer JWT token.)
```bash
curl -X 'DELETE' \
  'http://localhost:8080/v1/todo/all' \
  -H 'accept: */*'
```

### 12. OpenAPI Documentation.
This application uses Swagger/OpenAPI for API documentation. To view the API documentation in your browser:

- Start the Spring Boot application.
- Go to the following URL: http://localhost:8080/swagger-ui.html

You will see all the available API endpoints and be able to test them directly from the Swagger UI.
