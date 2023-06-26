package com.efc.pdm.LiftApp.controllers;


import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;
import com.efc.pdm.LiftApp.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/ejercicio")
public class ExerciseController {

    @Autowired
    ExerciseRepository excRepository;
    @Autowired
    ExerciseService exerciseService;

    //Get exercises verified by admin
    @GetMapping("/get/admin/verified")
    public List<Exercise> listVerified(@RequestParam("query") String query) {
        return exerciseService.VerifiedAdminExc(query);
    }

    @GetMapping("/get/admin/earring")
    public List<Exercise> listearring(@RequestParam("query") String query) {
        return exerciseService.EarringUserExc(query);
    }

    @GetMapping("/get/users")
    public List<Exercise> listMyExercises(@RequestParam("query") String query) {
        return exerciseService.GetMyExercises(query);
    }


    //Get exercises verified adding a query
    @GetMapping("/search/user/verified")
    public List<Exercise> searchListVerified(@RequestParam("query")  String query) {
        return exerciseService.searchExcDatabase(query);
    }

    //get data of a single exercise

    @GetMapping("/details/{id}")
    public Optional<Exercise> excDetails(@PathVariable Integer id) {
        return exerciseService.getExcDetails(id);
    }

    //Edit an exercise

    @PutMapping("/edit/{id}")
    public ResponseEntity updateExc(@RequestBody Exercise updatedExc, @PathVariable Integer id) {
        exerciseService.editExc(updatedExc, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //Create a verified Exercise
    @PostMapping("/post/admin/{id}")
    public ResponseEntity createExc(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddVerifiedExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Create a request to verify an exercise
    @PostMapping("/post/user/{id}")
    public ResponseEntity createExcUser(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddEarringExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    ////Create a personal exercise
    @PostMapping("/post/user/personal/{id}")
    public ResponseEntity createMyExc(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddMyExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Delete a personal exercise
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        exerciseService.deleteExerciseById(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    //Deny an exercise verification request
    @DeleteMapping("/deny/{id}")
    public ResponseEntity denyExc(@PathVariable("id") Integer id) {
        exerciseService.denyExercise(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }


    //Authorize an exercise verification request
    @PutMapping("/autorizar/{id}")
    public ResponseEntity authorizeByID(@PathVariable("id") Integer id) {
        exerciseService.AuthorizeExercise(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
