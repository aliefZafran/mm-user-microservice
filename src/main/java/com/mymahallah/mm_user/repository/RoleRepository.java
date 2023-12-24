package com.mymahallah.mm_user.repository;

import com.mymahallah.mm_user.entities.ERole;
import com.mymahallah.mm_user.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(ERole name);
}
