package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepo;

    public List<User> getUsers(){
        return userRepo.findAll();
    }


    public User editUser(User newUser, Integer id){
        String rawPasword= newUser.getPassword();
        if(rawPasword==""){
            return editNoPass(newUser,id);
        }else{
            return editPass(newUser,id,rawPasword);
        }
    }

    private User editNoPass(User newUser, Integer id){
        return userRepo.findById(id)
                .map(user -> {
                    user.setNombrecompleto(newUser.getNombrecompleto());
                    user.setEmail(newUser.getEmail());
                    return userRepo.save(user);
                }).orElseGet(() -> {
                    return userRepo.save(newUser);
                });
    }
    private User editPass(User newUser, Integer id, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword=passwordEncoder.encode(password);
        return userRepo.findById(id)
                .map(user -> {
                    user.setNombrecompleto(newUser.getNombrecompleto());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(encodedPassword);
                    return userRepo.save(user);
                }).orElseGet(() -> {
                    return userRepo.save(newUser);
                });

    }

    public User searchByEmail(String email) {
        User usuario = userRepo.UserfindExistence(email);
        return usuario;
    }

    public void deleteUser(Integer id){
        User deluser = userRepo.getById(id);
        userRepo.delete(deluser);
    }

    public Optional<User> changePassword(String newPassword, Integer id) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword=passwordEncoder.encode(newPassword);
        return userRepo.findById(id)
                .map(user -> {
                    user.setPassword(encodedPassword);
                    user.setPasswordState(Boolean.FALSE);
                    return userRepo.save(user);
                });
    }
}
