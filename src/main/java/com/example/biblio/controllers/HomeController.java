package com.example.biblio.controllers;

import com.example.biblio.models.Book;
import com.example.biblio.models.User;
import com.example.biblio.repositories.BookRepo;
import com.example.biblio.repositories.UserRepo;
import com.example.biblio.services.BookService;
import com.example.biblio.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/*")
public class HomeController {


    @Autowired
    UserRepo userRepo;

    @Autowired
    BookRepo bookRepo;

    @Autowired
    private UserService userService;

    @Autowired
    private BookService bookService;

    private static final Logger logger = LoggerFactory.getLogger(HomeController.class);


    @GetMapping(value = "/")
    public String welcome(Model model){
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public ModelAndView registration() {
        ModelAndView modelAndView = new ModelAndView();
        User user = new User();
        modelAndView.addObject("user", user);
        modelAndView.setViewName("registration");
        return modelAndView;
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();

        if (userService.findUserByEmail(user.getEmail()) == null) {
            userService.saveUser(user);
            modelAndView.addObject("successMessage", "Rejestracja przebiegła pomyślnie, możesz się teraz zalogować");
            modelAndView.setViewName("login");
        }

        return modelAndView;
    }

    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public ModelAndView login() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login");
        return modelAndView;
    }

    @RequestMapping(value="/user/home", method = RequestMethod.GET)
    public ModelAndView home(){

        ModelAndView model = new ModelAndView();
        model.setViewName("/user/home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());

        model.addObject("user", "Cześć " +user.getEmail());

        return model;
    }

    @RequestMapping(value="/user/home", method = RequestMethod.POST)
    public ModelAndView userHome(@Valid User garb)
    {
        System.out.println(garb.getEmail());
        User user_new = userService.findUserByEmail(garb.getEmail());
        ModelAndView model = new ModelAndView();
        model.setViewName("/user/home");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User active = userService.findUserByEmail(auth.getName());
        model.addObject("user", "Cześć  " +active.getFirstname());
        return model;
    }

    @RequestMapping(value="/book", method = RequestMethod.GET)
    public ModelAndView book(){

        ModelAndView model = new ModelAndView();
        model.setViewName("/book");
        List<Book> all_books = bookRepo.findAll();
        Book book = new Book();
        model.addObject("search", book);
        model.addObject("books", all_books);

        return model;
    }

    @RequestMapping(value="/book", method = RequestMethod.POST)
    public ModelAndView bookSearch(@RequestParam("tytul")String tytul){

        ModelAndView model = new ModelAndView();
        model.setViewName("/book");

        List<Book> all_books = bookRepo.findAll();
        List<Book> new_book = new ArrayList<>();
        Book book = new Book();

        for(Book u: all_books){
            if(u.getTytul().equals(tytul.trim())){
                new_book.add(u);
            }
        }
        model.addObject("search", book);
        model.addObject("books", new_book);
        return model;
    }


    @GetMapping(value = "/user/mybook")
    public ModelAndView myBook(){
        ModelAndView model = new ModelAndView();
        model.setViewName("/user/mybook");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        List<Book> all_books = bookRepo.findAll();
        List<Book> my_post = new ArrayList<>();
        

        for(Book u : all_books){
            if(u.getUser().getId() == user.getId() ){
                System.out.println(u.getOpis());
                my_post.add(u);
            }
        }

        model.addObject("books", my_post);
        return model;
    }

    @RequestMapping(value="/user/add", method = RequestMethod.GET)
    public ModelAndView addBook(){
        ModelAndView modelAndView = new ModelAndView();
        Book book = new Book();
        modelAndView.addObject("book", book);
        modelAndView.setViewName("user/add");
        return modelAndView;
    }
    @RequestMapping(value="/user/add", method = RequestMethod.POST)
    public ModelAndView addBook(@Valid Book book) {
        ModelAndView model = new ModelAndView();
        model.setViewName("user/add");
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByEmail(auth.getName());
        book.setUser(user);
        bookService.saveBook(book,user.getId());
        model.addObject("successMessage", "Pomyślnie dodano książkę");
        return myBook();
    }


    @RequestMapping(value="/user/delete", method = RequestMethod.GET)
    public ModelAndView deleteBook(@RequestParam(value = "id") int id){

        Book book = bookRepo.findBookById(id);
        bookRepo.delete(book);
        return myBook();
    }

    @RequestMapping(value="/user/book", method = RequestMethod.GET)
    public ModelAndView book1(){

        ModelAndView model = new ModelAndView();
        model.setViewName("/user/book");
        List<Book> all_books = bookRepo.findAll();
        Book book = new Book();
        model.addObject("search", book);
        model.addObject("books", all_books);
        return model;
    }

    @RequestMapping(value="/user/book", method = RequestMethod.POST)
    public ModelAndView book_search1(@RequestParam("tytul")String tytul){

        ModelAndView model = new ModelAndView();
        model.setViewName("/user/book");

        List<Book> all_books = bookRepo.findAll();
        List<Book> new_book = new ArrayList<>();
        Book book = new Book();

        for(Book u: all_books){
            if(u.getTytul().equals(tytul.trim())){
                new_book.add(u);
            }
        }
        model.addObject("search", book);
        model.addObject("books", new_book);
        return model;
    }
}