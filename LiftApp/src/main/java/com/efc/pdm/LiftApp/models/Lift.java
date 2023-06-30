package com.efc.pdm.LiftApp.models;

import javax.persistence.*;

@Entity
@Table(name = "lift")
public class Lift {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = true, length = 150, name = "nombre")
    private String exercisename;

    @Column(nullable = false, name = "weight")
    private Integer weight;

    @Column(nullable = false, name = "reps")
    private Integer reps;

    @Column(nullable = false, name = "liftpoints")
    private double liftpoints;

    @Column(nullable = false, name = "highlight")
    private Boolean highlight;



    @ManyToOne
    private User user;

    public Lift() {
    }

    public Lift(String exercisename, Integer weight, Integer reps, double liftpoints, Boolean highlight, User user) {
        this.exercisename = exercisename;
        this.weight = weight;
        this.reps = reps;
        this.liftpoints = liftpoints;
        this.highlight = highlight;
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getExercisename() {
        return exercisename;
    }

    public void setExercisename(String exercisename) {
        this.exercisename = exercisename;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Integer getReps() {
        return reps;
    }

    public void setReps(Integer reps) {
        this.reps = reps;
    }

    public Boolean getHighlight() {
        return highlight;
    }

    public void setHighlight(Boolean highlight) {
        this.highlight = highlight;
    }

    public double getLiftpoints() {
        return liftpoints;
    }

    public void setLiftpoints(double liftpoints) {
        this.liftpoints = liftpoints;
    }
}
