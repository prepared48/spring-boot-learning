package com.prepared;

import com.prepared.entity.UserEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringBootRedisApplicationTests {

	@Autowired
	private RedisTemplate<String, String> strRedisTemplate;

	@Autowired
	private RedisTemplate<String, Serializable> serializableRedisTemplate;
	
	@Test
	public void testString() {
		strRedisTemplate.opsForValue().set("strKey", "zwqh");
		assertEquals(strRedisTemplate.opsForValue().get("strKey"), "zwqh");
	}
	
	@Test
	public void testSerializable() {
		UserEntity user=new UserEntity();
		user.setId(1L);
		user.setUserName("prepared");
		user.setUserSex("M");
		serializableRedisTemplate.opsForValue().set("user", user);		
		UserEntity user2 = (UserEntity) serializableRedisTemplate.opsForValue().get("user");
		System.out.println("user:"+user2.getId()+","+user2.getUserName()+","+user2.getUserSex());

		assertEquals(user2.getId(), Long.valueOf(1));
		assertEquals(user2.getUserName(), "prepared");
		assertEquals(user2.getUserSex(), "M");
	}

}
