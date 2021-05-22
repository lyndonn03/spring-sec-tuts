package io.lpamintuan.springsectuts.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import io.lpamintuan.springsectuts.backend.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    
}
