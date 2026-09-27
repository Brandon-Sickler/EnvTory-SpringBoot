package com.envtory.backend_api.model;

import java.util.ArrayList;
import java.util.UUID;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name = "lumber_bundles")
public class LumberBundle {
    
    //Defines the state a bundle can exist
    public enum MoistureState { GREEN, AIR_DRIED, KILN_DRIED, SURFACED}

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    
    private String species;
    private double length;
    private double width;
    private String grade;

    @Enumerated(EnumType.STRING)
    private MoistureState moistureState;

    //set this variable to -1.0 to indicate that no manual volume has been entered yet.
    private double manualBlockFootage = -1.0;

    @OneToMany(mappedBy = "bundle", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Lumber> stackedBoards = new ArrayList<>();

    //Empty constructor for database retrieval
    public LumberBundle() {

    }
    

    //Constructors
    public LumberBundle(String species, double length, double width, String grade, MoistureState startingState) {
        this.species = species;
        this.length = length;
        this.width = width;
        this.grade = grade;
        this.moistureState = startingState;
        this.stackedBoards = new ArrayList<>();
    }

    //Business Logic

    //adds an individual board to the pack's ArrayList
    public void addBoard(Lumber newBoard) {
        this.stackedBoards.add(newBoard);
    }

    //replaces an incorrect board at a specific index
    public void replaceBoard(int index, Lumber correctBoard) {
        //enforce safety by checking if the index is valid
        if (index >= 0 && index < stackedBoards.size()) {
            this.stackedBoards.set(index, correctBoard);
        }
    }

    //allows a grader to input a hand tally or block tally override
    public void setManualBlockFootage(double footage) {
        this.manualBlockFootage = footage;
    }

    //checks for a block tally override before looping the array
    public double getTotalBoardFootage() {

        if (this.manualBlockFootage != -1.0) {
            return this.manualBlockFootage;
        }
        double totalFootage = 0.0;
        for (Lumber board : stackedBoards) {
            totalFootage += board.calculateBoardFootage();
        }
        return totalFootage;
    }

    //Getters for the bundles properties
    public String getSpecies() {
        return species;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public UUID getId() {
        return id;
    }

    public String getGrade() {
        return grade;
    }

    public List<Lumber> getStackedBoards() {
        return stackedBoards;
    }

    public MoistureState getMoistureState() {
        return moistureState;
    }


    public double getManualBlockFootage() {
        return manualBlockFootage;
    }


    //setters for the bundle's properties
    public void setSpecies(String species) {
        this.species = species;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public void setMoistureState(MoistureState state) {
    this.moistureState = state;
   }

}