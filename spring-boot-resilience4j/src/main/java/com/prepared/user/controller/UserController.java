package com.prepared.user.controller;

import com.prepared.user.domain.User;
import com.prepared.user.service.UserService;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: prepared
 * @Date: 2022/8/29 21:47
 */
@RestController
public class UserController {


	@Resource
	private UserService userService;

	@RateLimiter(name = "backendA")
	@RequestMapping("/add")
	public Mono<Boolean> add() {
		User user = new User("xiaoming", 10, "F");
		return userService.save(user) ;

	}

	@RateLimiter(name = "backendA")
	@RequestMapping("/list")
	public Mono<List<User>> list() {
		return userService.list();
	}
}
