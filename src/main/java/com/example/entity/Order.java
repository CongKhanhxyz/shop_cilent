package com.example.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {


//    @EmbeddedId
//    private OrderProductId orderProductId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;

//    @ManyToOne
//    @JoinColumn(name = "order_id2")
//    @MapsId("productId")
//    private Order order;
//

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column
    private int quantity;

    @Column
    private int status;

    @Column
    private LocalDate createdDate;

    @Column
    private LocalDate shipDate;

    @Column
    private String color;

    @Column
    private String size;

    @Column
    private String methodPayment;

    @Column
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "address_id")
    private Address address;

    @Column
    private LocalTime createdTime;
}