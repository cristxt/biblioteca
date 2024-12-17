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

    public List<Book> getAll() {
        return bookRepository.findAll();
    }

    public Book addBook(Book newBook) {
        return bookRepository.save(newBook);
    }

    public void addBooks(List<Book> newBooks) {
        bookRepository.saveAll(newBooks);
    }
    public void deleteBooks(int id){
        bookRepository.deleteById(id);
    }

    public Optional<Book> findBook(int id){
        return bookRepository.findById(id);

    }
    public Book updatedBook(int id, Book updatedBook){
        //buscar producto por id
        Optional<Book> foundBook = bookRepository.findById(id);

        if(foundBook.isPresent()){
            Book existingBook =foundBook.get();

            //Actualizar los campos
            existingBook.setTitle(updatedBook.getTitle());
            existingBook.setDescription(updatedBook.getDescription());

            //guarda el producto
            return bookRepository.save(existingBook);
        }

        //Enviar mensaje al usuario
        throw new RuntimeException("Product not found with id: " +id);
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
}

