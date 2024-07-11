package org.dromara.telegram.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;


@Data
@TableName("user_telegram_bot_token")
public class UserTelegramBotToken {
    @TableId(value = "id")
    private Long id;

    private Long userId;


    private String botToken;

    private String botUsername;


    private Date createTime;

    private Date updateTime;


}
