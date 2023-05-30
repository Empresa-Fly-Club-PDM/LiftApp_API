package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;

import java.util.List;
import java.util.Optional;


public class ExerciseService {
    ExerciseRepository exerciseRepo;


    /* Create exercise */
    public Exercise createExercise(Exercise newExc) {
        return exerciseRepo.save(newExc);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }

    /* Update exercise */
    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    /* Delete exersice by id */
    public void deleteExerciseById(Long id) {
        Exercise delExc = exerciseRepo.getById(id);
        exerciseRepo.delete(delExc);
    }



}
