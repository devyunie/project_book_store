package com.book.book_store.service.implement.books;

import com.book.book_store.common.Books.BooksList;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.books.GetBooksListResponseDto;
import com.book.book_store.entity.books.BooksEntity;
import com.book.book_store.repository.books.BooksRepository;
import com.book.book_store.repository.categories.CategoriesRepository;
import com.book.book_store.repository.user.UserRepository;
import com.book.book_store.service.books.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BooksServiceImplement implements BookService {
    private final UserRepository userRepository;
    private final BooksRepository booksRepository;
    private final CategoriesRepository categoriesRepository;

    @Override
    public ResponseEntity<? super GetBooksListResponseDto> getBooksList(Integer categoryNumber, String orderSet) {
        List<BooksEntity> booksEntities = new ArrayList<>();
        try{
            booksEntities = booksRepository.getBookList(categoryNumber, orderSet);
        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBooksListResponseDto.success(booksEntities);
    }
}
