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

    @Column(nullable= false, name = "role")
    @Enumerated(EnumType.STRING)
    private Role role;

    public User() {
    }

    public User(String nombrecompleto, String email, String password, Role role, Boolean passwordState) {
        this.nombrecompleto = nombrecompleto;
        this.email = email;
        this.password = password;
        this.role = role;
        this.passwordState = passwordState;
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
        // TODO Auto-generated method stub
        return true;
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
}
