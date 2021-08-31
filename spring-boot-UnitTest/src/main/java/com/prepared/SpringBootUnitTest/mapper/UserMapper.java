package com.prepared.SpringBootUnitTest.mapper;


import com.prepared.SpringBootUnitTest.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 *  user mapper
 *
 * @author prepared
 * @date 2021-06-08
 */
@Mapper
@Repository
public interface UserMapper {

    /**
     * 新增
     * @author zhengkai.blog.csdn.net
     * @date 2021/06/08
     **/
    int insert(User user);

    /**
     * 刪除
     * @author zhengkai.blog.csdn.net
     * @date 2021/06/08
     **/
    int delete(int id);

    /**
     * 更新
     * @author zhengkai.blog.csdn.net
     * @date 2021/06/08
     **/
    int update(User user);

    /**
     * 查询 根据主键 id 查询
     * @author zhengkai.blog.csdn.net
     * @date 2021/06/08
     **/
    User load(int id);

    /**
     * 查询 分页查询
     * @author zhengkai.blog.csdn.net
     * @date 2021/06/08
     **/
    List<User> pageList(int offset, int pagesize);

    /**
     * 查询 分页查询 count
     * @author zhengkai.blog.csdn.net
     * @date 2021/06/08
     **/
    int pageListCount(int offset,int pagesize);

}