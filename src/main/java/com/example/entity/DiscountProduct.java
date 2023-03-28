package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discount_product")
public class DiscountProduct {
    @EmbeddedId
    private DiscountProductId discountProductId = new DiscountProductId();

    @ManyToOne(cascade = CascadeType.REMOVE)
    @MapsId("discountId")
    private Discount discount;

    @ManyToOne
    @MapsId("productId")
    private Product product;

    public DiscountProduct(Discount discount, Product product) {
        this.discount = discount;
        this.product = product;
    }
}
