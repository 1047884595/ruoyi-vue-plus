package org.dromara.telegram.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import jakarta.annotation.Resource;
import org.dromara.common.core.domain.R;
import org.dromara.common.core.domain.model.LoginUser;
import org.dromara.common.satoken.utils.LoginHelper;
import org.dromara.telegram.domain.MyBot;
import org.dromara.telegram.domain.TelegramGroupInfo;
import org.dromara.telegram.domain.UserTelegramBotToken;
import org.dromara.telegram.domain.UserTelegramGroup;
import org.dromara.telegram.mapper.TelegramGroupInfoMapper;
import org.dromara.telegram.mapper.UserTelegramBotTokenMapper;
import org.dromara.telegram.mapper.UserTelegramGroupMapper;
import org.dromara.telegram.service.TelegramBotService;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.DefaultBotOptions;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TelegramBotServiceImpl implements TelegramBotService {

    @Resource
    UserTelegramBotTokenMapper userTelegramBotTokenMapper;
    @Resource
    UserTelegramGroupMapper userTelegramGroupMapper;
    @Resource
    TelegramGroupInfoMapper telegramGroupInfoMapper;




    @Override
    public R start(){
        //配置代理服务器
        DefaultBotOptions botOptions = new DefaultBotOptions();
        botOptions.setProxyHost("127.0.0.1");
        botOptions.setProxyPort(60770);
        botOptions.setProxyType(DefaultBotOptions.ProxyType.HTTP);

        //查询用户机器人信息
        LoginUser loginUser = LoginHelper.getLoginUser();
        UserTelegramBotToken byUserId = userTelegramBotTokenMapper.findByUserId(loginUser.getUserId());
        if (byUserId == null){
            return R.fail("请先绑定机器人");
        }

        //查询用户绑定的群信息
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", loginUser.getUserId());
        List<UserTelegramGroup> userTelegramGroups = userTelegramGroupMapper.selectList(queryWrapper);
        if (userTelegramGroups.size() == 0){
            return R.fail("请先绑定群信息");
        }
        List<Long> telegramGroupInfoIds = userTelegramGroups.stream()
            .map(UserTelegramGroup::getTelegramGroupInfoId)
            .collect(Collectors.toList());


        //查询用户绑定的群号
        QueryWrapper queryWrapper1 = new QueryWrapper();
        queryWrapper1.in("id",telegramGroupInfoIds);
        List<TelegramGroupInfo> groupInfoList = telegramGroupInfoMapper.selectList(queryWrapper1);
        List<String> groupIds = groupInfoList.stream()
            .map(TelegramGroupInfo::getGroupId)
            .collect(Collectors.toList());

        MyBot bot = new MyBot(botOptions,byUserId.getBotUsername(),byUserId.getBotToken(),groupIds);

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(bot);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        return R.ok();
    }
}
