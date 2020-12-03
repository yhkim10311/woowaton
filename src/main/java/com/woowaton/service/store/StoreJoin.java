package com.woowaton.service.store;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class StoreJoin {

    private String name;

    private String latitude;

    private String longitude;

    private Integer minDeliveryTime;

    private Integer maxDeliveryTime;

    private Integer minDeliveryTip;

    private Integer maxDeliveryTip;
}
