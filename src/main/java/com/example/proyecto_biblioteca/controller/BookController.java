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

    // Encontrar libros
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) {
        Optional<Book> foundBook = bookService.findBook(id);

        if (foundBook.isPresent()) {
            return new ResponseEntity<>(foundBook.get(), HttpStatus.OK);
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

    // Crear nuevos libros
    @PostMapping
    public ResponseEntity<List<Book>> createBooks(@RequestBody List<Book> newBooks) {
        List<Book> savedBooks = bookService.addBooks(newBooks);
        return new ResponseEntity<>(savedBooks, HttpStatus.CREATED);
    }

    // Eliminar libros
    @DeleteMapping
    public ResponseEntity<String> deleteBooksById(@RequestBody List<Integer> ids) {
        try {
            bookService.deleteBooksById(ids);
            return new ResponseEntity<>("Libro(s) eliminado(s) correctamente", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error al eliminar los libros", HttpStatus.BAD_REQUEST);
        }
    }

    // Actualizar libros
    @PutMapping("/{id}")
    //he cambiado ResponseEntity<Book> por ResponseEntity<Object> para poder devolver tanto un objeto Book en el caso de éxito, como un mensaje String en caso de error, sin causar conflicto de tipos.
    public ResponseEntity<Object> updateBook(@PathVariable int id, @RequestBody Book updatedBook) {
        try {
            Optional<Object> updated = bookService.updatedBook(id, updatedBook);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (RuntimeException e) {

            String errorMessage = "Libro con ID " + id + " no encontrado";
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }



}
