package com.book.book_store.common.Books;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.book.book_store.entity.books.BooksEntity;

import com.book.book_store.entity.categories.CategoriesEntity;
import com.book.book_store.repository.categories.CategoriesRepository;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class BooksList {
    private Integer bookNumber;
    private String bookName;
    private String author;
    private Integer bookPrice;
    private Date registrationDate;
    private Integer discountRate;
    private String categoryName;

    public BooksList(BooksEntity booksEntity, CategoriesEntity categoriesEntity) {

        this.bookNumber = booksEntity.getBookNumber();
        this.bookName = booksEntity.getBookName();
        this.author = booksEntity.getAuthor();
        this.bookPrice = booksEntity.getBookPrice();
        this.registrationDate = booksEntity.getRegistrationDate();
        this.discountRate = booksEntity.getDiscountRate();
        this.categoryName =categoriesEntity.getName();
    }

    public static List<BooksList> getList(List<BooksEntity> booksEntities, CategoriesEntity categoriesEntity) {
        List<BooksList> booksLists = new ArrayList<>();
        for(BooksEntity booksEntity : booksEntities){
            BooksList booksList = new BooksList(booksEntity, categoriesEntity);
            booksLists.add(booksList);
        }
        return booksLists;
    }

}
