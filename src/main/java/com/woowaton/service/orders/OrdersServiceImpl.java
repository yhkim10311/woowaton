package com.woowaton.service.orders;

import com.woowaton.domain.orders.Orders;
import com.woowaton.domain.orders.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrdersServiceImpl implements OrdersService{

    private final OrdersRepository ordersRepository;

    public List<OrdersDto> findAllByDesc(){
        return ordersRepository.findAllDesc().stream().map(orders -> new OrdersDto(orders)).collect(Collectors.toList());
    }
}
