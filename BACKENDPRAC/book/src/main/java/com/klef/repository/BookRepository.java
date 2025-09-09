package com.klef.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.klef.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByIsbn(String isbn);
    Book findByTitle(String title);
}
