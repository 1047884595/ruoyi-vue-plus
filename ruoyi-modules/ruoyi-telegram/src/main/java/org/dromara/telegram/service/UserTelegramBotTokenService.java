package org.dromara.telegram.service;

import org.dromara.common.core.domain.R;
import org.dromara.telegram.bo.TelegramBotBo;

public interface UserTelegramBotTokenService {

    R addBotToken(TelegramBotBo telegramBotBo);

    R deleteBotToken();

    R updateBotToken(TelegramBotBo telegramBotBo);

    R queryBotInfo();

}
