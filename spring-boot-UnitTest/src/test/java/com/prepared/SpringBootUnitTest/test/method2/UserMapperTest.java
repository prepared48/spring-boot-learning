package com.prepared.SpringBootUnitTest.test.method2;

import com.prepared.SpringBootUnitTest.entity.User;
import com.prepared.SpringBootUnitTest.mapper.UserMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 建议使用，按需加载对应的Mapper文件
 *
 * @Author: prepared
 * @Date: 2021/6/8 10:50
 */
public class UserMapperTest {

    static SqlSession sqlSession;

    static UserMapper userMapper;

    @Before
    public void before() {
        sqlSession = Mybatis3Utils.getCurrentSqlSession();
        userMapper = sqlSession.getMapper(UserMapper.class);
    }

    @After
    public void after() {
        Mybatis3Utils.closeCurrentSession();
    }

    @Test
    public void testLoad() {
        User user = userMapper.load(1);
        assertThat(user.getName()).isNotNull();
    }
}

