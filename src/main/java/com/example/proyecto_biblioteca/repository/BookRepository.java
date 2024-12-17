package com.example.proyecto_biblioteca.repository;

import com.example.proyecto_biblioteca.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
