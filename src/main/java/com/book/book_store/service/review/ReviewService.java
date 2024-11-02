package com.book.book_store.service.review;

import org.springframework.http.ResponseEntity;

import com.book.book_store.dto.request.Review.PostReviewRequestDto;
import com.book.book_store.dto.response.ResponseDto;

public interface  ReviewService {
    ResponseEntity<ResponseDto> createReviews(PostReviewRequestDto dto, String userId, Integer bookNumber);
}
