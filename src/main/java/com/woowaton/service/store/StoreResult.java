package com.woowaton.service.store;

import com.woowaton.domain.store.Store;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import static org.springframework.beans.BeanUtils.copyProperties;

@Getter
@Setter
@ToString
public class StoreResult {

    private String name;

    private String latitude;

    private String longitude;

    public StoreResult(Store store){
        copyProperties(store, this);
    }
}
