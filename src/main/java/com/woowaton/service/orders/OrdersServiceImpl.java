package com.woowaton.service.orders;

import com.woowaton.domain.orders.Orders;
import com.woowaton.domain.orders.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    private final RedisTemplate<String, Object> redisTemplate;

    private final String woowaCookie = "woowatonV";

    @Override
    public List<OrdersDto> findAllByDesc(){
        return ordersRepository.findAllDesc().stream().map(orders -> new OrdersDto(orders)).collect(Collectors.toList());
    }

    @Override
    public List<OrdersDto> findAllByPage(int offset, int limit){
        return ordersRepository.findAllByPage(PageRequest.of(offset,limit)).stream().map(orders -> new OrdersDto(orders)).collect(Collectors.toList());
    }

    @Override
    public OrdersDto findOrder(Long id){
        Orders orders = ordersRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("No such order exists"));
        return new OrdersDto(orders);
    }

    @Override
    public List<OrdersDto> findListWithCookie(HttpServletRequest request, HttpServletResponse response) {
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
            List<OrdersDto> ordersDtos = findAllByDesc();
            redisTemplate.opsForValue().set(redisKey+"List",ordersDtos);
            redisTemplate.opsForValue().set(redisKey,10, 1, TimeUnit.MINUTES);
            result = ordersDtos.subList(0,10);
            Cookie cookie = new Cookie(woowaCookie, redisKey);
            cookie.setHttpOnly(true);
            cookie.setPath("/");
            cookie.setMaxAge(30);
            response.addCookie(cookie);
        }
        return result;
    }


}
