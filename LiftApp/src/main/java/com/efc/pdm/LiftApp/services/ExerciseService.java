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

    //Ejercicios debe crearse bajo las situaciónes:
    //Consideraciones en el servicio:
    //Administrador
    //Administrador debe ver todas las solicitudes de los usuarios verified = false, ownership = 1(pertenece a pendientes)
    public List<Exercise> EarringUserExc() {
        return exerciseRepo.getExerciseByVerifiedAndOwnership(false,1);
    }

    //Administrador debe poder aprobarlo(verified = true, ownership pasa a 0 owned by app, envia un correo notificando al user)
    public Optional<Exercise> AuthorizeExercise(Integer id) {
        return exerciseRepo.findById(id)
                .map(exc->{
                    exc.setVerified(true);
                    exc.setOwnership(0);
                    return exerciseRepo.save(exc);
                });
    }
    //Administrador debe poder negarlo (Elimina de la base de datos)
    public void denyExercise(Integer id) {
        Exercise delexc = exerciseRepo.getReferenceById(id);
        exerciseRepo.delete(delexc);
    }

    //Administrador debe poder visualizar la base de datos oficial (verified true, ownership en 0)
    public List<Exercise> VerifiedAdminExc() {
        return exerciseRepo.getExerciseByVerifiedAndOwnership(true,0);
    }

    //Administrador debe poder eliminar el ejercicio
    public void deleteExerciseById(Integer id) {
        Exercise delexc = exerciseRepo.getReferenceById(id);
        exerciseRepo.delete(delexc);
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

    //Administrador debe poder crear ejercico verificado
    public Exercise AddVerifiedExercise(Exercise newExc, Integer id) {
        Exercise auxexc = new Exercise(newExc.getName(),newExc.getMuscle(), newExc.getDifficulty(), newExc.getType(), newExc.getDescription(), newExc.getSets(),newExc.getReps(),true,userRepository.getReferenceById(id),0);
        Exercise exc = exerciseRepo.save(auxexc);
        return exc;
    }



    //Usuario
    //Usuario debe poder ver sus ejercicios (verified = false, ownership 2) join a usuarios para ver por id de usuario

    public List<Exercise> getPersonalExc(Integer id) {
        return exerciseRepo.getMyExercises(id);
    }
    //El usuario debe poder ingresar una petición para verificación (verified false, ownership 1)
    public Exercise AddEarringExercise(Exercise newExc, Integer id) {
        Exercise auxexc = new Exercise(newExc.getName(),newExc.getMuscle(), newExc.getDifficulty(), newExc.getType(), newExc.getDescription(), newExc.getSets(),newExc.getReps(),false,userRepository.getReferenceById(id),1);
        Exercise exc = exerciseRepo.save(auxexc);
        return exc;
    }
    //El usuario debe poder crear sus ejercicios (verified = false, ownership 2)
    public Exercise AddMyExercise(Exercise newExc, Integer id) {
        Exercise auxexc = new Exercise(newExc.getName(),newExc.getMuscle(), newExc.getDifficulty(), newExc.getType(), newExc.getDescription(), newExc.getSets(),newExc.getReps(),false,userRepository.getReferenceById(id),2);
        Exercise exc = exerciseRepo.save(auxexc);
        return exc;
    }

    //El usuario debe poder buscar entre sus ejercicios y los ejercicios globales primero los suyos (Query ownership 0 y 2)
    public List<Exercise> searchExcDatabase(String query){
        return exerciseRepo.searchExercises(query);
    }






}
