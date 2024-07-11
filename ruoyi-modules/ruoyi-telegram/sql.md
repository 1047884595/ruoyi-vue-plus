

CREATE TABLE `user_telegram_bot_token` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
`user_id` bigint NOT NULL COMMENT '用户ID，与sys_user表的user_id关联',
`bot_token` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT 'Telegram Bot的Token',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
`bot_username` varchar(255) NOT NULL COMMENT '机器人用户名',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户机器人令牌表';


CREATE TABLE `user_telegram_group` (
`user_id` bigint NOT NULL COMMENT '用户ID',
`telegram_group_info_id` bigint NOT NULL COMMENT 'Telegram群信息ID',
PRIMARY KEY (`user_id`,`telegram_group_info_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户和Telegram群信息关联表';


CREATE TABLE `telegram_group_info` (
`id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键ID，自增',
`user_id` bigint NOT NULL COMMENT '用户ID，与sys_user表的user_id关联',
`group_name` varchar(255) NOT NULL COMMENT '群名',
`group_id` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL COMMENT '群号（例如Telegram中的群组ID）',
`create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1811299775060045827 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='Telegram群信息表';