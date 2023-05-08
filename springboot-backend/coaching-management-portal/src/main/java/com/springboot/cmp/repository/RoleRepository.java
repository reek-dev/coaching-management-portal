package com.springboot.cmp.repository;

import com.springboot.cmp.entity.EROLE;
import com.springboot.cmp.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(EROLE name);
}
