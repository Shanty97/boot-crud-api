# boot-crud-api

## ➡️ Spring Boot CRUD API with In-Memory Map Database

**Overview:**

This project implements a Spring Boot application that provides a RESTful API for CRUD operations (Create, Read, Update, Delete) on data stored in an in-memory Map data structure. It demonstrates essential API development concepts and data management using Spring Boot.

**Key Features:**

* RESTful API endpoints for CRUD operations (`/api/v1/...` for all resources).
* In-memory data storage using a Java `Map` for simplicity.
* Basic unit testing.
* Spring Boot autoconfiguration for easy setup and deployment.
* Clear project structure and comments for readability.

**API Endpoints:**

| Method | URL                           | Description                                 | Request Body    | 
|--------|-------------------------------|---------------------------------------------|------------------
| GET    | `/api/v1/students`            | Get all students.                           | N/A             |
| GET    | `/api/v1/student/id/{id}`     | Get a specific student by ID.               | N/A             |
| GET    | `/api/v1/student/age/{age}`   | Get specific student/students by age        | N/A             |
| POST   | `/api/v1/student/add`         | Create a new student using name and age     | Student         |
| POST   | `/api/v1/student/addAll`      | Create new students using name and age      | `List<Student>` |
| PUT    | `/api/v1/student/update?id=`  | Update a student using id                   | Student         |
| DELETE | `/api/v1/student/remove?id=`  | Delete a student using id                   | N/A             |

**Data Structure:**

The data is stored in a `Map<String, Student>` instance, where the key is a unique identifier (Student id) and the value is an object representing the student. 

**Additional Notes:**

* This project is designed for demonstration purposes and may not be suitable for production use without further enhancements.
