package com.woowaton.domain.user;

import com.woowaton.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @Size(min = 1)
    private String name;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String passwd;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    //위치
    @Column
    private String latitude;

    @Column
    private String longitude;

    @Builder
    public User(String name, String email, String passwd, Role role, String latitude, String longitude){
        this.name = name;
        this.email = email;
        this.passwd = passwd;
        this.role = role;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}
