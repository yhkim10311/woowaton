package com.woowaton.service.orders;

import java.util.List;

public interface OrdersService {

    List<OrdersDto> findAllByDesc();

    List<OrdersDto> findAllByPage(int offset, int limit);
}
