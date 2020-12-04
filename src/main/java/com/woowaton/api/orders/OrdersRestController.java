package com.woowaton.api.orders;

import com.woowaton.service.orders.OrdersDto;
import com.woowaton.service.orders.OrdersService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrdersRestController {

    private final OrdersService ordersService;

    private final RedisTemplate<String, Object> redisTemplate;

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
    public ResponseEntity<List<OrdersDto>> allOrdersByPageV2(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                           @RequestParam(value = "limit", defaultValue = "30") int limit){

        Object obj = redisTemplate.opsForValue().get("yhAdminKey");
        List<OrdersDto> result;
        if(obj == null){
            List<OrdersDto> ordersDtos = ordersService.findAllByDesc();
            redisTemplate.opsForValue().set("yhAdmin",ordersDtos);
            redisTemplate.opsForValue().set("yhAdminKey",10, 1, TimeUnit.MINUTES);
            result = ordersDtos.subList(0,10);
        }else{
            Integer seq = (Integer) redisTemplate.opsForValue().get("yhAdminKey");
            List<OrdersDto> ordersDtos = (List<OrdersDto>) redisTemplate.opsForValue().get("yhAdmin");
            result = ordersDtos.subList(seq,seq+10);
            redisTemplate.opsForValue().set("yhAdminKey",seq+10, 1, TimeUnit.MINUTES);
        }

        return ResponseEntity.ok(result);
    }

}
