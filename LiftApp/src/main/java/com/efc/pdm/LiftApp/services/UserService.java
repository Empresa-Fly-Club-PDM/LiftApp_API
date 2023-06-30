package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Role;
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


    //Crear usuario como administrador
    public User AddAdminUser(User newUser){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Double salary = 0.0, limit = 0.0;
        String rawPasword= newUser.getPassword();
        String encodedPassword=passwordEncoder.encode(rawPasword);
        User auxuser = new User(newUser.getNombrecompleto(),newUser.getEmail(),encodedPassword, Role.ADMIN,Boolean.FALSE,Boolean.TRUE);
        User user = userRepo.save(auxuser);
        return user;
    }



    //Editar usuario como administrador
    public User editUser(User newUser, Integer id){
        String rawPasword= newUser.getPassword();
        if(rawPasword==""){
            return editNoPass(newUser,id);
        }else{
            return editPass(newUser,id,rawPasword);
        }
    }

    //Editar como administrador sin modificar contraseña
    private User editNoPass(User newUser, Integer id){
        return userRepo.findById(id)
                .map(user -> {
                    user.setNombrecompleto(newUser.getNombrecompleto());
                    user.setEmail(newUser.getEmail());
                    user.setGenero(newUser.getGenero());
                    user.setWeight(newUser.getWeight());
                    user.setHeight(newUser.getHeight());
                    user.setFechanac(newUser.getFechanac());
                    return userRepo.save(user);
                }).orElseGet(() -> {
                    return userRepo.save(newUser);
                });
    }

    //Editar incluyendo cambio de contraseña
    private User editPass(User newUser, Integer id, String password){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword=passwordEncoder.encode(password);
        return userRepo.findById(id)
                .map(user -> {
                    user.setNombrecompleto(newUser.getNombrecompleto());
                    user.setEmail(newUser.getEmail());
                    user.setPassword(encodedPassword);
                    user.setGenero(newUser.getGenero());
                    user.setWeight(newUser.getWeight());
                    user.setHeight(newUser.getHeight());
                    user.setFechanac(newUser.getFechanac());
                    return userRepo.save(user);
                }).orElseGet(() -> {
                    return userRepo.save(newUser);
                });

    }

    public Optional<User> getUserDetails(Integer id) {
        return userRepo.findById(id);
    }

    public List<User> gettopFive(String query){
        return  userRepo.getTop5(query);
    }

    public List<User>SearchAllUsers(Integer id,String query){
        return userRepo.findNonFriendUsers(id,query);
    };

    //Cambiar contraseña del usuario
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

    //Get all admins by Role
    public List<User> getAllAdmins(String query) {
        return userRepo.SearchFromAllAdmins(query);
    }

    public Optional<User> Delete(Integer id) {
        return userRepo.findById(id)
                .map(user -> {
                    user.setEnabledstate(false);
                    return userRepo.save(user);
                });
    }

    public void deleteAdmin(Integer id) {
        User deluser = userRepo.getReferenceById(id);
        userRepo.delete(deluser);
    }
}
