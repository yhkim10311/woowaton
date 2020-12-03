package com.woowaton.api.user;

import com.woowaton.domain.user.User;
import com.woowaton.service.user.JoinRequest;
import com.woowaton.service.user.JoinResult;
import com.woowaton.service.user.UserDto;
import com.woowaton.service.user.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserRestController {

    private final UserService userService;

    @PostMapping("/join")
    public ResponseEntity<JoinResult> join(@Valid @RequestBody JoinRequest joinRequest){
        User user = userService.join(joinRequest.getName(), joinRequest.getPrincipal(), joinRequest.getCredentials());
        log.info("Joined"+user.toString());
        return ResponseEntity.ok(new JoinResult(new UserDto(user)));
    }
}
