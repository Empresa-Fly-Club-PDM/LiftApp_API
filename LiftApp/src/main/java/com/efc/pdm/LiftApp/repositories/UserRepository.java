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
    @Query("SELECT u FROM User u WHERE u.role = 'USER' AND u.enabledstate = TRUE AND u.enabledstate = true AND (u.email LIKE CONCAT('%',:query, '%') OR u.nombrecompleto LIKE CONCAT('%',:query, '%') OR u.fechanac LIKE CONCAT('%',:query, '%') OR u.genero LIKE CONCAT('%',:query, '%') OR u.height LIKE CONCAT('%',:query, '%') OR u.weight LIKE CONCAT('%',:query, '%')) ORDER BY u.points ASC")
    List<User> getTop5(String query);

    @Query("SELECT u FROM User u WHERE u.role = 'USER' AND u.enabledstate = TRUE AND u.enabledstate = true AND (u.email LIKE CONCAT('%',:query, '%') OR u.nombrecompleto LIKE CONCAT('%',:query, '%') OR u.fechanac LIKE CONCAT('%',:query, '%') OR u.genero LIKE CONCAT('%',:query, '%') OR u.height LIKE CONCAT('%',:query, '%') OR u.weight LIKE CONCAT('%',:query, '%'))")
    List<User> searchFromAllUser(String query);

    @Query("SELECT u FROM User u WHERE u.id != :searchingUserId AND u.id NOT IN (SELECT CASE WHEN f.firstUser.id = :searchingUserId THEN f.secondUser.id ELSE f.firstUser.id END FROM Friends f WHERE f.firstUser.id = :searchingUserId OR f.secondUser.id = :searchingUserId) AND u.role != com.efc.pdm.LiftApp.models.Role.ADMIN AND (u.email LIKE CONCAT('%',:query, '%') OR u.nombrecompleto LIKE CONCAT('%',:query, '%') OR u.fechanac LIKE CONCAT('%',:query, '%') OR u.genero LIKE CONCAT('%',:query, '%') OR u.height LIKE CONCAT('%',:query, '%') OR u.weight LIKE CONCAT('%',:query, '%'))")
    List<User> findNonFriendUsers(Integer searchingUserId,String query);

    @Query("SELECT u FROM User u WHERE u.role = 'ADMIN' AND u.enabledstate = true AND  (u.email LIKE CONCAT('%',:query, '%') OR u.nombrecompleto LIKE CONCAT('%',:query, '%') )")
    List<User> SearchFromAllAdmins(String query);


}
