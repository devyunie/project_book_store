package com.book.book_store.dto.request.orders;

import java.util.List;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor // 기본 생성자 추가
@AllArgsConstructor
public class PostOrderCreateRequestDto {
   @NotNull(message = "주문 항목 목록은 비어 있을 수 없습니다.")
   private List<com.book.book_store.dto.request.orders.PostOrderItemsRequestDto> items; // 각 항목에 대한 유효성 검증
}
