package com.efc.pdm.LiftApp.auth;

import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthApi {
	@Autowired
	AuthService authService;
	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody @Valid AuthRequest request){
		try {
			return ResponseEntity.ok(authService.login(request));
		} catch (BadCredentialsException ex) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity createUser(@RequestBody @Valid User newUser) {
			authService.register(newUser);
			return ResponseEntity.status(HttpStatus.CREATED).build();
		}

	@GetMapping("/recoverpassword")
	public ResponseEntity recoverPassword(@RequestBody String email) {
		User recoveryUser = authService.searchByEmail(email);
		if(recoveryUser != null){
			System.out.println("userfound");
			authService.changePasswordOTP(email);
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}else{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}
}
