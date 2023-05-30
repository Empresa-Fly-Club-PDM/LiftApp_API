package com.efc.pdm.LiftApp.repositories;

import com.efc.pdm.LiftApp.models.Exercise;
import com.efc.pdm.LiftApp.models.Routine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoutineRepository  extends JpaRepository<Routine, Long> {

    // TODO() -> All implementation
}
