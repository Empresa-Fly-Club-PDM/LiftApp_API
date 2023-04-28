package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.auth.AuthRequest;
import com.efc.pdm.LiftApp.auth.AuthResponse;
import com.efc.pdm.LiftApp.jwt.JwtTokenUtil;
import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired UserRepository userRepo;
    @Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;
    public User register(User newUser){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Double salary = 0.0, limit = 0.0;
        String rawPasword= newUser.getPassword();
        String encodedPassword=passwordEncoder.encode(rawPasword);
        User auxuser = new User(newUser.getNombrecompleto(),newUser.getEmail(),encodedPassword, newUser.getRole());
        User user = userRepo.save(auxuser);
        return user;
    }
    public AuthResponse login(AuthRequest request){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accesToken=jwtUtil.generateAccesToken(user);
        AuthResponse response = new AuthResponse(user.getUsername(), accesToken,user.getId());
        return response;
    }
}
