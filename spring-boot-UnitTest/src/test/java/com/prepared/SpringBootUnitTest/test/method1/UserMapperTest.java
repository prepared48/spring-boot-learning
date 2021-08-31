package com.prepared.SpringBootUnitTest.test.method1;

import com.prepared.SpringBootUnitTest.entity.User;
import com.prepared.SpringBootUnitTest.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 不建议 使用该方法
 *
 * 需要启动整个Spring上下文，耗时比较长
 *
 * @Author: prepared
 * @Date: 2021/6/8 10:50
 */
@RunWith(SpringRunner.class)
@WebAppConfiguration
@SpringBootTest
public class UserMapperTest {

    @Resource
    UserMapper userMapper;

    @Test
    public void testLoad() {
        User user = userMapper.load(1);
        assertThat(user.getName()).isNotNull();
    }
}

