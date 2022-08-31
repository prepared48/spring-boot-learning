package com.prepared.user.service;

import com.prepared.user.dao.UserRepository;
import com.prepared.user.domain.User;
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

	@Resource
	private UserRepository userRepository;

	public Mono<Boolean> save(User user) {
		return Mono.fromSupplier(()-> {
			return userRepository.save(user) != null;
		});
	}

	public Mono<User> findById(Long id) {
		return Mono.fromSupplier(()-> {
			return userRepository.getReferenceById(id);
		});
	}

	public Mono<List<User>> list() {
		return Mono.fromSupplier(()-> {
			return userRepository.findAll();
		});
	}
}
