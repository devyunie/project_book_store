package com.book.book_store.controller.books;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.book.book_store.dto.response.books.GetBooksListResponseDto;
import com.book.book_store.service.books.BookService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/books")
@RequiredArgsConstructor
public class BooksController {
    private final BookService bookService;

    @GetMapping(value = {"/",""})
    public ResponseEntity <? super GetBooksListResponseDto> getBooksList(
        @RequestParam("categoryNumber") Integer CategoryNumber,
        @RequestParam("orderSet") String orderSet
    ){
        ResponseEntity<? super GetBooksListResponseDto> response = bookService.getBooksList(CategoryNumber,orderSet);
        return response;
    }
}
