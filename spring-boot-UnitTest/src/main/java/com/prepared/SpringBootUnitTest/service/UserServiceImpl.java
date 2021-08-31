package com.prepared.SpringBootUnitTest.service;

import com.prepared.SpringBootUnitTest.common.ReturnT;
import com.prepared.SpringBootUnitTest.entity.User;
import com.prepared.SpringBootUnitTest.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author prepared
 * @date 2021-06-08
 */
@Service
public class UserServiceImpl implements UserService {

    private UserMapper userMapper;

    public UserServiceImpl(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public ReturnT insert(User user) {

        // valid
        if (user == null) {
            return ReturnT.error("必要参数缺失");
        }

        int insert = userMapper.insert(user);
        return ReturnT.success(insert);
    }


    @Override
    public ReturnT delete(int id) {
        int ret = userMapper.delete(id);
        return ret > 0 ? ReturnT.success() : ReturnT.error();
    }


    @Override
    public ReturnT update(User user) {
        int ret = userMapper.update(user);
        return ret > 0 ? ReturnT.success() : ReturnT.error();
    }


    @Override
    public ReturnT load(int id) {
        return ReturnT.success(userMapper.load(id));
    }


    @Override
    public ReturnT pageList(int offset, int pagesize) {

        List<User> pageList = userMapper.pageList(offset, pagesize);
        int totalCount = userMapper.pageListCount(offset, pagesize);

        // result
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("pageList", pageList);
        result.put("totalCount", totalCount);

        return ReturnT.success(result);
    }

}