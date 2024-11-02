package com.book.book_store.repository.orders;

import java.util.List;

import com.book.book_store.entity.orders.resultSet.OrdersResultSet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.orders.OrdersEntity;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
    @Query(value =
    "SELECT DISTINCT oi.book_number as bookNumber " +
    "FROM order_items oi JOIN orders o ON o.order_number = oi.order_number " +
    "WHERE o.user_id = :userId " +
    "AND oi.book_number =:bookNumber",
    nativeQuery = true)
    OrdersResultSet hasPurchasedBook(@Param("userId") String userId, @Param("bookNumber") Integer bookNumber);

    @Query(value =
            "SELECT b.category_number AS cateoryNumber , SUM(oi.quantity) AS totalCount " +
            "FROM order_items oi " +
            "JOIN books b ON oi.book_number  = b.book_number " +
            "JOIN orders o ON oi.order_number = o.order_number " +
            "WHERE o.user_id =:userId " +
            "GROUP BY b.category_number " +
            "ORDER BY totalCount DESC " +
            "LIMIT 1",
            nativeQuery = true)
   OrdersResultSet PurchasedBookList(@Param("userId") String userId);

    OrdersEntity findByOrderNumber(Integer orderNumber);

    List<OrdersEntity> findAllByStatus(String status);
}
