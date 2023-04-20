package com.example.entity.shop;

import com.example.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "shop_follower")
public class ShopFollower {

    @EmbeddedId
    private ShopFollowerId shopFollowerId;

    @ManyToOne
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @MapsId("shopId")
    @JoinColumn(name = "shop_id")
    private Shop shop;
}
