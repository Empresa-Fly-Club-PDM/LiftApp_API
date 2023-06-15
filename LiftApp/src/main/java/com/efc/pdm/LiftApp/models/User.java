package com.efc.pdm.LiftApp.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name="usuarios")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable= false, length= 150 ,name = "nombrecompleto")
    private String nombrecompleto;
    @Column(nullable= false, unique= true, length= 150 ,name = "email")
    private String email;
    @Column(nullable= false, length=128, name = "password")
    private String password;
    @Column(nullable= false, name = "password_state")
    private Boolean passwordState;

    @Column(nullable= true, name = "genero")
    private String genero;

    @Column(nullable= true, name = "fechanac")
    private String fechanac;
    @Column(nullable= true, name = "weight")
    private Integer weight;
    @Column(nullable= true, name = "height")
    private Double height;

    @Column(nullable= false, name = "enabledstate")
    private Boolean enabledstate;

    @Column(nullable= true, name = "points")
    private Integer points;

    @Column(nullable= false, name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;


    public User() {
    }

    //Constructor para administradores

    public User(String nombrecompleto, String email, String password, Role role, Boolean passwordState, Boolean enabledstate) {
        this.nombrecompleto = nombrecompleto;
        this.email = email;
        this.password = password;
        this.role = role;
        this.passwordState = passwordState;
        this.enabledstate = enabledstate;
    }

    //Constructor para usuario regular


    public User(String nombrecompleto, String email, String password, Boolean passwordState, String genero, String fechanac, Integer weight, Double height, Role role, Boolean enabledstate, Integer points) {
        this.nombrecompleto = nombrecompleto;
        this.email = email;
        this.password = password;
        this.passwordState = passwordState;
        this.genero = genero;
        this.fechanac = fechanac;
        this.weight = weight;
        this.height = height;
        this.role = role;
        this.enabledstate = enabledstate;
        this.points = points;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(Role.USER.toString()));
    }

    @Override
    public String getPassword() {
        // TODO Auto-generated method stub
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        // TODO Auto-generated method stub
        return true;
    }

    @Override
    public boolean isEnabled() {
        if(enabledstate == Boolean.TRUE){
            return true;
        }else{
            return false;
        }
    }

    public Integer getId() {
        return id;
    }


    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getEmail() {
        return email;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getPasswordState() {
        return passwordState;
    }

    public void setPasswordState(Boolean passwordState) {
        this.passwordState = passwordState;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getFechanac() {
        return fechanac;
    }

    public void setFechanac(String fechanac) {
        this.fechanac = fechanac;
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Boolean getEnabledstate() {
        return enabledstate;
    }

    public void setEnabledstate(Boolean enabledstate) {
        this.enabledstate = enabledstate;
    }

    public Integer getPoints() {
        return points;
    }

    public void setPoints(Integer points) {
        this.points = points;
    }
}
