import { Component, OnInit, inject, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { IncidentService } from './incident.service';
import { Incident } from './incident.interface';

@Component({
  selector: 'app-root',
  imports: [RouterOutlet],
  templateUrl: './app.html',
  styleUrl: './app.css'
})
export class App implements OnInit {
  protected readonly title = signal('Ticket dispatch system');

  private incidentService = inject(IncidentService);

  protected incidents = signal<Incident[]>([]);

  ngOnInit() {
    this.loadTickets();
  }

  loadTickets() {
    this.incidentService.getIncidents().subscribe({
      next: (data) => this.incidents.set(data),
      error: (err) => console.error("Error loading tickets", err)
    });
  }

  createNewTicket(titleText: string, descText: string, priorityLevel: string) {
    // Validation 1: Prevent blank tickets
    if (!titleText.trim() || !descText.trim()) {
      alert("Hold on! Title and Description cannot be blank.");
      return;
    }

    const isDuplicate = this.incidents().some(
      ticket => ticket.title.toLowerCase() === titleText.trim().toLowerCase()
    );

    if (isDuplicate) {
      alert("A ticket with this exact title already exists!");
      return;
    }

    const newIncident: Incident = {
      title: titleText.trim(),
      description: descText.trim(),
      priority: priorityLevel
    };

    this.incidentService.createIncident(newIncident).subscribe({
      next: () => {
        console.log("Ticket created successfully!");
        this.loadTickets(); // Refresh the list
      },
      error: (err) => console.error("Failed to create ticket", err)
    });
  }

  takeTicket(incidentId: number | undefined, solverIdText: string) {
    if (!incidentId) return;

    const solverId = Number(solverIdText);
    if (!solverId) {
      alert("Please enter a valid Dispatcher ID!");
      return;
    }

    this.incidentService.lockIncident(incidentId, solverId).subscribe({
      next: (updatedTicket) => {
        console.log("Ticket taken!", updatedTicket);
        this.loadTickets(); // Refresh the list to show it's IN_PROGRESS
      },
      error: (err) => {
        console.error("Failed to take ticket", err);
        alert("Could not take ticket. Check console for details.");
      }
    });
  }

  solveTicket(incidentId: number | undefined, solverIdText: string) {
    if (!incidentId) return;

    const solverId = Number(solverIdText);
    if (!solverId) {
      alert("Please enter a valid Dispatcher ID!");
      return;
    }

    this.incidentService.solveIncident(incidentId, solverId).subscribe({
      next: () => {
        console.log("Ticket solved!");
        this.loadTickets(); // Refresh the list to show it's SOLVED
      },
      error: (err) => {
        console.error("Failed to solve ticket", err);
        alert("Cannot solve this ticket. Make sure you are the dispatcher who took it!");
      }
    });
  }
}
