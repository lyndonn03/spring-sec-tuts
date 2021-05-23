package io.lpamintuan.springsectuts.backend.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import io.lpamintuan.springsectuts.backend.models.Role;

public interface RoleRepository extends JpaRepository<Role, String> {
    
}
