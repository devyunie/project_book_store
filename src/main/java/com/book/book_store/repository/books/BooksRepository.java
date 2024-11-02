package com.book.book_store.repository.books;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.books.BooksEntity;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Integer> {
    boolean existsByBookNumber (Integer bookNumber);
    BooksEntity findByBookNumber (Integer bookNumber);
    @Query(
    value=
    "SELECT * FROM books " +
    "WHERE category_number=:categoryNumber " +
    "ORDER By :orderSet DESC",
            nativeQuery = true)
    List<BooksEntity> getBookList(@Param("categoryNumber") Integer categoryNumber, @Param("orderSet") String orderSet );

}