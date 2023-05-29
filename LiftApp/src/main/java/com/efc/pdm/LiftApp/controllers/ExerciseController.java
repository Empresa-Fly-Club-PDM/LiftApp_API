package com.efc.pdm.LiftApp.controllers;


import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;
import com.efc.pdm.LiftApp.services.ExerciseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ejercicio")
public class ExerciseController {

    ExerciseRepository excRepository;
    ExerciseService excService;

    @GetMapping("/get")
    public List<Exercise> list() {
        return excService.getAllExercises();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editarEjercicio(@PathVariable Long id) {
        Exercise excUpdate = excRepository.getById(id);
        if (excUpdate != null){
            excService.updateExercise(excUpdate);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity createExc(@RequestBody Exercise exc) {
        Exercise ejercicio = excService.createExercise(exc);
        return ResponseEntity.status(HttpStatus.CREATED).body(ejercicio);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarEjercicio(@PathVariable long id) {
        Exercise excExistente = excRepository.getById(id);
        if (excExistente != null) {
            excService.deleteExerciseById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}
