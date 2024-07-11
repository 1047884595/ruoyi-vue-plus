package org.dromara.telegram.bo;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
public class TelegramBotBo {

    @NotBlank(message = "机器人用户名不能为空")
    private String botUsername;

    @NotBlank(message = "机器人令牌不能为空")
    private String botToken;
}
