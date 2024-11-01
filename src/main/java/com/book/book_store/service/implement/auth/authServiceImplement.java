package com.book.book_store.service.implement.auth;

import com.book.book_store.dto.request.auth.SignInRequestDto;
import com.book.book_store.dto.request.auth.SignUpRequestDto;
import com.book.book_store.dto.response.ResponseDto;
import com.book.book_store.dto.response.auth.SignInResponseDto;
import com.book.book_store.entity.auth.UserEntity;
import com.book.book_store.provider.JwtProvider;
import com.book.book_store.repository.user.UserRepository;
import com.book.book_store.service.auth.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class authServiceImplement implements AuthService {
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    private final JwtProvider jwtProvider;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<ResponseDto> signUp(SignUpRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String email = dto.getEmail();
        String name = dto.getName();
        try{
            String encodedPassword = passwordEncoder.encode(password);
            dto.setPassword(encodedPassword);
            UserEntity userEntity = new UserEntity(dto);
            userRepository.save(userEntity);

        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return ResponseDto.success();
    }

    @Override
    public ResponseEntity<? super SignInResponseDto> signIn(SignInRequestDto dto) {
        String userId = dto.getUserId();
        String password = dto.getPassword();
        String accessToken = null;
        try{
            UserEntity userEntity = userRepository.findByUserId(userId);
            if(userEntity == null){
                return ResponseDto.signInFail();
            }
            String encodedPassword = userEntity.getPassword();
            boolean isMatched = passwordEncoder.matches(password, encodedPassword);
            if(!isMatched) return ResponseDto.signInFail();

            accessToken = jwtProvider.create(userId);
            if(accessToken == null) return ResponseDto.tokenCreateFail();


        }catch (Exception exception){
            exception.printStackTrace();
            return ResponseDto.databaseError();
        }
        return SignInResponseDto.success(accessToken);
    }
}
