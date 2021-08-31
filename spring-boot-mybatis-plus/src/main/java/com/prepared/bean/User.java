package com.prepared.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

/**
 * 主键，数据库指定了自增User
 *
 * @Author: zhongshibo
 * @Date: 2020/3/10 13:40
 */
@Data
@TableName("t_user")
public class User {

    @TableId
    private int id;

    @TableField("userName")
    private String userName;

    @TableField("password")
    private String password;

    @TableField("addTime")
    private Date addTime;
}
