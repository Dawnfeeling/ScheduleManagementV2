package com.example.schedulemanagementv2.service;

import com.example.schedulemanagementv2.config.PasswordEncoder;
import com.example.schedulemanagementv2.dto.LoginResponseDto;
import com.example.schedulemanagementv2.dto.SignUpResponseDto;
import com.example.schedulemanagementv2.dto.UserResponseDto;
import com.example.schedulemanagementv2.entity.User;
import com.example.schedulemanagementv2.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    //로그인
    public LoginResponseDto login(String email, String password) {

        Long index = userRepository.findIdByEmail(email);

        Optional<User> optionalUser = userRepository.findById(index);

        //비밀번호 검증
        if(passwordEncoder.matches(password, optionalUser.get().getPassword())) {
            return new LoginResponseDto(index);
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Wrong password");
        }
    }

    //유저 생성
    public SignUpResponseDto signUp(String username, String email, String password){

        User user = new User(username, email, passwordEncoder.encode(password));

        User savedUser = userRepository.save(user);

        return new SignUpResponseDto(savedUser.getId(), savedUser.getUsername(), savedUser.getEmail());
    }

    //유저 조회
    public UserResponseDto findById(Long id) {

        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Does not exist id = " + id);
        }

        User findUser = optionalUser.get();

        return new UserResponseDto(findUser.getUsername(), findUser.getEmail());
    }

    //유저 비밀번호 수정
    @Transactional
    public void updatePassword(Long id, String oldPassword, String newPassword) {

        User findUser = userRepository.findByIdOrElseThrow(id);

        if (!passwordEncoder.matches(oldPassword, findUser.getPassword())) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "비밀번호가 일치하지 않습니다.");
        }

        findUser.updatePassword(passwordEncoder.encode(newPassword));
    }

    public void delete(Long id){
        userRepository.deleteById(id);
    }
}
