package com.envtory.backend_api.model;

import java.util.UUID;
import java.util.List;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ElementCollection;


@Entity
@Table(name = "lumber_bundles")
public class LumberBundle {
    
    //Defines the state a bundle can exist
    public enum MoistureState { GREEN, AIR_DRIED, KILN_DRIED, SURFACED}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    @Enumerated(EnumType.STRING)
    private MoistureState moistureState;

    @Column(nullable = true)
    private String packNumber;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private double thickness;

    @Column(nullable = false)
    private String grade;

    @ElementCollection
    private List<Double> lengthsIncluded;

    @Column(nullable = false)
    private double totalBoardFootage;

    @ManyToOne 
    @JoinColumn(name = "run_id", nullable = true)
    private Run run;

    @Column(nullable = true)
    private UUID loadId;

    @OneToMany(mappedBy = "bundle")
    private List<Lumber> boards;

    //Empty constructor for database retrieval
    public LumberBundle() {

    }
    

    //Constructor
    public LumberBundle(String species, double thickness, String grade, MoistureState startingState) {
        this.species = species;
        this.thickness = thickness;
        this.grade = grade;
        this.moistureState = startingState;
        this.totalBoardFootage = 0.0;
        
    }


    //Getters and Setters

    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
    }


    public MoistureState getMoistureState() {
        return moistureState;
    }


    public void setMoistureState(MoistureState moistureState) {
        this.moistureState = moistureState;
    }


    public String getPackNumber() {
        return packNumber;
    }


    public void setPackNumber(String packNumber) {
        this.packNumber = packNumber;
    }


    public String getSpecies() {
        return species;
    }


    public void setSpecies(String species) {
        this.species = species;
    }


    public double getThickness() {
        return thickness;
    }


    public void setThickness(double thickness) {
        this.thickness = thickness;
    }


    public String getGrade() {
        return grade;
    }


    public void setGrade(String grade) {
        this.grade = grade;
    }


    public List<Double> getLengthsIncluded() {
        return lengthsIncluded;
    }


    public void setLengthsIncluded(List<Double> lengthsIncluded) {
        this.lengthsIncluded = lengthsIncluded;
    }


    public double getTotalBoardFootage() {
        return totalBoardFootage;
    }


    public void setTotalBoardFootage(double totalBoardFootage) {
        this.totalBoardFootage = totalBoardFootage;
    }


    public Run getRun() {
        return run;
    }


    public void setRun(Run run) {
        this.run = run;
    }


    public UUID getLoadId() {
        return loadId;
    }


    public void setLoadId(UUID loadId) {
        this.loadId = loadId;
    }


    public List<Lumber> getBoards() {
        return boards;
    }


    public void setBoards(List<Lumber> boards) {
        this.boards = boards;
    }


}