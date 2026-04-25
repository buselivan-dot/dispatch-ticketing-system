package com.dispatch.incident_manager.service;

import com.dispatch.incident_manager.entity.IncidentEntity;
import com.dispatch.incident_manager.repository.IncidentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class IncidentService {
    private final IncidentRepository repository;

    public IncidentService(IncidentRepository repository){
        this.repository = repository;
    }

    public List<IncidentEntity> getAllIncidents(){
        return repository.findAll();
    }

    public IncidentEntity createIncident(IncidentEntity incident){
        incident.setStatus("OPEN");
        return repository.save(incident);
    }

    public IncidentEntity lockIncident(Long incidentId, Long solverId){
        Optional<IncidentEntity> optionalIncident = repository.findById(incidentId);
        if(optionalIncident.isEmpty()){
            throw new RuntimeException("Incident not found in database");
        }

        IncidentEntity incident = optionalIncident.get();

        if(incident.getSolverId() != null){
            throw new RuntimeException("The ticket is already taken by someone else, please try another task!");
        }
        if(!incident.getStatus().equals("OPEN") ){
            throw new RuntimeException("The ticket is not open!");
        }

        incident.setStatus("IN_PROGRESS");
        incident.setSolverId(solverId);
        return repository.save(incident);
    }

    public IncidentEntity solvedIncident(Long incidentId, Long solverId){
        Optional<IncidentEntity> optionalIncident = repository.findById(incidentId);
        if(optionalIncident.isEmpty()){
            throw new RuntimeException("The incident does not exists");
        }
        IncidentEntity incident = optionalIncident.get();
        if(!incident.getStatus().equals("IN_PROGRESS")){
            throw new RuntimeException("You cannot solve the ticket that wasnt taken");
        }
        if(!incident.getSolverId().equals(solverId)){
            throw new RuntimeException("You cannot solve the ticket that was taken by someone else");
        }
        incident.setStatus("SOLVED");
        return repository.save(incident);
    }


}
