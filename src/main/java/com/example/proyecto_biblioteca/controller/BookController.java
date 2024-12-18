package com.example.proyecto_biblioteca.controller;

import com.example.proyecto_biblioteca.exception.BookNotFoundException;
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
        return bookService.getAllBooks();
    }

    @GetMapping("/without-description")
    public List<Book> getBooksWithoutDescription() {
        return bookService.getBooksWithoutDescription();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> findBookById(@PathVariable int id) {
        Optional<Book> foundBook = bookService.findBook(id);
        if(foundBook.isPresent()){
            return new ResponseEntity<>(foundBook.get(),HttpStatus.NOT_FOUND);
        }
        throw new BookNotFoundException("Error al encontrar el libro con el id " + id );
    }

    @GetMapping("/title")
    public ResponseEntity<Book> searchBookByTitle(@RequestParam String title) {
        Optional<Book> foundBook = bookService.findByTitleIgnoreCase(title);

        if (foundBook.isPresent()) {
            return new ResponseEntity<>(foundBook.get(), HttpStatus.OK);
        }

        throw new BookNotFoundException("Error al encontrar el libro con el título: " + title);
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
    public ResponseEntity<String> deleteBooksById(@RequestBody List<Integer> ids) {
        bookService.deleteBooksById(ids);
        return new ResponseEntity<>("¡Éxito! libro(s) eliminado(s) con id:" +ids , HttpStatus.OK);
    }
}
