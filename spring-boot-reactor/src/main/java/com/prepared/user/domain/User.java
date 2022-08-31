package com.prepared.user.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * @Author: prepared
 * @Date: 2022/8/29 21:40
 */
@Data
@NoArgsConstructor
@Table(name = "t_user")
@Entity
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	private String userName;

	private int age;

	private String sex;

	public User(String userName, int age, String sex) {
		this.userName = userName;
		this.age = age;
		this.sex = sex;
	}
}
