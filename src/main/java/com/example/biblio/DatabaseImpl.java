package com.example.biblio;

import com.example.biblio.models.Book;
import com.example.biblio.models.Role;
import com.example.biblio.models.User;
import com.example.biblio.repositories.BookRepo;
import com.example.biblio.repositories.RoleRepo;
import com.example.biblio.services.BookService;
import com.example.biblio.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DatabaseImpl {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private BookService bookService;


    @PostConstruct
    public void init(){

        // dodawanie roli uzytkownikow
        Role roleAdmin = new Role();
        roleAdmin.setRole("ADMIN");

        Role roleUser = new Role();
        roleUser.setRole("USER");



        if(roleRepo.findByRole("ADMIN")==null) {

            roleRepo.save(roleAdmin);
            roleRepo.save(roleUser);
            System.out.println("Dodano role do bazy");
        }
        else {
            System.out.println("Istnieja role");
        }
        
        User admin = new User();
        admin.setEmail("user@user.pl");
        admin.setFirstname("user");
        admin.setLastname("user");
        admin.setPassword("qwerty");
        admin.setActive(1);

        User user1 = new User();
        user1.setEmail("user1");
        user1.setFirstname("user1");
        user1.setLastname("user1");
        user1.setPassword("12345");
        user1.setActive(1);

        User user2 = new User();
        user2.setEmail("user2");
        user2.setFirstname("user2");
        user2.setLastname("user2");
        user2.setPassword("12345");
        user2.setActive(1);

        Book book1 = new Book();
        book1.setTytul("Pustyni i w puszczy");
        book1.setAutor("Henryk sienkiewicz");
        book1.setData("24-06-1900");
        book1.setUser(user1);
        book1.setOpis("Wspaniała ksiązka");

        Book book2 = new Book();
        book2.setTytul("Harry Potter i Komnata tajemnic");
        book2.setAutor("J.K Rowling");
        book2.setData("24-06-2001");
        book2.setUser(user2);
        book2.setOpis("Wspaniała ksiązka");

        userService.saveAdmin(admin);
        userService.saveUser(user1);
        userService.saveUser(user2);
        bookService.saveBook(book1,user1.getId());
        bookService.saveBook(book2,user2.getId());
    }
}
