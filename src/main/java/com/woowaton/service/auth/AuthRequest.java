package com.woowaton.service.auth;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
public class AuthRequest {

    private String principal;

    private String credentials;

}
