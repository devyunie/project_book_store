package com.book.book_store.dto.request.Review;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class PostReviewRequestDto {
    @NotBlank
    private String rating;
    @NotBlank
    private String comment;
}
