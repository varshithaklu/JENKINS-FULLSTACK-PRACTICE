package com.klef.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import com.klef.entity.Book;
import com.klef.service.BookService;

@RestController
@RequestMapping("/bookapi")
@CrossOrigin(origins = "*")
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping("/")
    public String home() {
        return "Book Management API is running...";
    }

    // ✅ Add Book
    @PostMapping("/add")
    public ResponseEntity<?> addBook(@RequestBody Book book) {
        try {
            Book savedBook = bookService.addBook(book);
            return new ResponseEntity<>(savedBook, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error saving book: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // ✅ Get All Books
    @GetMapping("/all")
    public ResponseEntity<List<Book>> getAllBooks() {
        List<Book> books = bookService.getAllBooks();
        return new ResponseEntity<>(books, HttpStatus.OK);
    }

    // ✅ Get Book by ID
    @GetMapping("/get/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            return new ResponseEntity<>(book, HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Book with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // ✅ Update Book
    @PutMapping("/update")
    public ResponseEntity<?> updateBook(@RequestBody Book book) {
        Book existing = bookService.getBookById(book.getId());
        if (existing != null) {
            try {
                Book updatedBook = bookService.updateBook(book);
                return new ResponseEntity<>(updatedBook, HttpStatus.OK);
            } catch (Exception e) {
                e.printStackTrace();
                return new ResponseEntity<>("Error updating book: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity<>("Cannot update. Book with ID " + book.getId() + " not found.", HttpStatus.NOT_FOUND);
        }
    }

    // ✅ Delete Book
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
        Book existing = bookService.getBookById(id);
        if (existing != null) {
            bookService.deleteBookById(id);
            return new ResponseEntity<>("Book with ID " + id + " deleted successfully.", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Cannot delete. Book with ID " + id + " not found.", HttpStatus.NOT_FOUND);
        }
    }
}
