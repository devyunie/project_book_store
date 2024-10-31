package com.book.book_store.repository.reviews;

import com.book.book_store.entity.reviews.ReviewsEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewsRepository extends JpaRepository<ReviewsEntity,Integer> {
}
