package com.book.book_store.repository.orders;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.orders.OrdersEntity;

@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
    @Query(value = 
    "SELECT DISTINCT oi.book_number " +
    "FROM order_items oi JOIN orders o ON o.order_number = oi.order_number " +
    "WHERE o.user_id = :user_id " + 
    "AND oi.book_number =: book_number",
    nativeQuery = true)
    boolean hasPurchasedBook(@Param("user_id") String user_id, @Param("book_number") Integer book_number);

    OrdersEntity findByOrderNumber(Integer orderNumber);

    List<OrdersEntity> findAllByStatus(String status);
}
