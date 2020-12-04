package com.woowaton.api.store;

import com.woowaton.domain.store.Store;
import com.woowaton.service.store.StoreJoin;
import com.woowaton.service.store.StoreResult;
import com.woowaton.service.store.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/store")
@RequiredArgsConstructor
public class StoreRestController {

    private final StoreService storeService;

    @PostMapping("/join")
    public ResponseEntity<StoreResult> join(@RequestBody StoreJoin storeJoin){

        Store store = storeService.addStore(storeJoin.getName(),storeJoin.getLatitude(),storeJoin.getLatitude(),storeJoin.getMinDeliveryTime(), storeJoin.getMaxDeliveryTime(), storeJoin.getMinDeliveryTip(), storeJoin.getMaxDeliveryTip());

        return ResponseEntity.ok(new StoreResult(store));
    }

}
