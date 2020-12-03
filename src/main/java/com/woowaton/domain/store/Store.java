package com.woowaton.domain.store;

import com.woowaton.domain.BaseTimeEntity;
import com.woowaton.domain.menu.Menu;
import com.woowaton.domain.orders.Orders;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class Store extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @Size(min = 1)
    private String name;

    //별점
    @Column(nullable = false)
    private String rating;

    //위치
    @Column
    private String latitude;

    @Column
    private String longitude;

    //소요시간
    @Column
    private Integer minDeliveryTime;

    @Column
    private Integer maxDeliveryTime;

    //배달팁
    @Column
    private Integer minDeliveryTip;

    @Column
    private Integer maxDeliveryTip;

    @Column
    private String comment1;

    @Column
    private String comment2;

    @Column
    private String comment1imageUrl;

    @Column
    private String comment2imageUrl;

    @Column
    private Integer ratingCnt;

    @OneToMany(mappedBy = "store")
    private List<Orders> orders = new ArrayList<>();

    @Builder
    public Store(String name, String rating, String latitude, String longitude,
                 Integer minDeliveryTime, Integer maxDeliveryTime, Integer minDeliveryTip, Integer maxDeliveryTip,
                 String comment1, String comment2, String comment1imageUrl, String comment2imageUrl, Integer ratingCnt){
        this.name = name;
        this.latitude = latitude;
        this.rating = rating;
        this.longitude = longitude;
        this.minDeliveryTime = minDeliveryTime;
        this.maxDeliveryTime = maxDeliveryTime;
        this.minDeliveryTip = minDeliveryTip;
        this.maxDeliveryTip = maxDeliveryTip;
        this.comment1 = comment1;
        this.comment2 = comment2;
        this.comment1imageUrl = comment1imageUrl;
        this.comment2imageUrl = comment2imageUrl;
        this.ratingCnt = ratingCnt;
    }
}
