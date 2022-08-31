package com.prepared.user.controller;

import com.prepared.user.domain.User;
import com.prepared.user.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: prepared
 * @Date: 2022/8/29 21:47
 */
@RestController
public class UserController {

	@Resource
	private UserService userService;

	@RequestMapping("/add")
	public Mono<Boolean> add() {
		User user = new User("xiaoming", 10, "F");
		return userService.save(user) ;

	}

	@RequestMapping("/list")
	public Mono<List<User>> list() {
		return userService.list();
	}
}
