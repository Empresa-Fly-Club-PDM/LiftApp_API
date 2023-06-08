package com.efc.pdm.LiftApp.repositories;

import com.efc.pdm.LiftApp.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise>getExerciseByVerifiedAndOwnership(Boolean verified, Integer ownership);
    @Query("SELECT e FROM Exercise e INNER JOIN e.user u WHERE u.id=:id")
    List<Exercise> getMyExercises(Integer id);

    @Query("SELECT e FROM Exercise e INNER JOIN e.user u WHERE e.verified = true AND e.ownership = 0 AND (e.name LIKE CONCAT('%',:query, '%') Or e.muscle LIKE CONCAT('%', :query, '%') Or e.difficulty LIKE CONCAT('%', :query, '%') Or e.type LIKE CONCAT('%', :query, '%') Or e.description LIKE CONCAT('%', :query, '%') )")
    List<Exercise> searchExercises(String query);
}
