package com.example.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cart")
public class Cart {
	@EmbeddedId
	private CartProductId cartProductId;

	@ManyToOne
	@MapsId("userId")
	private User userCart;

	@ManyToOne
	@MapsId("productId")
	private Product product;

	@Column
	private int quantity;

	@Column
	private String color;

	@Column
	private String size;

	@Column
	private LocalDateTime createdTime;
}