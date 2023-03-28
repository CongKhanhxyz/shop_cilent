package com.example.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountProductId implements Serializable {

    @Column(name = "discount_id")
    private Long discountId;

    @Column(name = "product_id")
    private Long productId;
}
