package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.time.LocalDate;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "discount")
public class Discount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long discountId;
    private String name;
    private LocalDate startDate;
    private LocalDate endDate;
    private String method;
    private int value;
    private String unit;
    @OneToMany(mappedBy = "discount")
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Set<DiscountProduct> discountProductSet;
}
