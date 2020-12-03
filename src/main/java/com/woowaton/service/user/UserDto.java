package com.woowaton.service.user;

import com.woowaton.domain.user.Role;
import com.woowaton.domain.user.User;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class UserDto {

    private Long id;

    private String name;

    private String email;

    private Role role;

    public UserDto(User user){
        copyProperties(user, this);
    }

}
