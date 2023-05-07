package com.efc.pdm.LiftApp.models;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ejercicios")
public class Exercise {

    @Id
    private Long id;
    @Column(nullable = false, unique = true, length = 150, name = "nombre_rutina")
    private String name;
    @Column(nullable = false, unique = true, length = 20, name = "musculo")
    private String muscle;
    @Column(nullable = false, unique = true, length = 20, name = "dificultad")
    private String difficulty;
    @Column(nullable = false, unique = true, length = 50, name = "tipo")
    private String type;
    @Column(nullable = false, unique = true, length = 250, name = "descripcion")
    private String description;
    @Column(nullable = false, name = "sets")
    private Integer sets;
    @Column(nullable = false, name = "reps")
    private Integer reps;


    public Exercise(String name, String muscle, String difficulty, String type, String description, Integer sets, Integer reps) {
        this.name = name;
        this.muscle = muscle;
        this.difficulty = difficulty;
        this.type = type;
        this.description = description;
        this.sets = sets;
        this.reps = reps;
    }

    public Exercise() {

    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMuscle() {
        return muscle;
    }

    public void setMuscle(String muscle) {
        this.muscle = muscle;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getSets() {
        return sets;
    }

    public void setSets(Integer sets) {
        this.sets = sets;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
