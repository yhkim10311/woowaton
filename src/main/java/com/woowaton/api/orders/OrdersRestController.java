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
    public ResponseEntity<List<OrdersDto>> allOrdersByPageV2(@RequestParam(value = "offset", defaultValue = "0") int offset,
                                                             @RequestParam(value = "limit", defaultValue = "30") int limit,
                                                             HttpServletRequest request, HttpServletResponse response){
        List<OrdersDto> result;

        String redisKey = UUID.randomUUID().toString();
        Cookie[] cookies = request.getCookies();
        boolean flag = false;

        if(cookies!=null) {
            for (Cookie cookie : cookies) {
                String cookieName = cookie.getName();
                if (cookieName.equals(woowaCookie)) {
                    Object obj = redisTemplate.opsForValue().get(cookie.getValue());
                    if (obj != null) {
                        flag = true;
                        redisKey = cookie.getValue();
                        break;
                    }
                }
            }
        }
        if(flag){
            Integer seq = (Integer) redisTemplate.opsForValue().get(redisKey);
            List<OrdersDto> ordersDtos = (List<OrdersDto>) redisTemplate.opsForValue().get(redisKey+"List");
            result = ordersDtos.subList(seq,seq+10);
            redisTemplate.opsForValue().set(redisKey,seq+10, 1, TimeUnit.MINUTES);
            Cookie cookie = new Cookie(woowaCookie, redisKey);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(30);
            response.addCookie(cookie);
        }else{
            List<OrdersDto> ordersDtos = ordersService.findAllByDesc();
            redisTemplate.opsForValue().set(redisKey+"List",ordersDtos);
            redisTemplate.opsForValue().set(redisKey,10, 1, TimeUnit.MINUTES);
            result = ordersDtos.subList(0,10);
            Cookie cookie = new Cookie(woowaCookie, redisKey);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(30);
            response.addCookie(cookie);
        }


        /*
        Object obj = redisTemplate.opsForValue().get("yhAdminKey");

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

         */

        return ResponseEntity.ok(result);
    }

    @GetMapping("/v2/page/{id}")
    public ResponseEntity<OrdersDto> getOrder(@PathVariable("id") Long id){
        return ResponseEntity.ok(ordersService.findOrder(id));
    }
}
