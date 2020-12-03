package com.woowaton.domain.orders;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {
    @Query("select o from Orders o")
    List<Orders> findAllDesc();
}
