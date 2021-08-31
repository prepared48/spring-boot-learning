package com.prepared;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.prepared.bean.User;
import com.prepared.bean.UserTest;
import com.prepared.mapper.UserMapper;
import com.prepared.mapper.UserTestMapper;
import com.prepared.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PreparedMybatisPlusApplicationTests {

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private UserTestMapper userTestMapper;

	@Autowired
	private UserService userService;


	@Test
	public void testUserTest2() {
		//	声明主键策略，数据库没有设置，代码设置。
		//		@TableId(type = IdType.AUTO, value = "id")
		UserTest userTest = new UserTest();
		userTest.setUserName("tst");
		userTest.setPassword("tst");
		userTest.setId(1);
		userTestMapper.insert(userTest);
		//	还是报错：Caused by: java.sql.SQLException: Field 'id' doesn't have a default value
		//	搜索说：数据库没有设置 Auto Increment
		//	这种情况需求修改主键类型，比如修改为INPUT

	}

	@Test
	public void testUserTest1() {
		//	没有声明主键策略，数据库没有设置，代码也没设置。
		UserTest userTest = new UserTest();
		userTest.setUserName("tst");
		userTest.setPassword("tst");
		userTestMapper.insert(userTest);
		//	第一条能插入成功，因为 int 默认是 0.
	}

	@Test
	public void testselectList() {
		List<User> users = userMapper.selectList(new QueryWrapper<>());
		System.out.println(users);
	}

	@Test
    public void testselectList2() {
		List<User> users = userService.list();
		System.out.println(users);
	}

	@Test
    public void testAddUser() {
		User user = new User();
		user.setUserName("test2");
		user.setPassword("2345667");
		boolean save = userService.save(user);
		System.out.println(">>>>>>>>>>> " + save);
		Assert.assertEquals(save, true);
	}

	@Test
    public void testUpdateUser() {
		User user = new User();
		user.setId(1);
		user.setUserName("test3");
		user.setPassword("654321");
		boolean save = userService.updateById(user);
		System.out.println(">>>>>>>>>>> " + save);
		Assert.assertEquals(save, true);
	}
}
