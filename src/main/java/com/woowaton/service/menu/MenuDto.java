package com.woowaton.service.menu;

import com.woowaton.domain.menu.Menu;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class MenuDto {

    private Long id;

    private String name;

    private Integer amount;

    public MenuDto(Menu menu){
        copyProperties(menu, this);
    }
}
