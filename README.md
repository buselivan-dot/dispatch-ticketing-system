# DispatchPro

A simple ticket management system built with Spring Boot and Angular. It lets dispatchers create tickets, claim them, and mark them as solved.

## Features
- Create tickets with title, description, and priority
- Basic validation to prevent empty or duplicate tickets
- Dashboard with color-coded ticket statuses (Open, In Progress, Solved)
- Claim tickets using a dispatcher ID
- Only the dispatcher who claimed a ticket can solve it

## Stack
- Frontend: Angular (Signals, Standalone components)
- Backend: Java, Spring Boot
- Database: H2 In-Memory

## How to run

Backend:
1. Open the Spring Boot project in your IDE and run the main application class.
2. The server will start on http://localhost:8080.
Note: Since it uses H2, the database resets completely every time you restart the server.

Frontend:
1. Open a terminal and navigate to the frontend folder.
2. Run: npm install
3. Run: ng serve
4. Open http://localhost:4200 in your browser.

## API Endpoints
- GET /api/incidents : Get all tickets
- POST /api/incidents : Create a new ticket
- PUT /api/incidents/{id}/lock?solverId={id} : Claim a ticket
- PUT /api/incidents/{id}/solve?solverId={id} : Mark a ticket as solved
