package com.book.book_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.book.book_store.dto.request.PostOrderCreateRequestDto;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.service.order.OrderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;

    @PostMapping(value={"/",""})
    public ResponseEntity<ResponseDto> postOrders(
        @RequestBody @Valid PostOrderCreateRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = orderService.CreateOrder(userId, requestBody);
        return response;
    }

    @PostMapping("/payment/{orderNumber}")
    public ResponseEntity<ResponseDto> paymentOrder(
        @PathVariable("orderNumber") Integer orderNumber,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = orderService.OrderPayment(orderNumber, userId);
        return response;
    }
}
