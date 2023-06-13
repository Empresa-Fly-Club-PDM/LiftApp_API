package com.efc.pdm.LiftApp.repositories;

import com.efc.pdm.LiftApp.models.Role;
import com.efc.pdm.LiftApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.email =:email")
    User UserfindExistence(String email);
    @Query("SELECT u FROM User u WHERE u.role = 'USER' AND u.enabledstate = TRUE ORDER BY u.points ASC")
    List<User> getTop5();

    List<User>findAllByRole(Role role);


}
