package com.prepared.SpringBootUnitTest.service;

import com.prepared.SpringBootUnitTest.common.ReturnT;
import com.prepared.SpringBootUnitTest.entity.User;

/**
 * @description user
 * @author prepared
 * @date 2021-06-08
 */
public interface UserService {

    /**
    * 新增
    */
    public ReturnT insert(User user);

    /**
    * 删除
    */
    public ReturnT delete(int id);

    /**
    * 更新
    */
    public ReturnT update(User user);

    /**
    * 根据主键 id 查询
    */
    public ReturnT load(int id);

    /**
    * 分页查询
    */
    public ReturnT pageList(int offset, int pagesize);

}