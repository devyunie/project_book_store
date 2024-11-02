package com.book.book_store.repository.books;

import java.util.List;

import com.book.book_store.common.Books.BooksList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.books.BooksEntity;

@Repository
public interface BooksRepository extends JpaRepository<BooksEntity, Integer> {
    boolean existsByBookNumber(Integer bookNumber);
    List<BooksEntity> findByCategoryNumber(Integer categoryNumber);
    BooksEntity findByBookNumber(Integer bookNumber);

    @Query(
            value =
                    "SELECT * FROM books WHERE discount_rate > 0",
            nativeQuery = true)
    List<BooksEntity> getBookDisCountList();

    @Query(
            value =
                    "SELECT * FROM books " +
                            "WHERE category_number=:categoryNumber " +
                            "ORDER By :orderSet DESC",
            nativeQuery = true)
    List<BooksEntity> getBookList(@Param("categoryNumber") Integer categoryNumber, @Param("orderSet") String orderSet);

    @Query(
    value = "SELECT * FROM Books ORDER BY registration_date DESC LIMIT 5",
            nativeQuery = true
    )
    List<BooksEntity> getRecentlyBookList();

}