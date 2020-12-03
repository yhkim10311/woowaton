package com.woowaton.security;

import com.woowaton.domain.user.Role;
import com.woowaton.domain.user.User;
import com.woowaton.service.auth.CustomUserDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

public class RedisAuthFilter extends OncePerRequestFilter {

    @Autowired
    private HttpSession httpSession;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        Object obj = httpSession.getAttribute("user");

        if(obj!=null){
            setContextHolder(obj);

        }
        filterChain.doFilter(request, response);
    }

    private void setContextHolder(Object obj){
        Collection<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(Role.USER.getKey()));
        User user = User.builder().email((String)obj).name("name").role(Role.USER).build();
        Authentication authentication =
                new UsernamePasswordAuthenticationToken(new CustomUserDetails(user), null, authorities);
        SecurityContextHolder.getContext().setAuthentication(authentication);
    }
}
