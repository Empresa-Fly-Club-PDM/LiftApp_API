package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;

import java.util.List;
import java.util.Optional;


public class ExerciseService {
    ExerciseRepository exerciseRepo;

    public Exercise createExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    public List<Exercise> getAllExercises() {
        return exerciseRepo.findAll();
    }


    public Exercise updateExercise(Exercise exercise) {
        return exerciseRepo.save(exercise);
    }

    public void deleteExerciseById(Long id) {
        exerciseRepo.deleteById(id);
    }

}
