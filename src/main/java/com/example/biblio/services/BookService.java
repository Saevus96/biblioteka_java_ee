package com.example.biblio.services;

import com.example.biblio.models.Book;
import com.example.biblio.models.User;

public interface BookService {
    void saveBook(Book book , long id_user);
}

