package com.efc.pdm.LiftApp.models;


import net.bytebuddy.description.modifier.Ownership;

import javax.persistence.*;

@Entity
@Table(name = "ejercicios")
public class Exercise {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150, name = "nombre")
    private String name;
    @Column(nullable = false, length = 20, name = "musculo")
    private String muscle;
    @Column(nullable = false, length = 20, name = "dificultad")
    private String difficulty;
    @Column(nullable = false, length = 50, name = "tipo")
    private String type;
    @Column(nullable = false, length = 250, name = "descripcion")
    private String description;
    @Column(nullable = false, name = "sets")
    private Integer sets;
    @Column(nullable = false, name = "reps")
    private Integer reps;
    @Column(nullable = false ,name = "verificado")
    private boolean verified;

    @Column(nullable = false ,name = "ownership")
    private Integer ownership;

    @ManyToOne
    private User user;


    public Exercise(String name, String muscle, String difficulty, String type, String description, Integer sets, Integer reps, boolean verified, User user, Integer Ownership){
        this.name = name;
        this.muscle = muscle;
        this.difficulty = difficulty;
        this.type = type;
        this.description = description;
        this.sets = sets;
        this.reps = reps;
        this.verified = verified;
        this.user = user;
        this.ownership = Ownership;
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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isVerified() {
        return verified;
    }

    public void setVerified(boolean verified) {
        this.verified = verified;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getOwnership() {
        return ownership;
    }

    public void setOwnership(Integer ownership) {
        this.ownership = ownership;
    }
}
