package com.book.book_store.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.book_store.dto.request.PostReviewRequestDto;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.books.GetBooksListResponseDto;
import com.book.book_store.service.books.BookService;
import com.book.book_store.service.review.ReviewService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;
    private final ReviewService reviewService;
    @GetMapping(value = {"/",""})
    public ResponseEntity <? super GetBooksListResponseDto> getBooksList(
        @RequestParam("categoryNumber") Integer CategoryNumber,
        @RequestParam("orderSet") String orderSet
    ){
        ResponseEntity<? super GetBooksListResponseDto> response = bookService.getBooksList(CategoryNumber,orderSet);
        return response;
    }

    @PostMapping("{bookNumber}/reviews")
    public ResponseEntity<ResponseDto> createReview(
        @PathVariable("bookNumber") Integer bookNumber,
        @RequestBody PostReviewRequestDto requestBody,
        @AuthenticationPrincipal String userId
    ){
        ResponseEntity<ResponseDto> response = reviewService.createReviews(requestBody, userId, bookNumber);
        return response;
    }
}
