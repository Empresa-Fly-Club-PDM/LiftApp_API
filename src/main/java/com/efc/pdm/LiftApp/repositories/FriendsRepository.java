package com.efc.pdm.LiftApp.repositories;


import com.efc.pdm.LiftApp.models.Friends;
import com.efc.pdm.LiftApp.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendsRepository extends JpaRepository<Friends,Integer> {

    boolean existsByFirstUserAndSecondUser(User first, User second);

    List<Friends> findByFirstUser(User user);
    List<Friends> findBySecondUser(User user);
}
