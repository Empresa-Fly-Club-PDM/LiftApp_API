package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.auth.AuthRequest;
import com.efc.pdm.LiftApp.auth.AuthResponse;
import com.efc.pdm.LiftApp.jwt.JwtTokenUtil;
import com.efc.pdm.LiftApp.models.Role;
import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import com.efc.pdm.LiftApp.utils.Otp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthService {
    @Autowired UserRepository userRepo;
    @Autowired AuthenticationManager authManager;
    @Autowired JwtTokenUtil jwtUtil;
    @Autowired SendEmailService emailService;

    //Registrar usuario como regular
    public User register(User newUser){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        Double salary = 0.0, limit = 0.0;
        String rawPasword= newUser.getPassword();
        String encodedPassword=passwordEncoder.encode(rawPasword);
        User auxuser = new User(newUser.getNombrecompleto(),newUser.getEmail(),encodedPassword,Boolean.FALSE,newUser.getGenero(),newUser.getFechanac(),newUser.getWeight(),newUser.getHeight(), Role.USER,Boolean.TRUE,0);
        User user = userRepo.save(auxuser);
        return user;
    }

    //Login de usuario
    public AuthResponse login(AuthRequest request){
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(),request.getPassword())
        );
        User user = (User) authentication.getPrincipal();
        String accesToken=jwtUtil.generateAccesToken(user);
        AuthResponse response = new AuthResponse(user.getUsername(), accesToken,user.getId());
        return response;
    }

    //Buscar por email(deprecated)
    public User searchByEmail(String email) {
        User usuario = userRepo.UserfindExistence(email);
        return usuario;
    }


    //Función de cambio de contraseña

    public Optional<User> changePasswordOTP(String email) {
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return userRepo.findByEmail(email)
                .map(user -> {
                    Otp otp = new Otp();
                    String auxotp = otp.generateOTP(8);
                    emailService.sendEmail(user.getEmail(), "Saludos "+user.getNombrecompleto()+" este correo es para informarle que se ha cambiado su contraseña, favor usar contraseña provisional: " + auxotp, "Cambio de contraseña Liftapp");
                    String encodedPassword=passwordEncoder.encode(auxotp);
                    user.setPassword(encodedPassword);
                    user.setPasswordState(Boolean.TRUE);
                    return userRepo.save(user);
                });
    }
}
