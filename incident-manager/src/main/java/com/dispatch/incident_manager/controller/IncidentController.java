package com.dispatch.incident_manager.controller;

import com.dispatch.incident_manager.entity.IncidentEntity;
import org.springframework.web.bind.annotation.*;
import com.dispatch.incident_manager.service.IncidentService;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200/")
@RestController
@RequestMapping("/api/incidents")
public class IncidentController {
    private IncidentService service;

    public IncidentController(IncidentService service){
        this.service = service;
    }

    //GET
    @GetMapping
    public List<IncidentEntity> getAll(){
        return service.getAllIncidents();
    }
    //POST (@RequestBody tells the spring to look inside the JSON data from HTTP request)
    @PostMapping
    public IncidentEntity createIncident(@RequestBody IncidentEntity incident){
        return service.createIncident(incident);
    }
    //PUT
    @PutMapping("/{incidentId}/lock")
    public IncidentEntity lockIncident(@PathVariable Long incidentId,@RequestParam Long solverId) {
        return service.lockIncident(incidentId, solverId);
    }
    @PutMapping("/{incidentId}/solve")
    public IncidentEntity solveIncident(@PathVariable Long incidentId, @RequestParam Long solverId){
        return service.solvedIncident(incidentId, solverId);
    }




}
