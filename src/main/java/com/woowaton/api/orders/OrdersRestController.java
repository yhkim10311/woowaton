package com.woowaton.api.orders;

import com.woowaton.service.orders.OrdersDto;
import com.woowaton.service.orders.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
