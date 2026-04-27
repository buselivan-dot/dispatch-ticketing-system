# 🎫 DispatchPro

A lightweight full-stack ticket management system I built to connect a Java Spring Boot REST API with a modern Angular frontend. It simulates a simple dispatch center where issues can be logged, claimed by specific dispatchers, and eventually resolved.

## What it does

I wanted to build something that handles actual business logic and state changes, rather than just a basic database wrapper. 

* **The Queue:** Users can submit new incident tickets with a title, description, and priority level (Low/Medium/High). 
* **Claiming System:** Tickets start as 'Open'. A dispatcher can enter their unique ID number to "Take" a ticket, which shifts its status to 'In Progress' and locks it to them.
* **Ownership:** Once a dispatcher claims a ticket, only that specific dispatcher ID is allowed to mark it as 'Solved'.
* **Smart UI:** The frontend uses color-coded cards to show status at a glance. It also includes validations to stop duplicate or blank tickets before they even hit the backend.

## Tech Stack

* **Frontend:** Angular (using modern Standalone Components and `signal()` for reactive state management), plain HTML/CSS for UI.
* **Backend:** Java and Spring Boot (Spring Web, Spring Data JPA).
* **Database:** H2 In-Memory Database. I chose this because it's completely zero-setup. It wipes clean on every restart, making it perfect for quick testing without needing to spin up a Docker container or local SQL server.

---

## How to run it locally

### 1. Start the Spring Boot Backend
Open the backend project in your favorite Java IDE (IntelliJ, Eclipse, VS Code) and run the main application class. 
* It will start up on `http://localhost:8080`.
* *Note: The database is completely empty on startup.*

### 2. Start the Angular Frontend
Open your terminal, navigate to the frontend folder, and run:
> npm install
> ng serve

Once it compiles, open your web browser and go to `http://localhost:4200`.

---

## API Routes

If you want to bypass the frontend and test the backend directly using Postman or cURL, these are the available endpoints:

* `GET /api/incidents` - Grabs the full list of tickets.
* `POST /api/incidents` - Creates a new ticket (expects a JSON body with title, description, and priority).
* `PUT /api/incidents/{id}/lock?solverId={id}` - Claims a ticket for a dispatcher.
* `PUT /api/incidents/{id}/solve?solverId={id}` - Marks a claimed ticket as solved.
