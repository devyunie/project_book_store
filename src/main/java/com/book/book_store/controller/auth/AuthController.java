package com.book.book_store.controller.auth;

import com.book.book_store.dto.request.auth.SignInRequestDto;
import com.book.book_store.dto.request.auth.SignUpRequestDto;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.auth.SignInResponseDto;
import com.book.book_store.service.auth.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<ResponseDto> signUp(
            @RequestBody @Valid SignUpRequestDto requestBody
    ){
        ResponseEntity<ResponseDto> response = authService.signUp(requestBody);
        return response;
    }

    @PostMapping("/login")
    public ResponseEntity<? super SignInResponseDto> login(
            @RequestBody @Valid SignInRequestDto requestBody
    ){
        ResponseEntity<? super SignInResponseDto> response = authService.signIn(requestBody);
        return response;
    }
}
