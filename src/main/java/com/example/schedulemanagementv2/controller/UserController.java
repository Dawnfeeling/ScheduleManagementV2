package com.example.schedulemanagementv2.controller;

import com.example.schedulemanagementv2.common.Const;
import com.example.schedulemanagementv2.dto.LoginRequestDto;
import com.example.schedulemanagementv2.dto.LoginResponseDto;
import com.example.schedulemanagementv2.dto.SignUpRequestDto;
import com.example.schedulemanagementv2.dto.SignUpResponseDto;
import com.example.schedulemanagementv2.dto.UpdatePasswordRequestDto;
import com.example.schedulemanagementv2.dto.UserResponseDto;
import com.example.schedulemanagementv2.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDto> login(@RequestBody LoginRequestDto dto, HttpServletRequest request) {
        //로그인 유저 조회
        LoginResponseDto responseDto = userService.login(dto.getEmail(), dto.getPassword());
        Long userId = responseDto.getId();

        //로그인 실패
        if(userId == null){
            return new ResponseEntity<>(responseDto, HttpStatus.UNAUTHORIZED);
        }

        //로그인 성공
        HttpSession session = request.getSession();

        UserResponseDto loginUser = userService.findById(userId);

        session.setAttribute(Const.LOGIN_USER, loginUser);

        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(HttpServletRequest request) {

        HttpSession session = request.getSession(false);

        if(session != null){
            session.invalidate();
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/signup")
    public ResponseEntity<SignUpResponseDto> signUp(@RequestBody SignUpRequestDto requestDto) {

        SignUpResponseDto signUpResponseDto =
                userService.signUp(
                        requestDto.getUsername(),
                        requestDto.getEmail(),
                        requestDto.getPassword()
                );

        return new ResponseEntity<>(signUpResponseDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserResponseDto> findById(@PathVariable Long id) {

        UserResponseDto userResponseDto = userService.findById(id);

        return new ResponseEntity<>(userResponseDto, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> updatePassword(
            @PathVariable Long id,
            @RequestBody UpdatePasswordRequestDto requestDto
    ) {

        userService.updatePassword(id, requestDto.getOldPassword(), requestDto.getNewPassword());

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        userService.delete(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
