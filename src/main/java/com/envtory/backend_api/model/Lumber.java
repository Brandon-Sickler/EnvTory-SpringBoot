package com.envtory.backend_api.model;

import java.util.UUID;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "lumber_boards")
public class Lumber {

    //Id and generated value for database
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)

    //Variables
    private String species;
    private double length;
    private double width;
    private double thickness;
    private String grade;
    private UUID id;


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
    public String getSpecies() {
        return species;
    }

    public double getLength() {
        return length;
    }

    public double getWidth() {
        return width;
    }

    public double getThickness() {
        return thickness;
    }

    //Formatted helper Getter for Thickness, Displays text like "4/4"
    public String getThicknessAsQuarters() {
        int quarters = (int) Math.round(this.thickness * 4.0);
        return quarters + "/4";
    }

    public String getGrade() {
        return grade;
    }

    public UUID getId() {
        return id;
    }


    public void setSpecies(String species) {
        this.species = species;
    }


    public void setLength(double length) {
        this.length = length;
    }


    public void setWidth(double width) {
        this.width = width;
    }


    public void setThickness(double thickness) {
        this.thickness = thickness;
    }


    //Added @JsonIgnore to prevent this setter from being used when JSON is translated into a Java object
    @JsonIgnore
    //Overloaded setter for Thickness, takes an interger and converts it to a decimal
    public void setThickness(int quarterCount) {
        this.thickness = quarterCount / 4.0; 
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }
    

}
