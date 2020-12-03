package com.woowaton.domain.orders;

import com.woowaton.domain.BaseTimeEntity;
import com.woowaton.domain.menu.Menu;
import com.woowaton.domain.store.Store;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Orders extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Integer amount;

    @Column
    private String imageUrl;

    @Column
    private Integer itemCnt;

    //메뉴
    @OneToMany(mappedBy = "orders")
    private List<Menu> menus = new ArrayList<>();

    //가게
    @ManyToOne
    @JoinColumn(nullable = false, name = "store_id", referencedColumnName = "id")
    private Store store;

    @Builder
    public Orders(Integer amount, String imageUrl, Integer itemCnt){
        this.amount = amount;
        this.imageUrl = imageUrl;
        this.itemCnt = itemCnt;
    }
}
