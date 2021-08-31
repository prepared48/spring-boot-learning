package com.prepared;

import com.prepared.bean.Post;
import com.prepared.mapper.PostMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class HotReloadApplicationTests {

	@Autowired
	private PostMapper postMapper;

	@Test
	public void testXmlReload() {
		List<Post> posts = postMapper.queryPost();
		System.out.println(">>>>>>>>> 第一个岗位名称：" + posts.get(0).getPostName());
	}

}
