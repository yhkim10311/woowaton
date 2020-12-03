package com.woowaton.api.orders;

import com.woowaton.service.orders.OrdersDto;
import com.woowaton.service.orders.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersRestController {

    private final OrdersService ordersService;

    @GetMapping
    public ResponseEntity<List<OrdersDto>> allOrdersByDesc(){
        return ResponseEntity.ok(ordersService.findAllByDesc());
    }

    @GetMapping("/page")
    public ResponseEntity<List<OrdersDto>> allOrdersByPage(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                           @RequestParam(value = "limit", defaultValue = "30") int limit){
        return ResponseEntity.ok(ordersService.findAllByPage(offset, limit));
    }
}
