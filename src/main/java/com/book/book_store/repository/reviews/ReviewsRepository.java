package com.book.book_store.repository.reviews;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.book.book_store.entity.reviews.ReviewsEntity;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity, Integer> {
    boolean existsByBookIdAndUserId(Integer bookNumber, String userId);
}
