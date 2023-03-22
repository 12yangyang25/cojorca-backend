package com.example.cojorca.service;
import com.example.cojorca.domain.User;
import com.example.cojorca.repository.UserRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Transactional
public class UserService {

    private final UserRepository userRepository;
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void registerUser(User user){
        isduplicated(user);
        userRepository.save(user);
    }
    private void isduplicated(User user) {
        userRepository.findByLoginId(user.getLoginId())
                .ifPresent(m -> { //null이 아니라 값이 있으면 중괄호 안의 로직이 실행, Optional 메소드
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    public Optional<User> findUser(Long id){
        return userRepository.findById(id);
    }

    public List<User> findAllUsers(){
        return userRepository.findAll();
    }

    public void removeUser(Long id){
        userRepository.deleteById(id);
    }

    public Optional<User> findByLoginId(String login){
        return userRepository.findByLoginId(login);
    }
}
