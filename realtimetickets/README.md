
# Real-time Ticketing System

## Introduction
This document provides an overview of the Real-time Ticketing System, including setup, usage, and basic functionality. The Ticketing System mimics the process of vendors issuing tickets and buyers purchasing them in real-time. The backend is built using Spring Boot, and it interfaces with an Angular frontend to provide an interactive user experience. The frontend communicates with the backend to display ticket statuses and manage ticketing operations, while the backend exposes REST APIs for ticket-related functionalities.

This project demonstrates synchronization, multithreading, and REST API integration for ticket administration.

## Prerequisites
Before running the system, ensure the following dependencies are installed:

- **Java 22**: To run the backend application.
- **Node.js 14+**: Required for the Angular frontend (not necessary for backend-only operations).
- **Spring Boot**: Managed by Maven during the build process.

## Building and Running the Backend (Java Spring Boot)

### Build the Backend
To install the necessary dependencies and build the backend, use Maven:

```bash
mvn clean install
```

### Run the Backend
Once the build completes, run the Spring Boot application:

```bash
mvn spring-boot:run
```

The backend will start on the default port: `8080`.

## Building and Running the Frontend (Angular)

### Install Dependencies
Navigate to the frontend project directory and run npm to install the required dependencies:

```bash
npm install
```

### Run the Frontend
Start the development server:

```bash
npm start
```

The frontend will run on the default port: `3000`, and it will communicate with the backend running on port `8080`.

## Frontend Features

### Ticket Management Dashboard:
- View a list of all tickets.
- Add, update, or delete tickets using interactive forms.

## Command-Line Interface

When running the backend, you can control the ticketing system directly from the terminal with the following commands:

- **Start**: Start the ticketing system by providing parameters like ticket release rate, customer retrieval rate, and max capacity.
- **Stop**: Stop the ticketing threads and halt the ticketing system.
- **Exit**: Shut down the application.

## Usage of the System

### 1. Start the Backend
Ensure the backend is running on `localhost:8080`.

### 2. Run the Frontend
Open the frontend in your browser via `localhost:3000`.

### 3. Ticket Management
Use the UI to add, view, update, or delete tickets.

### 4. Start the Simulation
Click the "Start" button on the frontend to begin the ticketing simulation. Set parameters such as:
- Ticket release rate
- Customer retrieval rate
- Maximum capacity

Watch as tickets are issued and purchased in real-time.

### 5. Stop the Simulation
Click the "Stop" button to halt the ticketing process.

## System Details

The system employs multithreading to replicate simultaneous interactions between vendors and customers with the ticket pool. The backend uses **Spring Boot**, **JPA**, and **Hibernate** for database management and request processing. The frontend leverages **React** (or another JavaScript framework, based on configuration) and communicates with the backend via REST API requests.

This project showcases the combination of a frontend and backend system for live operations, using a simulated ticket management system.

---

Feel free to contact the project maintainers for any issues or contributions.
