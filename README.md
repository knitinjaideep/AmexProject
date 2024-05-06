# Amex - Project School Management System

A simple school management system to manage students, their classes, and other related information.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Code Structure](#code-structure)
    - [Common Package](#common-package)
    - [Student Controller Package](#student-controller-package)
    - [Student Model Package](#student-model-package)
    - [Student Repository Package](#student-repository-package)
    - [Student Service Package](#student-service-package)
- [Local Development Setup](#local-development-setup)
- [Tech Stack](#tech-stack)

## Introduction

This project is a school management system built using Java and Spring Boot. It provides functionalities to manage students, including adding, updating, deleting, and retrieving student records.

## Features

- Retrieve all students
- Retrieve student by ID
- Retrieve students by name
- Retrieve students by class name
- Add a new student
- Update an existing student
- Delete a student

## Code Structure

### Common Package

The `common` package contains utility classes and global constants used throughout the application.

### Student Controller Package

The `student.controller` package contains the controller classes responsible for handling HTTP requests related to students.

### Student Model Package

The `student.model` package contains the entity class `Student` and an enum `ClassNames` representing different classes.

### Student Repository Package

The `student.repository` package contains the repository interface `StudentRepository` for database operations related to students.

### Student Service Package

The `student.service` package contains the service class `StudentService` for business logic related to students.

## Local Development Setup

To set up the project locally, follow these steps:

1. Clone the repository:

    ```bash
    git clone https://github.com/knitinjaideep/AmexProject.git
    ```

2. Run 2 docker containers for running postgres locally:
    ```bash
   - Make sure docker desktop is installed locally
   #Create a network
   docker network create AmexDb
   #Runs the postgresql container
   docker run --name AmexDb -p 5432:5432 --network=AmexDb -v "$PWD:/var/lib/postgresql/data" -e POSTGRES_PASSWORD=password -d postgres:16.2
   #Runs the psql locally
    docker run -it --rm --network=AmexDb postgres:16.2 psql -h AmexDb -U postgres
    ```

3. Install dependencies:

    ```bash
    mvn install
    ```
4. Run the application:

    ```bash
    mvn spring-boot:run
    ```

5. Access the application at `http://localhost:8080`.

## Tech Stack

- Java
- Spring Boot
- Maven
- PostgreSQL
