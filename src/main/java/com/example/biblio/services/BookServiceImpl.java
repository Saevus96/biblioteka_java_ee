package com.example.biblio.services;

import com.example.biblio.models.Book;
import com.example.biblio.models.User;
import com.example.biblio.repositories.BookRepo;
import com.example.biblio.repositories.RoleRepo;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

@Service("bookService")
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepo bookRepo;
    @Autowired
    private RoleRepo roleRepo;

    @Override
    public void saveBook(Book book , long id_user) {

        bookRepo.save(book);
    }

}

