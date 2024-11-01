package com.book.book_store.service.books;

import org.springframework.http.ResponseEntity;

import com.book.book_store.dto.response.books.GetBooksListResponseDto;

public interface BookService {
    ResponseEntity<? super GetBooksListResponseDto> getBooksList(Integer categoryNumber, String orderSet);

}
