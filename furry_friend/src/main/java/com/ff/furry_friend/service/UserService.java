package com.ff.furry_friend.service;

import com.ff.furry_friend.dto.UserForm;
import com.ff.furry_friend.entity.user;
import com.ff.furry_friend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Transactional
@Service
public class UserService {

    @Autowired
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    /**
     * 회원가입
     */
    public int create(user user) {
        System.out.println(user);
        validateDuplicateMember(user); //중복 회원 검증
        userRepository.save(user);
        return user.getCreate_id();
    }

    public boolean Login(UserForm form) {
        Optional<user> result = userRepository.findById(form.getId());
        if (!result.get().getId().equals(form.getId())) {
            return false;
        }
        if (!result.get().getPw().equals(form.getPw())) {
            return false;
        }
        return true;
    }

    public String findById(UserForm form){
        Optional<user> result = userRepository.findById(form.getId());

        return result.get().getId();
    }

    public String findByPw(UserForm form){
        Optional<user> result = userRepository.findById(form.getId());

        return result.get().getPw();
    }

    public void validateDuplicateMember(user user) {
        userRepository.findById(user.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원 아이디입니다.");
                });
    }

    private void findId(user user) {
        userRepository.findById(user.getId())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 전체 회원 조회
     */
    public Optional<user> findUsers(String id) {
        return userRepository.findById(id);
    }

    public Optional<user> findOne(int id) {
        return userRepository.findByCreate_Id(id);
    }

    public int nameCheck(String id) {
        System.out.println(userRepository.findid(id));
        return userRepository.findid(id);
    }

}
