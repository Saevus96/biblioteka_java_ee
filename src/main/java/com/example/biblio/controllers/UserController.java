package com.example.biblio.controllers;

import com.example.biblio.repositories.RoleRepo;
import com.example.biblio.repositories.UserRepo;
import com.example.biblio.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/*")
public class UserController {

    @Autowired
    UserRepo userRepo;

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping(value = "/index")
    public String indexlog() {
        logger.info("Spring Android Basic Auth");

        return "index";
    }
}
