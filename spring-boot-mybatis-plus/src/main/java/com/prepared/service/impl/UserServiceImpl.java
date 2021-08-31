package com.prepared.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.prepared.bean.User;
import com.prepared.mapper.UserMapper;
import com.prepared.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: zhongshibo
 * @Date: 2020/3/10 14:23
 */
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
