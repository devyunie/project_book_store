package com.book.book_store.entity.books;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "books")
@Table(name = "books")
public class BooksEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer bookNumber;
    private String bookName;
    private String author;
    private Integer bookPrice;
    private Date registrationDate;
    private Integer discountRate;
    private Integer categoryNumber;
}
