package com.book.book_store.common.orders;

import com.book.book_store.entity.orders.OrdersEntity;
import lombok.Getter;

import java.util.List;

@Getter
public class Orders {
    private Integer orderNumber;
    private String userId;
    private List<OrdersEntity> ordersEntityList;
    private Integer totalPrice;
    private String status;

    public Orders (OrdersEntity ordersEntity, List<OrdersEntity> ordersEntities) {

    }
}
