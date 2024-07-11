package org.dromara.telegram.domain;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.util.Date;

@Data
@TableName("telegram_group_info")
public class TelegramGroupInfo {
    @TableId(value = "id")
    private Long id;

    private Long userId;


    private String groupName;

    private String groupId;


    private Date createTime;

    private Date updateTime;

}
