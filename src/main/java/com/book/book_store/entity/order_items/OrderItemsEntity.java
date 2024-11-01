package com.book.book_store.entity.order_items;

import com.book.book_store.dto.request.orderItems.OrderItemsRequestDto;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orderItems")
@Table(name = "order_items")
public class OrderItemsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderItemNumber;
    private Integer orderNumber;
    private Integer bookNumber;
    private Integer quantity;
    private Integer pricePerUnit;

    public OrderItemsEntity (OrderItemsRequestDto dto)
    {
        this.bookNumber = dto.getBookNumber();
        this.quantity = dto.getQuantity();
        this.pricePerUnit = dto.getPricePerUnit();
    }
}
