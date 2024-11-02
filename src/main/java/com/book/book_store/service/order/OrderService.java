package com.book.book_store.service.order;

import org.springframework.http.ResponseEntity;

import com.book.book_store.dto.request.PostOrderCreateRequestDto;
import com.book.book_store.dto.response.ResponseDto;

public interface OrderService {
    ResponseEntity<ResponseDto> CreateOrder(String userId, PostOrderCreateRequestDto create);

    ResponseEntity<ResponseDto> OrderPayment(Integer orderNumber, String userId);

    ResponseEntity<ResponseDto> OrderStatusScheduler();

}
