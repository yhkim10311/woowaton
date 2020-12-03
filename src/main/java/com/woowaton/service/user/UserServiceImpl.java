package com.woowaton.service.user;

import com.woowaton.domain.user.Role;
import com.woowaton.domain.user.User;
import com.woowaton.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.google.common.base.Preconditions.checkArgument;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public User join(String name, String email, String passwd){

        checkArgument(passwd.length()>8, "password length must be greater than 8");

        User user = User.builder()
                .name(name)
                .email(email)
                .passwd(passwordEncoder.encode(passwd))
                .role(Role.USER)
                .build();

        return userRepository.save(user);
    }

    @Override
    public User findByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new IllegalArgumentException("해당 유저가 존재하지 않습니다."));
    }
}
