package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.sql.Blob;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long productID;
	private String productName;
	private double oldPrice;
	private String urlImage;
	private int status;
	private int quantity;
	private String shortDescription;
	private float totalLike;
	private float soldAmount;
	private double newPrice;

	@Column
	private int reviewAmount;

	@Column
	private int percentDiscount;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "category_id")
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private Set<DiscountProduct> discountProductSet;
	@Transient
	public String getPhotosImagePath() {
		if (urlImage == null || productID == null) return null;

		return "/imgs/product" + productID + "/" + urlImage;
	}
}
// product có nhiều size
// mỗi size có nhiều color
