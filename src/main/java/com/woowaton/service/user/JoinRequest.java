package com.woowaton.service.user;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Pattern;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class JoinRequest {

    private String name;

    @Pattern(regexp = "[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}", message = "이메일 형식이 아닙니다.")
    private String principal;

    private String credentials;
}
