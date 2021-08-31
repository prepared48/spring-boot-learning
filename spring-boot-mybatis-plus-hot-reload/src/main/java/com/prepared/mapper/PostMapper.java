package com.prepared.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.prepared.bean.Post;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface PostMapper extends BaseMapper<Post> {

	List<Post> queryPost();

}