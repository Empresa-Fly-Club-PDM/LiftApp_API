package com.efc.pdm.LiftApp.controllers;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Lift;
import com.efc.pdm.LiftApp.services.LiftService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/lift")
public class LiftController {

    @Autowired
    LiftService liftService;

    @GetMapping("/mylifts/{id}")
    public List<Lift> getMyRecords(@RequestParam("query") String query, @PathVariable Integer id) {
        return liftService.AllMyRecords(id, query);
    }
    @GetMapping("/myHighlight/{id}")
    public Lift getBestLift(@PathVariable Integer id) {
        return liftService.BestLift(id);
    }

    @PostMapping("/add/{exerciseid}/{idUser}")
    public ResponseEntity createExc(@RequestBody Lift newlift, @PathVariable Integer exerciseid,@PathVariable Integer idUser) {
        liftService.AddNewRecord(newlift,exerciseid,idUser);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteById(@PathVariable("id") Integer id) {
        liftService.deleteLift(id);
        return ResponseEntity.status(HttpStatus.GONE).build();
    }

}
