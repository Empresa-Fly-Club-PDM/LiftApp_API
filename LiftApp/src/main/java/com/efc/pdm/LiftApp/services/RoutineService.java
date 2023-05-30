package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Routine;
import com.efc.pdm.LiftApp.repositories.RoutineRepository;

import java.util.List;

public class RoutineService {
    RoutineRepository rouRepo;


    /* Create Routine */
    public Routine createRoutine(Routine newRou) {
        return rouRepo.save(newRou);
    }

    /* Get all Routines*/
    public List<Routine> getAllRoutines() {
        return rouRepo.findAll();
    }

    /* Update exercise */
    public Routine updateRoutine(Routine routine) {
        return rouRepo.save(routine);
    }

    /* Delete exersice by id */
    public void deleteRoutineById(Long id) {
        Routine delRou = rouRepo.getById(id);
        rouRepo.delete(delRou);
    }

}
