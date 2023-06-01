package com.efc.pdm.LiftApp.controllers;


import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;
import com.efc.pdm.LiftApp.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/ejercicio")
public class ExerciseController {

    @Autowired
    ExerciseRepository excRepository;
    @Autowired
    ExerciseService exerciseService;

    @GetMapping("/get/verified")
    public List<Exercise> listVerified() {
        return exerciseService.getVerifiedExercises();
    }
    @GetMapping("/get/earring")
    public List<Exercise> listEarring() {
        return exerciseService.getEarringExercises();
    }


    @PutMapping("/edit/{id}")
    public ResponseEntity updateExpense(@RequestBody Exercise updatedExc, @PathVariable Integer id) {
        exerciseService.editExc(updatedExc, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    @PostMapping("/post/admin/{id}")
    public ResponseEntity createExc(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddVerifiedExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PostMapping("/post/user/{id}")
    public ResponseEntity createExcUser(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddEarringExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        exerciseService.deleteExerciseById(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @PutMapping("/autorizar/{id}")
    public ResponseEntity authorizeByID(@PathVariable("id") Integer id) {
        exerciseService.AuthorizeExercise(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

}
