package com.woowaton.service.orders;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface OrdersService {

    List<OrdersDto> findAllByDesc();

    List<OrdersDto> findAllByPage(int offset, int limit);

    OrdersDto findOrder(Long id);

    List<OrdersDto> findListWithCookie(HttpServletRequest request, HttpServletResponse response);
}
