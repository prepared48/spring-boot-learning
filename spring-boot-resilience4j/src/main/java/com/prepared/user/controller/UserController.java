package com.prepared.user.controller;

import com.prepared.user.domain.User;
import com.prepared.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @Author: prepared
 * @Date: 2022/8/29 21:47
 */
@RestController
public class UserController {

	private Logger logger = LoggerFactory.getLogger(UserController.class);

	@Resource
	private UserService userService;

	@RequestMapping("/add")
	public Mono<Boolean> add() throws IOException {
		logger.info("invoke.add.user");
		User user = new User("xiaoming", 10, "F");
//		throw new IOException("插入数据失败");
		return userService.save(user) ;

	}

	@RequestMapping("/list")
	public Mono<List<User>> list() {
		return userService.list();
	}
}
