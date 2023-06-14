package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Routine;
import com.efc.pdm.LiftApp.repositories.RoutineRepository;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoutineService {
    @Autowired RoutineRepository routineRepository;
    @Autowired
    UserRepository userRepository;

    //Create new blank routine
    public Routine createRoutine(Routine newRoutine, Integer id) {
        Routine auxroutine = new Routine(newRoutine.getName(), newRoutine.getDifficulty(), newRoutine.getTime(), newRoutine.getTag(),userRepository.getReferenceById(id));
        Routine routine = routineRepository.save(auxroutine);
        return routine;
    }

    public void AddExcToRoutine(Integer routineId, Integer excId) {
        Routine routine = routineRepository.getReferenceById(routineId);
        routine.addExercise(new Exercise(excId));
        routineRepository.save(routine);
    }
    public void RemoveExcToRoutine(Integer routineId, Integer excId) {
        Routine routine = routineRepository.getReferenceById(routineId);
        routine.removeExercise(new Exercise(excId));
        routineRepository.save(routine);
    }
    public List<Exercise> getRoutineExc(Integer id) {
        return routineRepository.getRoutineExercise(id);
    }

    public List<Routine>findAllMyRoutines(Integer id){
        return routineRepository.getAllMyRoutines(id);
    }

}
