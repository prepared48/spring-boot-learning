package com.prepared.bean;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 *
 *
 * @Author: zhongshibo
 * @Date: 2020/3/10 13:40
 */
@Data
@TableName("t_user_test")
public class UserTest {

    /**
     * 主键，数据库没有指定自增
     */
    @TableId(type = IdType.INPUT, value = "id")
    private int id;

    @TableField("username")
    private String userName;

    @TableField("password")
    private String password;

}
