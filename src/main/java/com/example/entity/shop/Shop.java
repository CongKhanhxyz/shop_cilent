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
public class Shop {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long shopId;

	private String storeName;

	private String address;
	private String phone;

//	private int follower;
//	private int following;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
}