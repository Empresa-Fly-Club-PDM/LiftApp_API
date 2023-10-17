package com.efc.pdm.LiftApp.controllers;

import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import com.efc.pdm.LiftApp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/usuario")
public class UsuarioController {

	@Autowired
	UserRepository userRepo;
	@Autowired
	UserService userService;

	//Crear usuario de tipo administrador
	@PostMapping("/add/admin")
	public ResponseEntity addAdminUser(@RequestBody User newUser){
		userService.AddAdminUser(newUser);
		return ResponseEntity.status(HttpStatus.CREATED).build();
	}

	//DEtalle de un usuario

	@GetMapping("/details/{id}")
	public Optional<User> userDetails(@PathVariable Integer id) {
		return userService.getUserDetails(id);
	}

	//OBTENER TOP 5
	@GetMapping("/top5")
	public List<User> findTopFive(@RequestParam("query") String query) {
		return userService.gettopFive(query);
	}

	//Edit user
	@PutMapping("/edit/{id}")
	public ResponseEntity updateUser(@RequestBody User updatedUser, @PathVariable Integer id) {
		userService.editUser(updatedUser, id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	//Recuperar contraseña
	@PutMapping("/recovery/{id}")
	public ResponseEntity resetPassword(@RequestBody String newPassword, @PathVariable Integer id){
		userService.changePassword(newPassword,id);
		return ResponseEntity.status(HttpStatus.ACCEPTED).build();
	}

	//Suspend user account
	@PutMapping("/suspend/{id}")
	public ResponseEntity suspendaccount(@PathVariable Integer id){
		userService.Delete(id);
		return ResponseEntity.status(HttpStatus.GONE).build();
	}

	//Delete Admin

	@DeleteMapping("/delete/{id}")
	public ResponseEntity deleteadmin(@PathVariable Integer id){
		userService.deleteAdmin(id);
		return ResponseEntity.status(HttpStatus.GONE).build();
	}





	//Get all admins
	@GetMapping("/get/admins")
	public List<User> findAllAdmins(@RequestParam("query") String query) {
		return userService.getAllAdmins(query);
	}

	@GetMapping("/get/users/{id}")
	public List<User> findAllUsers(@PathVariable Integer id,@RequestParam("query") String query ) {
		return userService.SearchAllUsers(id,query);
	}

}
