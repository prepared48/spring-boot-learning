
## 创建表
CREATE TABLE `t_post` (
    `post_id`  int(11) NULL DEFAULT NULL ,
    `post_name`  varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
)
ENGINE=InnoDB
DEFAULT CHARACTER SET=utf8 COLLATE=utf8_general_ci
ROW_FORMAT=DYNAMIC
;
## 插入测试数据
INSERT INTO `t_post` VALUES (1, '岗位1');
INSERT INTO `t_post` VALUES (2, '岗位2');

