package com.klef.service;

import java.util.List;
import com.klef.entity.Book;

public interface BookService {
    Book addBook(Book book);
    List<Book> getAllBooks();
    Book getBookById(Long id);
    Book updateBook(Book book);
    void deleteBookById(Long id);
}
