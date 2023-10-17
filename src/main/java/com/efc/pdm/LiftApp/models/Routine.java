package com.efc.pdm.LiftApp.models;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity()
@Table(name = "rutina")
public class Routine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, length = 150, name = "nombre")
    private String name;
    @Column(nullable = false, length = 20, name = "dificultad")
    private String difficulty;
    @Column(nullable = false, length = 50, name = "tiempo")
    private String time;
    @Column(nullable = false, length = 250, name = "tag")
    private String tag;
    @ManyToOne
    private User user;
    @ManyToMany
    @JoinTable(name="routine_exc",joinColumns = @JoinColumn(name = "routine_id"),inverseJoinColumns = @JoinColumn(name = "ejercicios_id"))
    private Set<Exercise> exercises = new HashSet<>();

    public Routine(String name, String difficulty, String time, String tag, User user) {
        this.name = name;
        this.difficulty = difficulty;
        this.time = time;
        this.tag = tag;
        this.user = user;
    }

    public Routine(){

    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public String getTime() {
        return time;
    }

    public String getTag() {
        return tag;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<Exercise> getExercises() {
        return exercises;
    }

    public void setExercises(Set<Exercise> exercises) {
        this.exercises = exercises;
    }

    public void addExercise(Exercise exercise){
        this.exercises.add(exercise);
    }

    public void removeExercise(Exercise exercise){
        this.exercises.remove(exercise);
    }
}
