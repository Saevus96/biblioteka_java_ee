package com.example.biblio.repositories;

import com.example.biblio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepo")
public interface UserRepo extends JpaRepository<User, Long> {

    User findByEmail(String email);
}

