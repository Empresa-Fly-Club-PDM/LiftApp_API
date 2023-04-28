package com.efc.pdm.LiftApp.controllers;

import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import com.efc.pdm.LiftApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;

	@GetMapping("/get")
	public List<User> list() {
		return userService.getUsers();
	}

	@PutMapping("/edit/{id}")
	public ResponseEntity updateUser(@RequestBody User updatedUser, @PathVariable Integer id) {
		userService.editUser(updatedUser, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	@GetMapping("/checkExist")
	public ResponseEntity recoverPassword(@RequestBody String email) {
		if(userService.searchByEmail(email).size()>0){
			return ResponseEntity.status(HttpStatus.ACCEPTED).build();
		}else{
			return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
		}
	}

	@PutMapping("/recovery/{id}")
	public ResponseEntity resetPassword(@RequestBody String newPassword, @PathVariable Integer id){
		userService.changePassword(newPassword,id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}
}
