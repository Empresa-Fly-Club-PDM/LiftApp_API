package com.efc.pdm.LiftApp.repositories;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RoutineRepository  extends JpaRepository<Routine, Integer> {
    @Query("SELECT r.exercises FROM Routine r")
    List<Exercise> getRoutineExercise(Integer id);

    @Query("SELECT r FROM Routine r INNER JOIN r.user u WHERE u.id=:id AND (r.difficulty LIKE CONCAT('%',:query, '%') OR r.name LIKE CONCAT('%',:query, '%') OR r.tag LIKE CONCAT('%',:query, '%') OR r.time LIKE CONCAT('%',:query, '%'))")
    List<Routine> getAllMyRoutines(Integer id, String query);
}
