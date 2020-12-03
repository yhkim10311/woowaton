package com.woowaton.service.store;

import com.woowaton.domain.store.Store;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class StoreDto {

    private Long id;

    private String name;

    private String rating;

    private String latitude;

    private String longitude;

    private Integer minDeliveryTime;

    private Integer maxDeliveryTime;

    private Integer minDeliveryTip;

    private Integer maxDeliveryTip;

    private String comment1;

    private String comment2;

    private String comment1imageUrl;

    private String comment2imageUrl;

    private Integer ratingCnt;

    public StoreDto(Store store){
        copyProperties(store, this);
    }
}
