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
import java.util.Optional;

@CrossOrigin
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

    //Remove an exercise of a rotuine
    @PutMapping("/removeExercise/{routineid}/{exerciseid}")
    public ResponseEntity removeExerciseToRoutine(@PathVariable Integer routineid, @PathVariable Integer exerciseid) {
        routineService.RemoveExcToRoutine(routineid,exerciseid);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //Get a routine Exercises
    @GetMapping("/detail/{id}")
    public List<Exercise> getRoutineExercise(@PathVariable Integer id) {
        return routineService.getRoutineExc(id);
    }

    //Get Users Routine
    @GetMapping("/myRoutines/{id}")
    public List<Routine> getMyRoutines(@RequestParam("query")String query ,@PathVariable Integer id) {
        return routineService.findAllMyRoutines(id,query);
    }

    //Edit the fields of a routine
    @PutMapping("/edit/{id}")
    public ResponseEntity updateRoutine(@RequestBody Routine updatedRoutine, @PathVariable Integer id) {
        routineService.editRoutine(updatedRoutine, id);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }

    //Delete routine by id
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        routineService.deleteRotineById(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

    @GetMapping("/info/{id}")
    public Optional<Routine> excDetails(@PathVariable Integer id) {
        return routineService.getRoutineInfo(id);
    }


}
