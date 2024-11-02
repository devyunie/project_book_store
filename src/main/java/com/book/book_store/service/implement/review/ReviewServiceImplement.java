package com.book.book_store.service.implement.review;

import com.book.book_store.entity.orders.resultSet.OrdersResultSet;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.book.book_store.dto.request.PostReviewRequestDto;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.entity.reviews.ReviewsEntity;
import com.book.book_store.repository.books.BooksRepository;
import com.book.book_store.repository.orders.OrdersRepository;
import com.book.book_store.repository.reviews.ReviewsRepository;
import com.book.book_store.repository.user.UserRepository;
import com.book.book_store.service.review.ReviewService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImplement implements ReviewService {
    private final UserRepository userRepository;
    private final OrdersRepository ordersRepository;
    private final BooksRepository booksRepository;
    private final ReviewsRepository reviewsRepository;

    @Override
    public ResponseEntity<ResponseDto> createReviews(PostReviewRequestDto dto, String userId, Integer bookNumber) {
        OrdersResultSet ordersResultSet = null;
        try {
            boolean existBook = booksRepository.existsByBookNumber(bookNumber);
            if (!existBook) return ResponseDto.noExistBook();

            boolean existUser = userRepository.existsByUserId(userId);
            if (!existUser) return ResponseDto.noExistUserId();

            ordersResultSet  = ordersRepository.hasPurchasedBook(userId, bookNumber);
            if(ordersResultSet == null) return ResponseDto.noExistPurchaseBook();
            ordersResultSet.getBookNumber();

            boolean exists = reviewsRepository.existsByBookNumberAndUserId(bookNumber, userId);
            if (exists) return ResponseDto.alredyReviewWrite();

            ReviewsEntity reviewsEntity = new ReviewsEntity(bookNumber, userId, dto);
            reviewsRepository.save(reviewsEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}
