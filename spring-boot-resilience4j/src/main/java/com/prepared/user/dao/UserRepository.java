package com.prepared.user.dao;

import com.prepared.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: prepared
 * @Date: 2022/8/29 21:45
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
