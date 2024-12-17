package com.example.proyecto_biblioteca.service;

import com.example.proyecto_biblioteca.model.Book;
import com.example.proyecto_biblioteca.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    // Encontrar libros
    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public List<Book> findBooksByTitle(String title) {
        return bookRepository.findByTitleContainingIgnoreCase(title);
    }

    public List<Book> findBooksByAuthor(String author) {
        return bookRepository.findByAuthorContainingIgnoreCase(author);
    }

    public List<Book> findBooksByGenre(String genre) {
        return bookRepository.findByGenreContainingIgnoreCase(genre);
    }

    public Optional<Book> findBook(int id) {
        return bookRepository.findById(id);
    }

    // Agregar libros
    public List<Book> addBooks(List<Book> newBooks) {
        return bookRepository.saveAll(newBooks);
    }

    // Actualizar libros
    public Book updatedBook(int id, Book updatedBook) {
        Optional<Book> foundBook = bookRepository.findById(id);

        if (foundBook.isPresent()) {
            Book existingBook = foundBook.get();

            // Actualizar campos
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());

            return bookRepository.save(existingBook);
        }

        // Enviar mensaje si no se encuentra el libro
        throw new RuntimeException("Book not found with id: " + id);
    }

    // Eliminar libros
    public void deleteBooksById(List<Integer> ids) {
        bookRepository.deleteAllById(ids);
    }
}
