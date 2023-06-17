# README

This gradle project was generated via [spring initializr](https://start.spring.io/) using Spring Boot version 2.4.3 and Java 11. To allow for Java version flexibility, we're using [gradle toolchains](https://docs.gradle.org/current/userguide/toolchains.html#sec:consuming). If you do not have JDK 11, simply update the toolchain block in `build.gradle` to the appropriate version:

```
java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(11)
    }
}
```

Run `./gradlew tasks` to see what targets you can run.

Default target to build the project is `./gradlew assemble`
Default target to launch the app is `./gradlew bootRun`



Project Documentation :
Project Name: Frontend Tree Data App

Description: This project uses Spring Boot to build a frontend app for rendering and storing tree data. 
Users can view and add nodes.

Key Features: Tree retrieval, node addition, data persistence, testing, and documentation.

Technologies: Spring Boot, RESTful API, Relational DB, JUnit, Spring Test.

In summary, this project develops a streamlined frontend app using Spring Boot to handle tree data, 
providing essential features like node manipulation, data storage, testing, and documentation.




Getting Started:

->Clone the repository and open it in your preferred IDE.
->Ensure you have the Java Development Kit (JDK) installed.
->Configure the application properties, including the database connection details.
->Build the application using Maven or Gradle.
->Run the application locally, and it will start on the port (e.g., http://localhost:3001).
->Test the application using a web browser or API testing tool.
->Refer to the documentation for API endpoints, database schema, and testing guidelines.



API Documentation: 
    GET /api/tree
        Endpoint to retrieve the entire tree structure.

Request:    GET /api/tree
Response:

[
    {
        "<id>": {
            "label": "<first item>",
                "children": [
                    {
                        "<id>": {
                            "label": "<another item>",
                            "children": [] // empty children
                            }
                            },
                            {
                        "<id>": {
                        "label": "<another item>",
                        "children": [ ...<any children>... ]
                            }
                    }
                ]
        }
    }
]


POST /api/tree
Endpoint to add a node to the tree structure.

Request:    POST /api/tree
Content-Type: application/json

{
"parent": "<id>",
"label": "<label>"
}
Response:

Copy code
HTTP/1.1 200 OK


Database Schema: 
Relational Database Schema (MySQL):

CREATE TABLE tree (
id INT PRIMARY KEY AUTO_INCREMENT,
label VARCHAR(255) NOT NULL,
parent_id INT,
FOREIGN KEY (parent_id) REFERENCES tree(id)
);
