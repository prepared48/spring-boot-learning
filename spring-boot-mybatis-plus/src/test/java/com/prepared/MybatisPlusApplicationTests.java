package com.prepared;

import com.prepared.entity.Tuser;
import com.prepared.mapper.TuserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @Author: prepared
 * @Date: 2021/9/9 17:51
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MybatisPlusApplicationTests {

    @Resource
    private TuserMapper tuserMapper;

    @Test
    public void testAddUser() {
        Tuser tuser = new Tuser();
        tuser.setAge(1);
        tuser.setIdCard("123415413");
        tuser.setIsmale(Boolean.TRUE);
        tuser.setName("prepared");
        tuserMapper.insert(tuser);
    }
}
