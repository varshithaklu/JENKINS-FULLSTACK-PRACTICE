package com.klef.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "book_table")
public class Book {
    
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "book_id")
	private Long id;


    @Column(name = "book_title", nullable = false, length = 100)
    private String title;

    @Column(name = "book_author", nullable = false, length = 50)
    private String author;


    @Column(name = "book_isbn", nullable = false, unique = true, length = 20)
    private String isbn;

    @Column(name = "book_price", nullable = false)
    private double price;

    @Column(name = "book_publisher", length = 50)
    private String publisher;

    @Column(name = "book_year", length = 10)
    private String year;   // e.g. 2020, 2021, etc.

    // Getters & Setters
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

//    public String getCategory() {
//        return category;
//    }
//    public void setCategory(String category) {
//        this.category = category;
//    }

    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public double getPrice() {
        return price;
    }
    public void setPrice(double price) {
        this.price = price;
    }

    public String getPublisher() {
        return publisher;
    }
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getYear() {
        return year;
    }
    public void setYear(String year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book [id=" + id + ", title=" + title + ", author=" + author  + ", isbn=" + isbn 
                + ", price=" + price + ", publisher=" + publisher 
                + ", year=" + year + "]";
    }
}
