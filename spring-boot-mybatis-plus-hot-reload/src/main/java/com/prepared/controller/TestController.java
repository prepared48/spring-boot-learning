package com.prepared.controller;

import com.prepared.bean.Post;
import com.prepared.mapper.PostMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: zhongshibo
 * @Date: 2020/9/10 09:15
 */
@RestController
public class TestController {

	@Autowired
	private PostMapper postMapper;

	@RequestMapping("queryPosts")
	public String testPost() {
		List<Post> posts = postMapper.queryPost();
		return posts.get(0).getPostName();
	}
}
