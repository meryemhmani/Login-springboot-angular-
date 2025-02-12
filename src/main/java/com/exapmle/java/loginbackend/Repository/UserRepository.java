
         package com.exapmle.java.loginbackend.Repository;


 import com.exapmle.java.loginbackend.entities.User;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    Boolean existsByUsername(String username);

     boolean existsByEmail(String email);


     @Override
    Optional<User> findById(Long aLong);


}
