package com.woowaton.domain.menu;

import com.woowaton.domain.orders.Orders;
import com.woowaton.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
public class Menu {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    private Integer amount;

    @ManyToOne
    @JoinColumn(nullable = false, name = "orders_id", referencedColumnName = "id")
    private Orders orders;

    @Builder
    public Menu(String name, Integer amount){
        this.name = name;
        this.amount = amount;
    }
}
