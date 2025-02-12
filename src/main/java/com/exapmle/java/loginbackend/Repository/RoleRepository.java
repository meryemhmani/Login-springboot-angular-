package com.exapmle.java.loginbackend.Repository;

import com.exapmle.java.loginbackend.entities.ERole;
import com.exapmle.java.loginbackend.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);

}
