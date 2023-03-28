package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long addressId;

    private String detailAddress;

    private int defaultAddress;

    private Integer typeAddress;

    private String fullnameRecive;

    private String phone;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ward_id")
    private Ward ward;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

}
