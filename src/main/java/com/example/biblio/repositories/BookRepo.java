package com.example.biblio.repositories;

import com.example.biblio.models.Book;
import com.example.biblio.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepo extends JpaRepository<Book, Integer> {
    Book findBookById(int id);
}
