package com.book.book_store.dto.response.auth;

import com.book.book_store.dto.response.ResponseCode;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.ResponseMessage;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class SignInResponseDto extends ResponseDto {
    private String accessToken;
    private Integer expiration;

    private SignInResponseDto(String accessToken) {
        super(ResponseCode.SUCCESS, ResponseMessage.SUCCESS);
        this.accessToken = accessToken;
        this.expiration = 10 * 60 * 60;
    }

    public static ResponseEntity<SignInResponseDto> success(String accessToken) {
        SignInResponseDto responseBody = new SignInResponseDto(accessToken);
        return ResponseEntity.status(HttpStatus.OK).body(responseBody);
    }

}
