package com.prepared.SpringBootUnitTest.test.method3;

import com.prepared.SpringBootUnitTest.common.ReturnT;
import com.prepared.SpringBootUnitTest.entity.User;
import com.prepared.SpringBootUnitTest.mapper.UserMapper;
import com.prepared.SpringBootUnitTest.service.UserService;
import com.prepared.SpringBootUnitTest.service.UserServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 建议使用
 *
 * 使用场景：需要依赖其他实体的情况下使用。比如测试service层，COLA的app层等
 *
 * 1、需要通过构造方法注入依赖
 * 2、然后通过 Mockito 模拟需要注入的依赖项
 *
 * @Author: prepared
 * @Date: 2021/6/8 11:03
 */
@RunWith(SpringRunner.class)
public class UserServiceUseCase2Test {

    @Mock
    private UserMapper userMapper;

    private UserService userService;

    @Before
    public void initUseCase() {
        userService = new UserServiceImpl(userMapper);
    }

    @Test
    public void testLoad() {
        User user = new User();
        user.setId(6L);
        user.setName("Angela");
        // 模拟userMapper返回
        Mockito.when(userMapper.insert(user)).thenReturn(1);
        ReturnT returnT = userService.insert(user);
        assertThat(returnT.getData()).isEqualTo(1);
    }
}
