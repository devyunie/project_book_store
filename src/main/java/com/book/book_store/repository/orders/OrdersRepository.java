package com.book.book_store.repository.orders;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.orders.OrdersEntity;
@Repository
public interface OrdersRepository extends JpaRepository<OrdersEntity, Integer> {
    
}
