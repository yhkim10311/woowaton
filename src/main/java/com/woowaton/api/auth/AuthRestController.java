package com.woowaton.api.auth;

import com.woowaton.service.auth.AuthRequest;
import com.woowaton.service.auth.AuthResult;
import com.woowaton.service.auth.CustomUserDetails;
import com.woowaton.service.user.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthRestController {

    private final AuthenticationManager authenticationManager;

    private final HttpSession httpSession;

    @PostMapping
    public ResponseEntity<AuthResult> authentication(@RequestBody AuthRequest authRequest, HttpServletResponse response) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authRequest.getPrincipal(), authRequest.getCredentials()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        httpSession.setAttribute("user",authentication.getName());

        return  ResponseEntity.ok(
                new AuthResult(new UserDto(((CustomUserDetails)authentication.getPrincipal()).getUser()))
        );
    }
}
