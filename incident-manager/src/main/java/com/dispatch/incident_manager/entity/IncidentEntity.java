package com.dispatch.incident_manager.entity;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class IncidentEntity {
    @Id
    // Generating ids in database
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String status;
    private String priority;
    private Long solverId;

    public IncidentEntity(Long id, String title, String description, String status, String priority, Long solverId){
        this.id = id;
        this.title = title;
        this.description = description;
        this.status = status;
        this.priority = priority;
        this.solverId = solverId;
    }
    // Empty constructor for JPA
    public IncidentEntity(){

    }

    public Long getId() {return this.id;}
    public String getTitle() {return this.title;}
    public String getDescription() {return this.description;}
    public String getStatus() {return this.status;}
    public String getPriority() {return this.priority;}
    public Long getSolverId() {return this.solverId;}

    public void setId(Long id) {this.id = id;}
    public void setTitle(String title) {this.title = title;}
    public void setDescription(String description) {this.description = description;}
    public void setStatus(String status) {this.status = status;}
    public void setPriority(String priority) {this.priority = priority;}
    public void setSolverId(Long solverId){this.solverId = solverId;}

}