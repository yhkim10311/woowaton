package com.woowaton.service.store;

import com.woowaton.domain.store.Store;

public interface StoreService {

    Store addStore(String name, String latitude, String longitude, Integer minDeliveryTime, Integer maxDeliveryTime, Integer minDeliveryTip, Integer maxDeliveryTip);
}
