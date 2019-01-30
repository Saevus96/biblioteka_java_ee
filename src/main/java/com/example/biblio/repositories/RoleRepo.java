package com.example.biblio.repositories;

import com.example.biblio.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("roleRepo")
public interface RoleRepo extends JpaRepository<Role, Integer> {

    Role findByRole(String role);
}

