package com.woowaton.service.auth;

import com.woowaton.service.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@AllArgsConstructor
public class AuthResult {

    private UserDto userDto;
}
