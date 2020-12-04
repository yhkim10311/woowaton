package com.woowaton.service.orders;

import com.woowaton.domain.menu.Menu;
import com.woowaton.domain.orders.Orders;
import com.woowaton.service.menu.MenuDto;
import com.woowaton.service.store.StoreDto;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class OrdersDto implements Serializable {

    private static final long serialVersionUID = 2478197471250586664L;

    private Long id;

    private Integer amount;

    private String imageUrl;

    private Integer itemCnt;

    private StoreDto storeDto;

    private LocalDateTime createdDate;

    private String woowatonV;

    private List<MenuDto> menusDto = new ArrayList<>();

    public OrdersDto(Orders orders){
        copyProperties(orders, this);
        storeDto = new StoreDto(orders.getStore());
        orders.getMenus().forEach(menu -> menusDto.add(new MenuDto(menu)));
    }
}
