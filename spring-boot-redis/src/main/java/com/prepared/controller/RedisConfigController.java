package com.prepared.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * @Author: zhongshibo
 * @Date: 2021/8/31 15:23
 */
@Controller
public class RedisConfigController {

    // inject the actual template
    @Autowired
    private RedisTemplate<String, String> template;

    // inject the template as ListOperations
    @Resource(name="redisTemplate")
    private ListOperations<String, String> listOps;

    @Resource(name="redisTemplate")
    private ValueOperations<String, String> valueOperations;

    @RequestMapping("/addValue")
    public void addValue(@Param("userId") String userId, @Param("value") String value) {
        valueOperations.set(userId, value);
    }

    @RequestMapping("/getValue")
    public String getValue(@Param("userId") String userId) {
        String result = valueOperations.get(userId);
        return result;
    }
}
