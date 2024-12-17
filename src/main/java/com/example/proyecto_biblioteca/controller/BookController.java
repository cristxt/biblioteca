package com.example.proyecto_biblioteca.controller;

import com.example.proyecto_biblioteca.model.Book;
import com.example.proyecto_biblioteca.service.BookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // Encontrar libros
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/without-description")
    public List<Book> getBooksWithoutDescription() {
        return bookService.getBooksWithoutDescription();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) {
        return bookService.findBook(id)
                .map(book -> new ResponseEntity<>(book, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
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

    // Crear nuevos libros
    @PostMapping
    public ResponseEntity<List<Book>> createBooks(@RequestBody List<Book> newBooks) {
        List<Book> savedBooks = bookService.addBooks(newBooks);
        return new ResponseEntity<>(savedBooks, HttpStatus.CREATED);
    }

    // Actualizar libros
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        return bookService.findBook(id)
                .map(book -> {
                    Book updated = (Book) bookService.updatedBook(id, updatedBook).orElseThrow();
                    return new ResponseEntity<>(updated, HttpStatus.OK);
                })
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    // Eliminar libros
    @DeleteMapping
    public ResponseEntity<Void> deleteBooksById(@RequestBody List<Integer> ids) {
        bookService.deleteBooksById(ids);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
