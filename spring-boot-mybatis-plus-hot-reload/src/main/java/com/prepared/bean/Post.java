package com.prepared.bean;

import com.baomidou.mybatisplus.annotation.KeySequence;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;

/**
 * <p>
 * 特勤岗位表-基础表
 * </p>
 *
 * @author xiongwg
 * @since 2020-06-11
 */
@KeySequence(value = "SEQ_SECURITY_POST_ID", clazz = Integer.class)
@TableName("t_post")
public class Post {

	@TableId(value = "post_id")
	private Integer postId;

	@TableField("post_name")
	private String postName;

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getPostName() {
		return postName;
	}

	public void setPostName(String postName) {
		this.postName = postName;
	}
}