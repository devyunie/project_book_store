package com.book.book_store.common.Books;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.book.book_store.entity.books.BooksEntity;

import lombok.Getter;

@Getter
public class BooksList {
    private Integer bookNumber;
    private String bookName;
    private String author;
    private Integer bookPrice;
    private Date registrationDate;
    private Integer discountRate;
    private Integer categoryName;

    public BooksList(BooksEntity booksEntity) {
        this.bookNumber = booksEntity.getBookNumber();
        this.bookName = booksEntity.getBookName();
        this.author = booksEntity.getAuthor();
        this.bookPrice = booksEntity.getBookPrice();
        this.registrationDate = booksEntity.getRegistrationDate();
        this.discountRate = booksEntity.getDiscountRate();
        this.categoryName = booksEntity.getCategoryNumber();
    }

    public static List<BooksList> getList(List<BooksEntity> booksEntities) {
        List<BooksList> booksLists = new ArrayList<>();
        for(BooksEntity booksEntity : booksEntities){
            BooksList booksList = new BooksList(booksEntity);
            booksLists.add(booksList);
        }
        return booksLists;
    }

}
