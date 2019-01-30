package com.example.biblio.services;

import com.example.biblio.models.User;

public interface UserService {

    User findUserByEmail(String email);
    void saveUser(User user);
    void saveAdmin(User user);
}

