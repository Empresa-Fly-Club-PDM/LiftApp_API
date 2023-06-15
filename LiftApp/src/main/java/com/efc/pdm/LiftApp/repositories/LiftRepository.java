package com.efc.pdm.LiftApp.repositories;

import com.efc.pdm.LiftApp.models.Lift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LiftRepository extends JpaRepository<Lift,Integer> {
    @Query("SELECT l from Lift l INNER JOIN l.user u WHERE u.id = :id AND l.highlight = true")
    Lift findUserBestLift(Integer id);

    @Query("SELECT l from Lift l INNER JOIN l.user u WHERE u.id = :id AND l.liftpoints = (SELECT MAX(l.liftpoints) from Lift l)")
    List<Lift> determineBestLift(Integer id);

    @Query("SELECT l from Lift l INNER JOIN l.user u WHERE u.id = :id")
    List<Lift> findAllMyRecords(Integer id);
}
