package com.book.book_store.service.auth;

import com.book.book_store.dto.request.auth.SignInRequestDto;
import com.book.book_store.dto.request.auth.SignUpRequestDto;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.auth.SignInResponseDto;
import org.springframework.http.ResponseEntity;

public interface AuthService {
    ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto);
    ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto);
}
