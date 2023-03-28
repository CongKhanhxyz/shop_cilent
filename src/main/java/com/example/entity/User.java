package com.example.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.beans.Transient;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long userId;
	private String username;

	private String password;

	private String lastname;

	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_roles",
			joinColumns = {@JoinColumn(name = "user_id")},
			inverseJoinColumns = {@JoinColumn(name = "role_id")}

		)
	private List<Role> roles;

	private String firstname;

	private String phone;
	private String urlImageAvatar;
	@Transient
	public String getPhotosImagePath() {
		if (urlImageAvatar == null || userId == null) return null;

		return "/imgs/user" + userId + "/" + urlImageAvatar;
	}
}