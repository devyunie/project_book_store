package com.book.book_store.entity.reviews;

import com.book.book_store.dto.request.PostReviewRequestDto;

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
@Entity(name = "reviews")
@Table(name = "reviews")
public class ReviewsEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reviewNumber;
    private Integer bookNumber;
    private String userId;
    private String rating;
    private String comment;

    public ReviewsEntity(Integer bookNumber, String userId, PostReviewRequestDto dto){
        this.bookNumber = bookNumber;
        this.userId = userId;
        this.rating = dto.getRating();
        this.comment = dto.getComment();
    }

}
