package com.book.book_store.dto.request.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SignUpRequestDto {
    @NotBlank
    private String userId;
    @NotBlank
    private String name;
    @NotBlank
    private String email;
    @NotBlank
    @Pattern(regexp = "^(?=.*[a-zA-z])(?=.*[0-9]).{8,13}$")
    private String password;
}
