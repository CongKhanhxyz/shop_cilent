package com.example.entity.shop;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShopFollowerId implements Serializable {
    @Column(name = "shop_id")
    private Long shopId;

    @Column(name = "user_id")
    private Long userId;
}
