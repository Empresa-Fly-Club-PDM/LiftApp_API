package com.efc.pdm.LiftApp.repositories;

import com.efc.pdm.LiftApp.models.Exercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExerciseRepository extends JpaRepository<Exercise, Integer> {
    List<Exercise>getExerciseByVerified(Boolean verified);
}
