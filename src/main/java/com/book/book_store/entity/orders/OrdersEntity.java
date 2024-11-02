package com.book.book_store.entity.orders;
import java.time.LocalDate;
import java.util.Date;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "orders")
@Table(name = "orders")
public class OrdersEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer orderNumber;
    private String userId;
    private LocalDate orderDate;
    private Integer totalPrice;
    private String status;

    public OrdersEntity (String userId){
        this.userId = userId;
        this.orderDate = LocalDate.now();
        this.totalPrice = 0;
        this.status = "결제 대기";
    }
}
