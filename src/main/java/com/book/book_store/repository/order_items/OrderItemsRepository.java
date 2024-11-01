package com.book.book_store.repository.order_items;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.order_items.OrderItemsEntity;
import com.book.book_store.entity.order_items.resultSet.BestSellerOrdersListResultSet;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Integer> {
      @Query(
        value=
        "SELECT book_number AS BookNumber, COUNT(*) AS orderCount FROM order_items " +
        "GROUP BY book_number " +
        "ORDER BY COUNT(*) DESC " +
        "LIMIT 5",
        nativeQuery = true)
    List<BestSellerOrdersListResultSet> getBestSellerBookList();
    
}
