package com.envtory.backend_api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "runs")

public class Run {
    
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;    //the ID of the user doing the grading

    @Column(nullable = false)
    private String status;  //example: "Active", "Paused", "Closed"

    @Column(nullable = false)
    private LocalDateTime startTime;

    @Column(nullable = true)
    private LocalDateTime endTime;

    @ElementCollection
    private List<String> speciesAllowed;

    @ElementCollection
    private List<String> gradesAllowed;

    @ElementCollection 
    private List<Double> thicknessesAllowed;


    //Getters and setters
    
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public LocalDateTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public List<String> getSpeciesAllowed() {
        return speciesAllowed;
    }

    public void setSpeciesAllowed(List<String> speciesAllowed) {
        this.speciesAllowed = speciesAllowed;
    }

    public List<String> getGradesAllowed() {
        return gradesAllowed;
    }

    public void setGradesAllowed(List<String> gradesAllowed) {
        this.gradesAllowed = gradesAllowed;
    }

    public List<Double> getThicknessesAllowed() {
        return thicknessesAllowed;
    }

    public void setThicknessesAllowed(List<Double> thicknessesAllowed) {
        this.thicknessesAllowed = thicknessesAllowed;
    }

    

}
