# Brief 3 
# Bati Cuisine

A simple Java application for the management of construction and interior design projects, with a focus on calculating costs, managing resources, and generating client quotes.

## Table of Contents

- [Installation](#installation)
- [Structure](#Structure)
- [Features](#features)

## Installation

### Prerequisites

- Java 8 or higher
- PostgreSQL JDBC Driver
- A terminal or command prompt

### Setup environment variable

1. **For windows:**
   ```cmd
   set DB_URL= **Your postgreSQL URL**
   set DB_USER= **DB User**
   set DB_PASSWORD = **LEAVE EMPTY IF NO PASSWORD**

2. **For linux based:**
   ```bash
   export DB_URL= **Your postgreSQL URL**
   export DB_USER= **DB User**
   export DB_PASSWORD = **LEAVE EMPTY IF NO PASSWORD**

### Setup your database:

1. Ensure your PostgreSQL server is running.
2. Navigate to the directory containing `Database.sql`.
3. Run the following command to create the database and tables:
   ```bash
   psql -U your_username -f Database.sql
   

### Steps

1. **Clone the repository:**

   ```sh
   git clone https://github.com/Yorften/Brief-1.git
   cd Brief-1

4. **Run the application:**
   ```sh
   ./run.sh

## Structure

- **Model Layer**: Defines entities such as `Client`, `Project`, and `Component`, including specific component types like `Material` and `WorkForce`. This layer encapsulates the data and the business rules governing the data, representing the application's core business domain.
- **Controller Layer**: Manages user interactions and the flow of the application. It handles input from the presentation layer, invokes appropriate service methods to process the requests, and determines the next steps based on user choices.
- **Repository Layer**: Provides an abstraction for data access and management. This layer contains interfaces and their implementations for interacting with the database.
- **Service Layer**: Acts as a mediator between the controller and repository layers. This layer contains business logic and orchestrates operations involving multiple repositories.
- **Presentation Layer**: Represents the user interface and the interaction point for users. It includes views and menus that display information to the user and gather input.

## Features

1. Project Creation: Create projects with detailed information about clients and resources.
2. Total Cost Calculation: Provide accurate cost estimates, including freight, VAT and other charges.
3. Project management: Management of projects awaiting validation and projects in progress.