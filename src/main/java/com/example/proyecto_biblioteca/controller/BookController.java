package com.example.proyecto_biblioteca.controller;

import com.example.proyecto_biblioteca.model.Book;
import com.example.proyecto_biblioteca.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    //Encontrar libros
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id){
        Optional<Book> foundBook=bookService.findBook(id);

        if(foundBook.isPresent()){
            return new ResponseEntity<>(foundBook.get(), HttpStatus.FOUND);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/title")
    public List<Book> searchBooksByTitle(@RequestParam String title) {
        return bookService.findBooksByTitle(title);
    }

    @GetMapping("/author")
    public List<Book> searchBooksByAuthor(@RequestParam String author) {
        return bookService.findBooksByAuthor(author);
    }

    @GetMapping("/genre")
    public List<Book> searchBooksByGenre(@RequestParam String genre) {
        return bookService.findBooksByGenre(genre);
    }


    //Crear un nuevo libro
    @PostMapping("/books")
    public ResponseEntity<Book> createBook(@RequestBody Book newBook) {
        Book savedBook = bookService.addBook(newBook);
        return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
    }
    //eliminar libros
    @DeleteMapping("/books/{id}")
    public void deleteBooks(@PathVariable int id){
        bookService.deleteBooks(id);
    }



    @PutMapping("/books/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        Optional<Book> foundBook = bookService.findBook(id);

        if (foundBook.isPresent()) {
            Book updated = bookService.updatedBook(id, updatedBook);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}


