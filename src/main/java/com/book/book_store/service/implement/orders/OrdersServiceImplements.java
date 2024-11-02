package com.book.book_store.service.implement.orders;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import com.book.book_store.service.order.OrderService;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.book.book_store.dto.request.PostOrderCreateRequestDto;
import com.book.book_store.dto.request.PostOrderItemsRequestDto;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.entity.books.BooksEntity;
import com.book.book_store.entity.order_items.OrderItemsEntity;
import com.book.book_store.entity.orders.OrdersEntity;
import com.book.book_store.repository.books.BooksRepository;
import com.book.book_store.repository.order_items.OrderItemsRepository;
import com.book.book_store.repository.orders.OrdersRepository;
import com.book.book_store.repository.user.UserRepository;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrdersServiceImplements implements OrderService {
    private final UserRepository userRepository;
    private final BooksRepository booksRepository;
    private final OrdersRepository ordersRepository;
    private final OrderItemsRepository orderItemsRepository;

    @Override
    @Transactional
    public ResponseEntity<ResponseDto> CreateOrder(String userId, PostOrderCreateRequestDto create) {
        try {
            // 사용자 확인
            boolean existedUser = userRepository.existsByUserId(userId);
            if (!existedUser)
                return ResponseDto.noExistUserId();

            // 주문 엔티티 생성 및 저장
            OrdersEntity ordersEntity = new OrdersEntity(userId);
            ordersRepository.save(ordersEntity);

            // 주문 번호 가져오기
            Integer orderNumber = ordersEntity.getOrderNumber();
            Integer total = 0;

            // 주문 항목 처리
            for (PostOrderItemsRequestDto orderItemsRequestDto : create.getItems()) {
                OrderItemsEntity orderItemsEntity = new OrderItemsEntity(orderItemsRequestDto, orderNumber);

                BooksEntity booksEntity = booksRepository.findByBookNumber(orderItemsRequestDto.getBookNumber());
                if (booksEntity == null) return ResponseDto.noExistBook();

                Integer pricePerUnit = booksEntity.getBookPrice();
                double discout = booksEntity.getDiscountRate() / 100;
                if(discout > 0 ) pricePerUnit = (int) (pricePerUnit - (pricePerUnit * discout));

                orderItemsEntity.setPricePerUnit(pricePerUnit);
                Integer itemTotalPrice = pricePerUnit * orderItemsRequestDto.getQuantity();
                total += itemTotalPrice;

                // 주문 항목 저장
                orderItemsRepository.save(orderItemsEntity);
            }

            ordersEntity.setTotalPrice(total);
            ordersRepository.save(ordersEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    @Scheduled(cron = "0 0 0 * * ?")
    public ResponseEntity<ResponseDto> OrderStatusScheduler() {
        try {
            List<OrdersEntity> orders = ordersRepository.findAllByStatus("결제 완료");
            LocalDate today = LocalDate.now();
            System.out.println(orders);

            for (OrdersEntity order : orders) {
                LocalDate orderDate = order.getOrderDate();
                Integer daysBetween =(int) ChronoUnit.DAYS.between(orderDate, today);
                System.out.println(daysBetween);
                if (daysBetween == 1) {
                    order.setStatus("배송중");
                } else if (daysBetween == 2) {
                    order.setStatus("발송완료");
                }
            }
        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<ResponseDto> OrderPayment(Integer orderNumber, String userId) {
        try {
            OrdersEntity ordersEntity = ordersRepository.findByOrderNumber(orderNumber);
            String status = ordersEntity.getStatus();
            String user = ordersEntity.getUserId();
            boolean isUser = user.equals(userId);
            if (!isUser)
                return ResponseDto.noPermission();
            if (status.equals("결제 대기"))
                ordersEntity.setStatus("결제 완료");
            ordersRepository.save(ordersEntity);

        } catch (Exception exception) {
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }
}