package com.efc.pdm.LiftApp.controllers;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Routine;
import com.efc.pdm.LiftApp.repositories.RoutineRepository;
import com.efc.pdm.LiftApp.services.RoutineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/routine")
public class RoutineController {

    @Autowired RoutineService routineService;
//Add a new blank routine
    @PostMapping("/add/{id}")
    public ResponseEntity createRoutine(@RequestBody @Valid Routine newRoutine, @PathVariable Integer id) {
        routineService.createRoutine(newRoutine,id);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //Add an exercise to a routine
    @PutMapping("/addExercise/{routineid}/{exerciseid}")
    public ResponseEntity addExerciseToRoutine(@PathVariable Integer routineid, @PathVariable Integer exerciseid) {
        routineService.AddExcToRoutine(routineid,exerciseid);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //Get a routine Exercises
    @GetMapping("/detail/{id}")
    public List<Exercise> getRoutineExercise(@PathVariable Integer id) {
        return routineService.getRoutineExc(id);
    }

    //Get Users Routine
    @GetMapping("/myRoutines/{id}")
    public List<Routine> getMyRoutines(@PathVariable Integer id) {
        return routineService.findAllMyRoutines(id);
    }


}
