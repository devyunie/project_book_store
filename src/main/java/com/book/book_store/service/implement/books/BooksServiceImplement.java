package com.book.book_store.service.implement.books;

import com.book.book_store.common.Books.BooksList;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.books.GetBooksListResponseDto;
import com.book.book_store.entity.auth.UserEntity;
import com.book.book_store.entity.books.BooksEntity;
import com.book.book_store.entity.categories.CategoriesEntity;
import com.book.book_store.entity.order_items.OrderItemsEntity;
import com.book.book_store.entity.order_items.resultSet.BestSellerOrdersListResultSet;
import com.book.book_store.entity.orders.resultSet.OrdersResultSet;
import com.book.book_store.repository.books.BooksRepository;
import com.book.book_store.repository.categories.CategoriesRepository;
import com.book.book_store.repository.order_items.OrderItemsRepository;
import com.book.book_store.repository.orders.OrdersRepository;
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
    private final OrderItemsRepository orderItemsRepository;
    private final OrdersRepository ordersRepository;

    @Override
    public ResponseEntity<? super GetBooksListResponseDto> getBooksList(Integer categoryNumber, String orderSet) {
        List<BooksList> booksLists = new ArrayList<>();
        List<BooksEntity> booksEntities = new ArrayList<>();
        try {
            booksEntities = booksRepository.getBookList(categoryNumber, orderSet);
            for(BooksEntity booksEntity : booksEntities) {
                CategoriesEntity categoriesEntity = categoriesRepository.findByCategoriesNumber(categoryNumber);
                BooksList booksList = new BooksList(booksEntity, categoriesEntity);
               booksLists.add(booksList);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBooksListResponseDto.success(booksLists);
    }

    @Override
    public ResponseEntity<? super GetBooksListResponseDto> getBooksDiscountList() {
        List<BooksEntity> booksEntityList = new ArrayList<>();
        List<BooksList> booksLists = new ArrayList<>();
        try {
            booksEntityList = booksRepository.getBookDisCountList();
            for(BooksEntity booksEntity : booksEntityList) {
                CategoriesEntity categoriesEntity = categoriesRepository.findByCategoriesNumber(booksEntity.getCategoryNumber());
                BooksList booksList = new BooksList(booksEntity, categoriesEntity);
                booksLists.add(booksList);
            }
            if (booksEntityList.isEmpty()) return ResponseDto.databaseError();

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBooksListResponseDto.success(booksLists);
    }

    @Override
    public ResponseEntity<? super GetBooksListResponseDto> getBestSellerBookList() {
        List<BestSellerOrdersListResultSet> resultSetList = orderItemsRepository.getBestSellerBookList();
        List<BooksEntity> booksEntityList = new ArrayList<>();
        List<BooksList> booksLists = new ArrayList<>();
        try {
            for (BestSellerOrdersListResultSet resultSet : resultSetList) {
                Integer bookNumber = resultSet.getBookNumber();
                BooksEntity booksEntity = booksRepository.findByBookNumber(bookNumber);
                CategoriesEntity categoriesEntity = categoriesRepository.findByCategoriesNumber(booksEntity.getCategoryNumber());
                BooksList booksList = new BooksList(booksEntity, categoriesEntity);
                booksLists.add(booksList);
            }

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBooksListResponseDto.success(booksLists);
    }

    @Override
    public ResponseEntity<? super GetBooksListResponseDto> getRecentlyBookList() {
        List<BooksList> booksLists = new ArrayList<>();
        List<BooksEntity> booksEntities = new ArrayList<>();
        try {
            booksEntities = booksRepository.getRecentlyBookList();
            for(BooksEntity booksEntity : booksEntities) {
                Integer bookNumber = booksEntity.getBookNumber();
                CategoriesEntity categoriesEntity = categoriesRepository.findByCategoriesNumber(booksEntity.getCategoryNumber());
                BooksList booksList = new BooksList(booksEntity, categoriesEntity);
                booksLists.add(booksList);
            }
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBooksListResponseDto.success(booksLists);
    }

    @Override
    public ResponseEntity<? super GetBooksListResponseDto> getBooksCategoryBestSellerList(String userId) {
        List<BooksList> booksLists = new ArrayList<>();
        try {
            boolean isUser = userRepository.existsById(userId);
            if(!isUser) return  ResponseDto.noExistUserId();

            OrdersResultSet resultSetList = ordersRepository.PurchasedBookList(userId);
            List<BooksEntity> booksEntityList = new ArrayList<>();
            Integer cateoryNumber = resultSetList.getCateoryNumber();
            List<BooksEntity> booksEntities = booksRepository.findByCategoryNumber(cateoryNumber);
            for(BooksEntity booksEntity : booksEntities) {
                Integer bookNumber = booksEntity.getBookNumber();
                CategoriesEntity categoriesEntity = categoriesRepository.findByCategoriesNumber(booksEntity.getCategoryNumber());
                BooksList booksList = new BooksList(booksEntity, categoriesEntity);
                booksLists.add(booksList);
            }
        }catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return GetBooksListResponseDto.success(booksLists);
    }
}