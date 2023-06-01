package com.efc.pdm.LiftApp.models;

import javax.persistence.*;

@Entity()
@Table(name = "rutina")
public class Routine {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true, length = 150, name = "nombre")
    private String name;
    @Column(nullable = false, unique = true, length = 20, name = "dificultad")
    private String difficulty;
    @Column(nullable = false, unique = true, length = 50, name = "tiempo")
    private String time;
    @Column(nullable = false, unique = true, length = 250, name = "tag")
    private String tag;
    @Column(name = "verificado")
    private boolean verified;

    @ManyToOne
    private User user;

    public Routine(String name, String difficulty, String time, String tag, boolean verified, User user) {
        this.name = name;
        this.difficulty = difficulty;
        this.time = time;
        this.tag = tag;
        this.verified = verified;
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

    public boolean isVerified() {
        return verified;
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

    public void setVerified(boolean verified) {
        this.verified = verified;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
