package com.prepared.user.service;

import com.prepared.user.dao.UserRepository;
import com.prepared.user.domain.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author: prepared
 * @Date: 2022/8/29 21:45
 */
@Service
public class UserService {

	private Logger logger = LoggerFactory.getLogger(UserService.class);

	@Resource
	private UserRepository userRepository;

	public Mono<Boolean> save(User user) {
		long startTime = System.currentTimeMillis();
		return Mono.fromSupplier(() -> {
					return userRepository.save(user) != null;
				})
				.doOnError(e -> {
					// 打印异常日志&增加监控（自行处理）
					logger.error("save.user.error, user={}, e", user, e);
				})
				.doFinally(e -> {
					// 耗时 & 整体健康
					logger.info("save.user.time={}, user={}", user, System.currentTimeMillis() - startTime);
				});
	}

	public Mono<User> findById(Long id) {
		long startTime = System.currentTimeMillis();
		return Mono.fromSupplier(() -> {
					return userRepository.getReferenceById(id);
				}).doOnError(e -> {
					// 打印异常日志&增加监控（自行处理）
					logger.error("findById.user.error, id={}, e", id, e);
				})
				.doFinally(e -> {
					// 耗时 & 整体健康
					logger.info("findById.user.time={}, id={}", id, System.currentTimeMillis() - startTime);
				});
	}

	public Mono<List<User>> list() {
		long startTime = System.currentTimeMillis();
		return Mono.fromSupplier(() -> {
					return userRepository.findAll();
				}).doOnError(e -> {
					// 打印异常日志&增加监控（自行处理）
					logger.error("list.user.error, e", e);
				})
				.doFinally(e -> {
					// 耗时 & 整体健康
					logger.info("list.user.time={}, ", System.currentTimeMillis() - startTime);
				});
	}
}
