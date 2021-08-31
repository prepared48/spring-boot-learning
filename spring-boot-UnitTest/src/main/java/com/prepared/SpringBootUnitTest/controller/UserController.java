package com.prepared.SpringBootUnitTest.controller;

import com.prepared.SpringBootUnitTest.common.ReturnT;
import com.prepared.SpringBootUnitTest.entity.User;
import com.prepared.SpringBootUnitTest.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author prepared
 * @date 2021-06-08
 */
@RestController
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    /**
     * 新增
     *
     * @date 2021/06/08
     **/
    @RequestMapping("/insert")
    public Object insert(User user) {
        return userService.insert(user);
    }

    /**
     * 刪除
     *
     * @date 2021/06/08
     **/
    @RequestMapping("/delete")
    public ReturnT delete(int id) {
        return userService.delete(id);
    }

    /**
     * 更新
     *
     * @date 2021/06/08
     **/
    @RequestMapping("/update")
    public ReturnT update(User user) {
        return userService.update(user);
    }

    /**
     * 查询 根据主键 id 查询
     *
     * @date 2021/06/08
     **/
    @RequestMapping("/load")
    public ReturnT load(int id) {
        return userService.load(id);
    }

    /**
     * 查询 分页查询
     *
     * @date 2021/06/08
     **/
    @RequestMapping("/pageList")
    public ReturnT pageList(@RequestParam(required = false, defaultValue = "0") int offset,
                            @RequestParam(required = false, defaultValue = "10") int pagesize) {
        return userService.pageList(offset, pagesize);
    }

}