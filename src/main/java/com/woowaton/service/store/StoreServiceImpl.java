package com.woowaton.service.store;

import com.woowaton.domain.store.Store;
import com.woowaton.domain.store.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;

    @Override
    public Store addStore(String name, String latitude, String longitude, Integer minDeliveryTime, Integer maxDeliveryTime, Integer minDeliveryTip, Integer maxDeliveryTip){
        Store store = Store.builder().name(name).latitude(latitude).longitude(longitude)
                .minDeliveryTime(minDeliveryTime).maxDeliveryTime(maxDeliveryTime)
                .minDeliveryTip(minDeliveryTip).maxDeliveryTip(maxDeliveryTip).rating("0").build();
        return storeRepository.save(store);
    }
}
