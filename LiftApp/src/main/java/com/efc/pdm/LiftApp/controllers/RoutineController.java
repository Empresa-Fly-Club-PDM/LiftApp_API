package com.efc.pdm.LiftApp.controllers;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Routine;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;
import com.efc.pdm.LiftApp.repositories.RoutineRepository;
import com.efc.pdm.LiftApp.services.ExerciseService;
import com.efc.pdm.LiftApp.services.RoutineService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rutina")
public class RoutineController {


    RoutineRepository rouRepository;
    RoutineService rouService;

    @GetMapping("/get")
    public List<Routine> list() {
        return rouService.getAllRoutines();
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity editarRutina(@PathVariable Long id) {
        Routine rouUpdate = rouRepository.getById(id);
        if (rouUpdate != null){
            rouService.updateRoutine(rouUpdate);
            return ResponseEntity.status(HttpStatus.ACCEPTED).build();
        }else{
            return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).build();
        }
    }

    @PostMapping("/post")
    public ResponseEntity createRou(@RequestBody Routine rou) {
        Routine rutina = rouService.createRoutine(rou);
        return ResponseEntity.status(HttpStatus.CREATED).body(rutina);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> eliminarRutina(@PathVariable long id) {
        Routine rouExistente = rouRepository.getById(id);
        if (rouExistente != null) {
            rouService.deleteRoutineById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
