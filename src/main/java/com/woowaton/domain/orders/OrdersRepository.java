package com.woowaton.domain.orders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.util.List;

public interface OrdersRepository extends PagingAndSortingRepository<Orders, Long> {

    @Query("select o from Orders o order by o.createdDate desc")
    List<Orders> findAllDesc();

    @Query("select o from Orders o order by o.createdDate desc")
    List<Orders> findAllByPage(Pageable pageable);

}
