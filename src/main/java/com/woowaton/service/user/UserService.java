package com.woowaton.service.user;

import com.woowaton.domain.user.User;

public interface UserService {

    User join(String name, String email, String passwd);

    User findByEmail(String email);
}
