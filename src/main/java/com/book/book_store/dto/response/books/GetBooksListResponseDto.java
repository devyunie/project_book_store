package com.book.book_store.dto.response.books;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.book.book_store.common.Books.BooksList;
import com.book.book_store.dto.response.ResponseCode;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.ResponseMessage;
import com.book.book_store.entity.books.BooksEntity;

import lombok.Getter;

@Getter
public class GetBooksListResponseDto extends ResponseDto {
    private List<BooksList> booksList;
    private GetBooksListResponseDto(List<BooksList> booksLists) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.booksList = booksLists;
    }
    public static ResponseEntity<GetBooksListResponseDto> success(List<BooksList> booksLists) {
        GetBooksListResponseDto responseBody = new GetBooksListResponseDto(booksLists);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
