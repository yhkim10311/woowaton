package com.woowaton.api.orders;

import com.woowaton.service.orders.OrdersDto;
import com.woowaton.service.orders.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

//@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersRestController {

    private final OrdersService ordersService;

    private final RedisTemplate<String, Object> redisTemplate;

    private final String woowaCookie = "woowatonV";

    @GetMapping
    public ResponseEntity<List<OrdersDto>> allOrdersByDesc(){
        return ResponseEntity.ok(ordersService.findAllByDesc());
    }

    @GetMapping("/page")
    public ResponseEntity<List<OrdersDto>> allOrdersByPage(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                           @RequestParam(value = "limit", defaultValue = "30") int limit){
        return ResponseEntity.ok(ordersService.findAllByPage(offset, limit));
    }

    @GetMapping("/v2/page")
    public ResponseEntity<List<OrdersDto>> allOrdersByPageV2(HttpServletRequest request, HttpServletResponse response){

        return ResponseEntity.ok(ordersService.findListWithCookie(request,response));
    }

    @GetMapping("/v2/page/{id}")
    public ResponseEntity<OrdersDto> getOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(ordersService.findOrder(id));
    }
}
