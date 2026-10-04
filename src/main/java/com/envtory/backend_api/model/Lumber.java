package com.envtory.backend_api.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.JoinColumn;


@Entity
@Table(name = "lumber_boards")
public class Lumber {


    //Variables
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String species;
    private double thickness;
    private String grade;

    //nullable dimensions
    private Double length;
    private Double width;
    private Integer surfaceMeasure;
    private Double boardFootage;

    @ManyToOne 
    @JoinColumn(name = "run_id", nullable = false)
    @JsonIgnore 
    private Run run;

    @ManyToOne
    @JoinColumn(name = "bundle_id", nullable = true)
    @JsonIgnore
    private LumberBundle bundle;


    //empty constructor for database retrieval
    public Lumber() {

    }

    
    //Constructors
    public Lumber(String species, double length, double width, double thickness, String grade) {
        this.species = species;
        this.length = length;
        this.width = width;
        this.thickness = thickness;
        this.grade = grade;
    }

    //Business Logic: Calculates total board footage
    public double calculateBoardFootage() {
        return (this.length * this.width * this.thickness) / 12.0;
    }


    //Getters and Setters

    //Formatted helper Getter for Thickness, Displays text like "4/4"
    public String getThicknessAsQuarters() {
        int quarters = (int) Math.round(this.thickness * 4.0);
        return quarters + "/4";
    }


    public UUID getId() {
        return id;
    }


    public void setId(UUID id) {
        this.id = id;
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


    public Double getLength() {
        return length;
    }


    public void setLength(Double length) {
        this.length = length;
    }


    public Double getWidth() {
        return width;
    }


    public void setWidth(Double width) {
        this.width = width;
    }


    public Integer getSurfaceMeasure() {
        return surfaceMeasure;
    }


    public void setSurfaceMeasure(Integer surfaceMeasure) {
        this.surfaceMeasure = surfaceMeasure;
    }


    public Double getBoardFootage() {
        return boardFootage;
    }


    public void setBoardFootage(Double boardFootage) {
        this.boardFootage = boardFootage;
    }


    public Run getRun() {
        return run;
    }


    public void setRun(Run run) {
        this.run = run;
    }


    public LumberBundle getBundle() {
        return bundle;
    }


    public void setBundle(LumberBundle bundle) {
        this.bundle = bundle;
    }

    

}
