package com.efc.pdm.LiftApp.services;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Lift;
import com.efc.pdm.LiftApp.models.User;
import com.efc.pdm.LiftApp.repositories.ExerciseRepository;
import com.efc.pdm.LiftApp.repositories.LiftRepository;
import com.efc.pdm.LiftApp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LiftService {
    @Autowired
    ExerciseRepository exerciseRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    LiftRepository liftRepository;

    //Add a new record
    public Lift AddNewRecord(Lift newlift, Integer idExercise, Integer idUser) {
        Exercise auxexc = exerciseRepository.getReferenceById(idExercise);
        User user = userRepository.getReferenceById(idUser);
        Integer points = (newlift.getWeight() / 8) * user.getWeight();
        Integer actualPoints = user.getPoints();
        user.setPoints(points+actualPoints);
        Lift PreviousBest = liftRepository.findUserBestLift(user.getId());
        Boolean highlight = Boolean.FALSE;
        if(PreviousBest == null){
            highlight = Boolean.TRUE;
        }
        else if(PreviousBest != null && points>PreviousBest.getLiftpoints()){
            highlight = Boolean.TRUE;
            PreviousBest.setHighlight(Boolean.FALSE);
        }
        Lift lift = new Lift(auxexc.getName(),newlift.getWeight(), newlift.getReps(),points,highlight, user);
        Lift savelift = liftRepository.save(lift);
        return savelift;
    }

    //Remove a lift
    public void deleteLift(Integer id) {
        Lift dellift = liftRepository.getReferenceById(id);
        User user = dellift.getUser();
        Integer actualPoints = user.getPoints();
        user.setPoints(actualPoints-dellift.getLiftpoints());
        liftRepository.delete(dellift);
        List<Lift> newBestLift = liftRepository.determineBestLift(user.getId());
        Lift bestLift = newBestLift.get(0);
        bestLift.setHighlight(Boolean.TRUE);
        liftRepository.save(bestLift);
    }

    //Get all my records
    public List<Lift> AllMyRecords(Integer id) {
        return liftRepository.findAllMyRecords(id);
    }

    //Get My Best lift
    public Lift BestLift(Integer id) {
        return liftRepository.findUserBestLift(id);
    }



}
