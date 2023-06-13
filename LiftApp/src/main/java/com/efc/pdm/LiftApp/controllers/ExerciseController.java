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

@RestController
@RequestMapping("/ejercicio")
public class ExerciseController {

    @Autowired
    ExerciseRepository excRepository;
    @Autowired
    ExerciseService exerciseService;

    //DONE
    @GetMapping("/get/admin/verified")
    public List<Exercise> listVerified() {
        return exerciseService.VerifiedAdminExc();
    }


    //DONE
    @GetMapping("/search/user/verified/{query}")
    public List<Exercise> searchListVerified(@PathVariable String query) {
        return exerciseService.searchExcDatabase(query);
    }

    @GetMapping("/details/{id}")
    public Optional<Exercise> excDetails(@PathVariable Integer id) {
        return exerciseService.getExcDetails(id);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity updateExpense(@RequestBody Exercise updatedExc, @PathVariable Integer id) {
        exerciseService.editExc(updatedExc, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    //DONE
    @PostMapping("/post/admin/{id}")
    public ResponseEntity createExc(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddVerifiedExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //DONE
    @PostMapping("/post/user/{id}")
    public ResponseEntity createExcUser(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddEarringExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
    //DONE
    @PostMapping("/post/user/personal/{id}")
    public ResponseEntity createMyExc(@RequestBody @Valid Exercise newExc, @PathVariable Integer id) {
        exerciseService.AddMyExercise(newExc,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //DONE
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        exerciseService.deleteExerciseById(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    //DONE
    @DeleteMapping("/deny/{id}")
    public ResponseEntity denyExc(@PathVariable("id") Integer id) {
        exerciseService.denyExercise(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }


    //DONE
    @PutMapping("/autorizar/{id}")
    public ResponseEntity authorizeByID(@PathVariable("id") Integer id) {
        exerciseService.AuthorizeExercise(id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


}
