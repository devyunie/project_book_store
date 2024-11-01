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
    private GetBooksListResponseDto(List<BooksEntity> booksEntities) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.booksList = BooksList.getList(booksEntities);
    }
    public static ResponseEntity<GetBooksListResponseDto> success(List<BooksEntity> booksEntities) {
        GetBooksListResponseDto responseBody = new GetBooksListResponseDto(booksEntities);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }
}
