package com.efc.pdm.LiftApp.repositories;

import com.efc.pdm.LiftApp.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {

    @Query("SELECT e FROM Exercise e WHERE e.verified = :verified AND e.ownership = :ownership AND(e.name LIKE CONCAT('%',:query, '%') OR e.description LIKE CONCAT('%',:query, '%') OR e.difficulty LIKE CONCAT('%',:query, '%') OR e.muscle LIKE CONCAT('%',:query, '%') OR e.name LIKE CONCAT('%',:query, '%') OR e.type LIKE CONCAT('%',:query, '%'))")
    List<Exercise>getExerciseByVerifiedAndOwnership(Boolean verified, Integer ownership,String query);
    @Query("SELECT e FROM Exercise e INNER JOIN e.user u WHERE u.id=:id AND(e.name LIKE CONCAT('%',:query, '%') Or e.muscle LIKE CONCAT('%', :query, '%') Or e.difficulty LIKE CONCAT('%', :query, '%') Or e.type LIKE CONCAT('%', :query, '%') Or e.description LIKE CONCAT('%', :query, '%') )")
    List<Exercise> getMyExercises(Integer id,String query);
    @Query("SELECT e FROM Exercise e INNER JOIN e.user u WHERE (e.verified=true AND (e.name LIKE CONCAT('%',:query, '%') Or e.muscle LIKE CONCAT('%', :query, '%') Or e.difficulty LIKE CONCAT('%', :query, '%') Or e.type LIKE CONCAT('%', :query, '%') Or e.description LIKE CONCAT('%', :query, '%') )) OR (u.id = :userid AND (e.name LIKE CONCAT('%',:query, '%') Or e.muscle LIKE CONCAT('%', :query, '%') Or e.difficulty LIKE CONCAT('%', :query, '%') Or e.type LIKE CONCAT('%', :query, '%') Or e.description LIKE CONCAT('%', :query, '%') ))")
    List<Exercise> searchExercises(String query,Integer userid);



}
