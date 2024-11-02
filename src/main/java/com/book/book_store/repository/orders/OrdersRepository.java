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

    OrdersEntity findByOrderNumber(Integer orderNumber);

    List<OrdersEntity> findAllByStatus(String status);
}
