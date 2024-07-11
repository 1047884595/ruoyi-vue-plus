package org.dromara.telegram.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("user_telegram_group")
public class UserTelegramGroup {

    private Long userId;


    private Long telegramGroupInfoId;

}
