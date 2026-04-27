import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Incident } from './incident.interface';

@Injectable({
  providedIn: 'root' // this make the service available everywhere in app
})
export class IncidentService {
  private http = inject(HttpClient);

  private apiUrl = 'http://localhost:8080/api/incidents'; //SpringBoot address

  getIncidents(): Observable<Incident[]>{
    return this.http.get<Incident[]>(this.apiUrl);
  }
  createIncident(newTicket: Incident): Observable<Incident>{
    return this.http.post<Incident>(this.apiUrl, newTicket);
  }

  //Lock/Take a ticket
  lockIncident(incidentId: number, solverId: number): Observable<Incident> {
    const url = `${this.apiUrl}/${incidentId}/lock?solverId=${solverId}`;
    return this.http.put<Incident>(url, {});
  }

  //Solve a ticket
  solveIncident(incidentId: number, solverId: number): Observable<Incident> {
    const url = `${this.apiUrl}/${incidentId}/solve?solverId=${solverId}`;
    return this.http.put<Incident>(url, {});
  }
}
