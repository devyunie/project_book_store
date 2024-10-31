package com.book.book_store.repository.order_items;

import com.book.book_store.entity.order_items.OrderItemsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemsRepository extends JpaRepository<OrderItemsEntity, Integer> {

}
