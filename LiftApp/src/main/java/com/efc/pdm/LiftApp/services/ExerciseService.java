package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ExerciseService {
    @Autowired
    ExerciseRepository exerciseRepo;

    @Autowired
    UserRepository userRepository;
    public Exercise AddVerifiedExercise(Exercise newExc, Integer id) {
        Exercise auxexc = new Exercise(newExc.getName(),newExc.getMuscle(), newExc.getDifficulty(), newExc.getType(), newExc.getDescription(), newExc.getSets(),newExc.getReps(),true,userRepository.getReferenceById(id));
        Exercise exc = exerciseRepo.save(auxexc);
        return exc;
    }

    public Exercise AddEarringExercise(Exercise newExc, Integer id) {
        Exercise auxexc = new Exercise(newExc.getName(),newExc.getMuscle(), newExc.getDifficulty(), newExc.getType(), newExc.getDescription(), newExc.getSets(),newExc.getReps(),false,userRepository.getReferenceById(id));
        Exercise exc = exerciseRepo.save(auxexc);
        return exc;
    }

    public List<Exercise> getVerifiedExercises() {
        return exerciseRepo.getExerciseByVerified(true);
    }

    public List<Exercise> getEarringExercises(){
        return exerciseRepo.getExerciseByVerified(false);
    }


    /* Update exercise */

    public Optional<Exercise> editExc(Exercise newExce, Integer excid){
        return exerciseRepo.findById(excid)
                .map(exc -> {
                    exc.setName(newExce.getName());
                    exc.setMuscle(newExce.getMuscle());
                    exc.setDifficulty(newExce.getDifficulty());
                    exc.setType(newExce.getType());
                    exc.setDescription(newExce.getDescription());
                    exc.setSets(newExce.getSets());
                    exc.setReps(newExce.getReps());
                    return exerciseRepo.save(exc);
                });
    }

    public Optional<Exercise> getExcDetails(Integer id) {
        return exerciseRepo.findById(id);
    }

    /* Delete exersice by id */
    public void deleteExerciseById(Integer id) {
        Exercise delexc = exerciseRepo.getReferenceById(id);
        exerciseRepo.delete(delexc);
    }

    public Optional<Exercise> AuthorizeExercise(Integer id) {
        return exerciseRepo.findById(id)
                .map(exc->{
                    exc.setVerified(true);
                    return exerciseRepo.save(exc);
                });
    }
}
