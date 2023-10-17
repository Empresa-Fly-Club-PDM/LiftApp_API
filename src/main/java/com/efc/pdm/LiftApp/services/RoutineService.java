package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Routine;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;
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
    @Autowired ExerciseRepository exerciseRepository;

    //Create new blank routine
    public Routine createRoutine(Routine newRoutine, Integer id) {
        Routine auxroutine = new Routine(newRoutine.getName(), newRoutine.getDifficulty(), newRoutine.getTime(), newRoutine.getTag(),userRepository.getReferenceById(id));
        Routine routine = routineRepository.save(auxroutine);
        return routine;
    }

    //Add an exercise to a routine
    public void AddExcToRoutine(Integer routineId, Integer excId) {
        Routine routine = routineRepository.getReferenceById(routineId);
        routine.addExercise(new Exercise(excId));
        routineRepository.save(routine);
    }
    //Remove an exercise from a routine
    public void RemoveExcToRoutine(Integer routineId, Integer excId) {
        Routine routine = routineRepository.getReferenceById(routineId);
        Exercise exercise = exerciseRepository.getReferenceById(excId);
        routine.removeExercise(exercise);
        routineRepository.save(routine);
    }

    //Get the exercises inside a routine
    public List<Exercise> getRoutineExc(Integer id) {
        return routineRepository.getRoutineExercise(id);
    }

    //Get the whole routine
    public List<Routine>findAllMyRoutines(Integer id,String query){
        return routineRepository.getAllMyRoutines(id,query);
    }

    //Edit the fields of a routine not included the exercises of this one
    public Optional<Routine> editRoutine(Routine newRoutine, Integer routineId){
        return routineRepository.findById(routineId)
                .map(routine -> {
                    routine.setName(newRoutine.getName());
                    routine.setDifficulty(newRoutine.getDifficulty());
                    routine.setTime(newRoutine.getTime());
                    routine.setTag(newRoutine.getTag());
                    return routineRepository.save(routine);
                });
    }

    //Delete routine by id
    public void deleteRotineById(Integer id) {
        Routine delroutine = routineRepository.getReferenceById(id);
        routineRepository.delete(delroutine);
    }
    public Optional<Routine> getRoutineInfo(Integer id) {
        return routineRepository.findById(id);
    }


}
