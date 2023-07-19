# Excel File Handling Application

The Excel File Handling Application is a tool designed for efficient data management from Excel files. It provides capabilities to read, save data to a database, and search for specific information within the Excel files.

## Features

- **Excel File Reading**: The application allows for reading data from Excel files, including worksheets, cells, and their values. You can easily import data into the application and process it further.

- **Database Storage**: The application offers the ability to save data from Excel files to a database. This enables you to store information from Excel files in a structured database, facilitating further data management.

- **Information Search**: The application enables searching for specific information within Excel files. You can perform advanced searches based on specified criteria and receive results that meet the given conditions.

## Project Structure

The Excel File Handling Application project consists of the following modules:

- `data-sync-app`: This module contains the main application and the entry point (main class) to run the application. 

- `data-sync-config`: This module contains the application configuration, including configuration files.

- `data-sync-excel`: This module contains code responsible for handling Excel files. 

- `data-sync-model`: This module contains data models used in the application to represent data.

- `data-sync-repository`: This module contains data repositories that handle communication with the database. It includes interfaces for repositories.

- `data-sync-service`: This module contains business services of the application, encapsulating the business logic. Here, data processing and handling take place.

## Getting Started

To run the project, navigate to the `data-sync-app` directory run the application using the command `mvn spring-boot:run`.

## Requirements

The project utilizes various dependencies, such as Spring Boot, Lombok, SnakeYAML, Apache POI, JUnit Jupiter, H2 Database, and Mockito. Make sure you have Java version 17 and Maven installed.

## Author

- Author: [Zybercik](https://github.com/Zybercik00)
