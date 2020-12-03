package com.woowaton.service.user;

import com.woowaton.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@Getter
@ToString
@RequiredArgsConstructor
public class JoinResult {

    private final UserDto userDto;
}
