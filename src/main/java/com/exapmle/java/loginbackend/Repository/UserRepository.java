
         package com.exapmle.java.loginbackend.Repository;


 import com.exapmle.java.loginbackend.entities.User;
 import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);

    Optional<User> findByUsername(String username);

    List<User> findByFirstNameLike(String firstName);
}
